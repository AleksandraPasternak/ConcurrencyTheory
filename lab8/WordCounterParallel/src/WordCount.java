import java.util.*;
import java.util.stream.*;

public class WordCount {

    public int countWords(String sentences) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(sentences);

        WordCounter wordCounter = StreamSupport.stream(spliterator, true)
                .reduce(new WordCounter(0, 1),
                WordCounter::accumulate,
                WordCounter::combine
        );

        return wordCounter.getCounter();
    }

}