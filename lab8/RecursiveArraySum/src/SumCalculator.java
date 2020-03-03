import java.util.concurrent.RecursiveTask;

public class SumCalculator extends RecursiveTask<Long> {

    private long[] numbers;
    private int start;
    private int end;
    public static final long limit = 10_000;

    public SumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= limit) {
            return computeSequentially();
        }
        SumCalculator leftTask =
                new SumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        SumCalculator rightTask =
                new SumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}
