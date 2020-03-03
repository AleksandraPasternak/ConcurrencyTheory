public class StreamThread implements Runnable{

    public void run(){
        long startTime = System.nanoTime();
        long wordCount = new CounterStream().count("loremipsum-spaces.txt");
        long endTime = System.nanoTime() - startTime;

        System.out.println("Number of words : "+ wordCount);

        System.out.println("Calculating in parallel version : "+endTime/1_000_000+" ms");
    }

}
