import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){
        try {
            //String token = new TokenFetcher().getDeviceCode();

            ExecutorService executor = Executors.newFixedThreadPool(1);

            long startTime = System.nanoTime()/1_000_000_000;
            new PriceFetcher().getPrices(executor);
            long endTime = System.nanoTime()/1_000_000_000 - startTime;

            System.out.println("Pobranie 200 elementów równolegle : "+endTime+" s");
            executor.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
