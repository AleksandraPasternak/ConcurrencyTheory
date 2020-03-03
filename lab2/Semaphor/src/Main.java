public class Main {

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        Counter counter = new Counter(0);

        IncThread incThread = new IncThread(counter);
        DecThread decThread = new DecThread(counter);

        incThread.start();
        decThread.start();

        try{
            incThread.join();
            decThread.join();
        }
        catch(InterruptedException ex){
            System.out.println("Threads did not end properly");
        }

        System.out.println("Final number is : " + counter.number);

        long endTime = System.currentTimeMillis();
        long programTime = (endTime - startTime);

        System.out.println("Program lasted for (in miliseconds): " + programTime);
    }

}
