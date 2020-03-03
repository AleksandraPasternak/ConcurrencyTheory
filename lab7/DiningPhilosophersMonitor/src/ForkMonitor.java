import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForkMonitor {

    private int[] freeForks;
    private int length;
    private Lock lock = new ReentrantLock();
    private Condition[] philosophers;

    public ForkMonitor(int length) {
        this.length=length;
        this.freeForks=new int[length];
        this.philosophers=new Condition[length];
        for(int i=0;i<length;i++){
            freeForks[i]=2;
            philosophers[i]=lock.newCondition();
        }
    }

    public void acquire(int i){
        lock.lock();
        try{
            while(freeForks[i]<2)
                philosophers[i].await();
            freeForks[(i+length-1)%length]--;
            freeForks[(i+1)%length]--;
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void release(int i){
        lock.lock();
        try {
            freeForks[(i + length - 1) % length]++;
            freeForks[(i + 1) % length]++;
            philosophers[(i + length - 1) % length].signal();
            philosophers[(i + 1) % length].signal();
        }
        finally {
            lock.unlock();
        }
    }

}
