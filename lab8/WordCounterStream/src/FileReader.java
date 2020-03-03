import java.io.*;

public class FileReader {

    public String readFileAsString(String path){
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream(path);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
