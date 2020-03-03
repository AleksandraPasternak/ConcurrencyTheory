import java.util.concurrent.Semaphore;

public class ReadingRoom {

    private Buffer buffer;
    private int readersIn;
    private int readersOut;
    private boolean writerWaiting;
    private Semaphore enteringReaders;
    private Semaphore leavingReaders;
    private Semaphore writingAccess;

    public ReadingRoom() {
        this.buffer = new Buffer();
        this.readersIn=0;
        this.readersOut=0;
        this.writerWaiting = false;
        this.enteringReaders = new Semaphore(1);
        this.leavingReaders = new Semaphore(1);
        this.writingAccess = new Semaphore(1);
    }

    public void enterAndRead() throws InterruptedException{

        enteringReaders.acquire();
        readersIn++;
        enteringReaders.release();

        buffer.getText();
        Thread.sleep(1000); //reading

        leavingReaders.acquire();
        readersOut++;
        if(writerWaiting && readersOut==readersIn){
            writingAccess.release();
        }
        leavingReaders.release();

    }

    public void enterAndWrite(int number) throws InterruptedException{
        enteringReaders.acquire();
        leavingReaders.acquire();
        if(readersIn==readersOut){
            leavingReaders.release();
        }
        else{
            writerWaiting=true;
            leavingReaders.release();
            writingAccess.acquire();
            writerWaiting=false;
        }

        Thread.sleep(1000); //looking for a pen
        buffer.setText("Hello from : "+number);

        enteringReaders.release();
    }

}
