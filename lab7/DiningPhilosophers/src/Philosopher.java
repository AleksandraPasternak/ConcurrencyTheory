import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{

    public int number;
    public Fork leftFork;
    public Fork rightFork;
    public Arbiter arbiter;
    private long time;
    private static int lines = 0;
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter writer;

    public void run(){
        while (true) {
            int count=10;
            time = System.nanoTime();
            while(count>0) {
                think();
                arbiter.P();
               /* if (number % 2 == 0) {
                    rightFork.PB();
                    leftFork.PB();
                } else {*/
                    leftFork.PB();
                    rightFork.PB();
                //}
                eat();
                leftFork.VB();
                rightFork.VB();
                arbiter.V();
                count--;
            }
            time = System.nanoTime() - time;
            double milliseconds = (double) time / 1000000;
            writer.write("");
            if(lines <= 150) {
                writer.println("arbiter 15 " + this.number + " " + milliseconds);
                writer.flush();
                lines++;
            }
        }
    }

    public void think(){
        try {
            int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void eat() {
        try {
            int sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Philosopher(int number, Fork leftFork, Fork rightFork, Arbiter arbiter) {
        this.number = number;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.arbiter=arbiter;
        try{
            this.fw= new FileWriter("times2.csv", true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.bw = new BufferedWriter(fw);
        this.writer = new PrintWriter(bw);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    public Arbiter getArbiter() {
        return arbiter;
    }

    public void setArbiter(Arbiter arbiter) {
        this.arbiter = arbiter;
    }
}
