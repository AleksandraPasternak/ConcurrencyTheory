import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        Buffer buffer = new Buffer();

        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(new Consumer(buffer)));
        threads.add(new Thread(new Producer(buffer)));
        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            try{
                thread.join();
            }
            catch(InterruptedException execption){
                System.out.println("Thread was not finished");
            }
        }
        System.out.println("Messages flow finished");

    }
}
