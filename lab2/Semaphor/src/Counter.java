public class Counter {

    public int number;
    public Semaphor semaphor = new Semaphor();

    public Counter(int number){
        this.number=number;
    }

    public void inc(){
        semaphor.V();
        number++;
        semaphor.P();
    }

    public void dec(){
        semaphor.V();
        number--;
        semaphor.P();
    }

}
