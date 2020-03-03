import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CounterParallel {

    public long count(String path){

        long wordCount = 0;
        Path textFilePath = Paths.get(path);
        try {
            wordCount = Files.lines(textFilePath, Charset.defaultCharset())
                    .parallel()
                    .flatMap(line -> Arrays.stream(line.split("[ ][^ ]")))
                    .count();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
}
