public class Printer {

    private int number;

    public Printer(int i){
        number=i;
    }

    public void print(){
        try{
            Thread.sleep((long)(Math.random() * 1000));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
