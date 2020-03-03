import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Counter wordCounter = new Counter();
        FileReader fileReader = new FileReader();

        long startTime = System.nanoTime()/1_000_000;
        Stream<Character> characterStream = fileReader
                .readFileAsString("loremipsum-spaces.txt")
                .chars()
                .mapToObj(c -> (char) c);
        long wordCount = wordCounter.countWords(characterStream);
        long endTime = System.nanoTime()/1_000_000 - startTime;

        System.out.println("Found " + wordCount + " words");

        System.out.println("Calculating : " + endTime + " ms");
    }

}
