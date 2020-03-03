public class Counter {

    public int number;

    public Counter(int number){
        this.number=number;
    }

    public synchronized void inc(){ number++; }

    public synchronized void dec(){ number--; }

}
