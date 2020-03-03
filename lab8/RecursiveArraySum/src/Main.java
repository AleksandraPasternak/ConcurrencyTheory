import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        long[] numbers = LongStream.rangeClosed(1, 20_000_000).toArray();
        ForkJoinTask<Long> task = new SumCalculator(numbers, 0, numbers.length);
        Long invoke = new ForkJoinPool().invoke(task);
        System.out.println(invoke);
        long endTime = System.nanoTime() - startTime;
        System.out.println("Summed in : "+ endTime/1_000_000 + "ms");

    }

}
