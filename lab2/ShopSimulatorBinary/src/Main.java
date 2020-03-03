import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        Shop shop = new Shop(20);
        List<Consumer> consumers = new ArrayList<>();

        for(int i=0;i<200;i++){
            consumers.add(new Consumer(shop));
        }

        for(Consumer consumer : consumers){
            consumer.start();
        }

        try{
            for(Consumer consumer : consumers){
                consumer.join();
            }
        }
        catch (InterruptedException exception){
            System.out.println("Thread interrupted");
        }

        long endTime = System.currentTimeMillis();
        long programTime = (endTime - startTime);

        System.out.println("Czas trwania programu: " + programTime);

    }

}
