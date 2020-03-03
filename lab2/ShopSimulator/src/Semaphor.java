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
        System.out.println("Koszyk wziety. Dostepnych: " + basketCount);
    }

    public synchronized void P(){
        basketCount++;
        System.out.println("Koszyk odlozony. Dostepnych: " + basketCount);
        notifyAll();
    }

}
