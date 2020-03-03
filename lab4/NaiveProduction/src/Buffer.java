import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int elementsCount;
    private int M;
    private Integer[] buffer = new Integer[2*M];
    private final Lock lock = new ReentrantLock();
    private Condition canProduce = lock.newCondition();
    private Condition canConsume = lock.newCondition();

    public Buffer(int _M){
        this.M = _M;
        this.elementsCount = 0;
        for(int i=0; i<buffer.length; i++) buffer[i] = -1;
    }

    public void put(int amount){
        try{
            lock.lock();
            while(2*M - elementsCount < amount) canProduce.await();
            elementsCount += amount;
            canConsume.signalAll();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void take(int amount){
        try{
            lock.lock();
            while(elementsCount < amount) canConsume.await();
            elementsCount -= amount;
            canProduce.signalAll();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

}
