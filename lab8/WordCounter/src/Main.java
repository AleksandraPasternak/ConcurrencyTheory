public class Main {

    public static void main(String[] args){

        FileOrganizer fileOrganizer = new FileOrganizer();
        //fileOrganizer.createLines();
        //fileOrganizer.insertDoubleSpaces("loremipsum-spaces.txt");

        Thread thread = new Thread(new StreamThread());
        thread.start();

        //Thread threadParallel = new Thread(new ParallelThread());
        //threadParallel.start();

    }
}
