
public class Main {

    public static void main(String[] args){

            //String token = new TokenFetcher().getAccessToken();
            long startTime = System.nanoTime()/1_000_000_000;
            new PriceGetter().getPrices();
            long endTime = System.nanoTime()/1_000_000_000-startTime;
            System.out.println("Pobranie 200 elementów : "+endTime+" s");

    }

}
