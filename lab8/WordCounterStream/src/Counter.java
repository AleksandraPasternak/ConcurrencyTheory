import java.util.stream.Stream;

public class Counter {

    public int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, 1),
                WordCounter::accumulate,
                WordCounter::combine
        );
        return wordCounter.getCounter();
    }
}
