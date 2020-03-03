import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int bufferSize;
    private Integer[] buffer;
    private int processorsCount;

    private Lock[] locks;
    private Condition canProduce[];
    private Condition canConsume[];
    private Condition canProcess[];


    public Buffer(int bufferSize, int processorsCount){
        this.bufferSize=bufferSize;
        this.processorsCount=processorsCount;
        this.buffer= new Integer[bufferSize];
        this.locks = new ReentrantLock[bufferSize];
        this.canProduce = new Condition[bufferSize];
        this.canConsume = new Condition[bufferSize];
        this.canProcess = new Condition[bufferSize];
        for (int i=0; i<bufferSize; i++){
            locks[i] = new ReentrantLock();
            buffer[i] = -1;
            canProduce[i] = locks[i].newCondition();
            canConsume[i] = locks[i].newCondition();
            canProcess[i] = locks[i].newCondition();
        }
    }

    public void produce(int i, double speed) throws InterruptedException {
        locks[i].lock();
        TimeUnit.SECONDS.sleep((long)speed);
        while(!buffer[i].equals(-1)) canProduce[i].await();
        buffer[i] = 0;
        canProcess[i].signalAll();
        locks[i].unlock();
    }

    public void process(int i, int processorNumber, double speed){
        try {
            locks[i].lock();
            TimeUnit.SECONDS.sleep((long) speed);
            while (!buffer[i].equals(processorNumber - 1)) canProcess[i].await();
            buffer[i] = processorNumber;
            canProcess[i].signalAll();
            if (processorNumber == processorsCount) canConsume[i].signal();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            locks[i].unlock();
        }
    }

    public void consume(int i, double speed){
        try{
            locks[i].lock();
            TimeUnit.SECONDS.sleep((long)speed);
            while(!buffer[i].equals(processorsCount)) canConsume[i].await();
            buffer[i] = -1;
            canProduce[i].signal();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            locks[i].unlock();
        }
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
