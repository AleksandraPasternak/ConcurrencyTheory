import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class FileOrganizer {


    public void createLines() {

        PrintWriter pw =null;
        try {
            pw = new PrintWriter("loremipsummillion.txt");

            for (int i = 0; i < 5; i++) {
                BufferedReader br = new BufferedReader(new FileReader("loremipsum.txt"));
                String line;

                line = br.readLine();

                while (line != null) {
                    pw.println(line);
                    line = br.readLine();
                }
                pw.flush();
                br.close();
            }
            pw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void insertDoubleSpaces(String fileName){

        FileReader fileReader=null;
        try{
            fileReader = new FileReader(fileName);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        StringBuilder content = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
                content.append(System.getProperty("line.separator"));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String fileContent = content.toString();

        String randomString = "  ";
        char[] chrArray = fileContent.toCharArray();
        StringBuilder sb = new StringBuilder(fileContent);

        for(int i=0;i<100;i++) {
            Random rand = new Random();
            int insertSpot = rand.nextInt(chrArray.length + 1);

            if (insertSpot == chrArray.length) {
                sb.append(randomString);
            }
            else {
                sb.insert(insertSpot,randomString);
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try{
            bufferedWriter.write(sb.toString());
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
