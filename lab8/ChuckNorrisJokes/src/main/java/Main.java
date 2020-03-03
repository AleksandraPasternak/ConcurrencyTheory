public class Main {

    public static void main(String[] args){

        long startTime = System.nanoTime()/1_000_000_000;
        new JokesFetcher().getJokes();
        long endTime = System.nanoTime()/1_000_000_000-startTime;
        System.out.println("Getting 200 jokes : "+endTime+" s");

    }
}
