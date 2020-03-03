public class Semaphor {

    public int basketCount;

    public Semaphor(int basketCount){
        this.basketCount=basketCount;
    }

    public synchronized void V(){
        while(basketCount<=0){
            try{
                wait();
            }
            catch (InterruptedException exception){
                System.out.println("Thread interrupted");
            }
        }
        basketCount--;
    }

    public synchronized void P(){
        basketCount++;
        notifyAll();
    }

}
