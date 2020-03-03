public class Consumer extends Thread{

    public Shop shop;

    public Consumer(Shop shop){
        this.shop=shop;
    }

    public void run(){
        shop.visit();
    }

}
