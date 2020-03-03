import java.util.concurrent.Semaphore;

public class ReadingRoom {

    private Buffer buffer;
    private int readersCount;
    private Semaphore queue;
    private Semaphore writingAccess;
    private Semaphore joinReaders;

    public ReadingRoom() {
        this.buffer = new Buffer();
        this.readersCount=0;
        this.queue = new Semaphore(1);
        this.joinReaders = new Semaphore(1);
        this.writingAccess = new Semaphore(1);
    }

    public void enterAndRead() throws InterruptedException{

        queue.acquire();
        joinReaders.acquire();

        if(++readersCount==1){
            writingAccess.acquire();
        }

        joinReaders.release();
        queue.release();

        buffer.getText();
        Thread.sleep(1000); //reading

        joinReaders.acquire();
        if(--readersCount==0){
            writingAccess.release();
        }
        joinReaders.release();
    }

    public void enterAndWrite(int number) throws InterruptedException{
        queue.acquire();
        writingAccess.acquire();

        Thread.sleep(1000); //looking for a pen
        buffer.setText("Hello from "+number);

        writingAccess.release();
        queue.release();
    }

}
