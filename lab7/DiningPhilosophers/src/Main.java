import java.io.IOException;
import java.util.concurrent.*;

public class Main{

    public static void main(String argv[]){

        int philosophersNumber = 15;
        Philosopher philosophers[] = new Philosopher[philosophersNumber];
        Fork forks[] = new Fork[philosophersNumber];
        Arbiter arbiter = new Arbiter();

        for (int i = 0; i < philosophersNumber; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < philosophersNumber; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % philosophersNumber], arbiter);
            philosophers[i].start();
        }

    }
}
