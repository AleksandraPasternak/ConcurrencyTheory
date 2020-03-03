import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ReadingRoom readingRoom = new ReadingRoom();

        List<Thread> writers = new ArrayList<>();

        for(int i=0;i<20;i++){
            writers.add(new Thread(new Writer(readingRoom, i)));
        }

        for(Thread writer : writers){
            writer.start();
        }

        List<Thread> readers = new ArrayList<>();

        for(int i=0;i<200;i++){
            readers.add(new Thread(new Reader(readingRoom)));
        }

        for(Thread reader : readers){
            reader.start();
        }

        try{
            for(Thread reader : readers){
                reader.join();
            }
            for(Thread writer : writers){
                writer.join();
            }
        }
        catch (InterruptedException exception){
            System.out.println("Thread interrupted");
        }
    }
}
