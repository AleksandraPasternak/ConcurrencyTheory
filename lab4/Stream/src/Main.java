import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        try {
            int processorCount=5;
            Buffer buffer = new Buffer(10,processorCount);

            Thread producerThread = new Thread(new Producer(buffer));
            Thread consumerThread = new Thread(new Consumer(buffer));

            List<Thread> processors = new ArrayList<>();
            for (int i = 0; i < processorCount; i++) {
                processors.add(new Thread(new Processor(buffer, i+1)));
            }

            producerThread.start();
            for (Thread t : processors) t.start();
            consumerThread.start();

            producerThread.join();
            for (Thread processorThread : processors) processorThread.join();
            consumerThread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
