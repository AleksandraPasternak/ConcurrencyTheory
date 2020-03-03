import java.util.concurrent.Semaphore;

public class Arbiter {

    public Semaphore mutex = new Semaphore(4);

    public void P() {
        try {
            mutex.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

    public void V() {
        mutex.release();
    }

}
