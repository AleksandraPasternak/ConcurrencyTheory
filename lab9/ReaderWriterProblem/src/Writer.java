public class Writer implements Runnable {

    private ReadingRoom readingRoom;
    private int number;

    public Writer(ReadingRoom readingRoom, int number) {
        this.readingRoom = readingRoom;
        this.number = number;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep((long)(Math.random() * 1000));
                long start = System.nanoTime();
                readingRoom.enterAndWrite(number);
                long endTime = System.nanoTime() - start;
                System.out.println("Writer - waiting time : "+endTime/1_000_000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
