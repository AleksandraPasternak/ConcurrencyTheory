
public class Main {

    public static void main(String[] args) {

        WordCount wordCounter = new WordCount();
        FileReader fileReader = new FileReader();

        long startTime = System.nanoTime()/1_000_000;
        long wordCount = wordCounter.countWords(fileReader.readFileAsString("loremipsum-spaces.txt"));
        long endTime = System.nanoTime()/1_000_000 - startTime;

        System.out.println("Found " + wordCount + " words");

        System.out.println("Calculating in parallel version : " + endTime + " ms");
    }

}
