import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String [] args){

        int N=10;
        int M=4;

        PrintersMonitor printersMonitor = new PrintersMonitor(M);
        List<ThreadP> threads = new ArrayList<>();

        for(int i=0;i<N;i++){
            threads.add(new ThreadP(printersMonitor));
        }
        for(ThreadP thread : threads){
            thread.start();
        }
        for(ThreadP thread : threads){
            try{
                thread.join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
