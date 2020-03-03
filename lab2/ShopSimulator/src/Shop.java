public class Shop {

    Semaphor semaphor;

    public Shop(int basketCount){
        this.semaphor=new Semaphor(basketCount);
    }

    public void visit(){
        semaphor.V();
        try{
            Thread.sleep((long)(Math.random() * 1000));
        }
        catch (InterruptedException exception){
            System.out.println("Thread interrupted");
        }
        semaphor.P();
    }

}
