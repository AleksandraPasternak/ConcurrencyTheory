public class SemaphorBinary {

    public boolean free=true;

    public synchronized void P(){
        free=true;
        notifyAll();
    }

    public synchronized void V(){
        while(!free){
            try{
                wait();
            }
            catch (InterruptedException exception){
                System.out.println("Thread interrupted");
            }
        }
        free=false;
    }
}
