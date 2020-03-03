public class Reader implements Runnable{

    private ReadingRoom readingRoom;

    public Reader(ReadingRoom readingRoom) {
        this.readingRoom = readingRoom;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep((long)(Math.random() * 1000));
                long start = System.nanoTime();
                readingRoom.enterAndRead();
                long endTime = System.nanoTime() - start;
                System.out.println("Reader - waiting time : "+endTime/1_000_000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
