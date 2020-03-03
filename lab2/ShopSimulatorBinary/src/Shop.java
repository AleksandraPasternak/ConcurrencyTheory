import java.util.LinkedList;

public class Shop {

    public Semaphor semaphor;
    public SemaphorBinary semaphorBinary;
    public LinkedList<Basket> baskets;

    public Shop(int basketCount){
        this.semaphor=new Semaphor(basketCount);
        this.semaphorBinary=new SemaphorBinary();
        this.baskets=new LinkedList<>();
        for(int i =0;i<basketCount;i++){
            baskets.add(new Basket(i));
        }
    }

    public Basket takeBasket(){
        semaphor.V();
        semaphorBinary.V();
        Basket basket = baskets.getFirst();
        baskets.removeFirst();
        semaphorBinary.P();
        return basket;
    }

    public void returnBasket(Basket basket){
        semaphorBinary.V();
        baskets.add(basket);
        semaphorBinary.P();
        semaphor.P();
    }

}
