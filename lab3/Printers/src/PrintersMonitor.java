import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintersMonitor {

    final Lock lock = new ReentrantLock();
    final Condition freePrinters = lock.newCondition();

    private LinkedList <Printer> printers;

    public PrintersMonitor(int M){
        printers = new LinkedList<>();
        for(int i=0;i<M;i++){
            printers.add(new Printer(i));
        }
    }

    public Printer takePrinter() throws InterruptedException{
        lock.lock();
        try{
            while(printers.isEmpty())
                freePrinters.await();
            Printer printer = printers.getFirst();
            printers.removeFirst();
            return printer;
        }
        finally {
            lock.unlock();
        }
    }

    public void returnPrinter(Printer printer){
        lock.lock();
        try{
            printers.add(printer);
            freePrinters.signal();
        } finally {
            lock.unlock();
        }
    }

}
