public class Consumer extends Thread{

    public Shop shop;

    public Consumer(Shop shop){
        this.shop=shop;
    }

    public void run(){
        Basket basket = shop.takeBasket();
        try{
            Thread.sleep((long)(Math.random() * 1000));
        }
        catch (InterruptedException exception){
            System.out.println("Thread interrupted");
        }
        shop.returnBasket(basket);
    }

}
