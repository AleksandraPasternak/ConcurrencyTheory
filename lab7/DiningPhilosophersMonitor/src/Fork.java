import java.util.concurrent.Semaphore;

public class Fork {

    public Semaphore mutex = new Semaphore(1);

    public void PB() {
        try {
            mutex.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

    public void VB() {
        mutex.release();
    }

    public void isFree(){

    }
}
