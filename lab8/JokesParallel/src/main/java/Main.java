import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(20);
        ExecutorService executorApply = Executors.newFixedThreadPool(20);

        long startTime = System.nanoTime()/1_000_000_000;
        new JokesFetcher().getJokes(executor, executorApply);
        long endTime = System.nanoTime()/1_000_000_000 - startTime;

        System.out.println("Getting 200 jokes : "+endTime+" s");
        executor.shutdown();
        executorApply.shutdown();
    }

}
