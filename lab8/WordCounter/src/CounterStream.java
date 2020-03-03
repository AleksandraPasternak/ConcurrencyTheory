import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CounterStream {

    public long count(String path){

        long wordCount = 0;
        Path textFilePath = Paths.get(path);
        try {
            String regex="\\s+";
            wordCount = Files.lines(textFilePath, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(regex)))
                    .count();
            String regex2 = "\\s{2}";
            long wordCount2 = Files.lines(textFilePath, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(regex2)))
                    .count();
            String regex3 = "\\s{3}";
            long wordCount3 = Files.lines(textFilePath, Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(regex3)))
                    .count();
            wordCount= wordCount - wordCount2 + wordCount3;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
}
