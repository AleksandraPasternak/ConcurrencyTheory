import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Simulator {

    private static final String FILE_NAME = "results.csv";
    private FileWriter fileWriter;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public void simulate(){
        new Mandelbrot(1,1).setVisible(false);
        try{
            this.fileWriter=new FileWriter(FILE_NAME);
        } catch (IOException e){
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fileWriter);
        try{
            writer.write("threadsNo,tasksNo,time\n");
        } catch (IOException e){
            e.printStackTrace();
        }

        int [] threadsNo = {1,4,8};

        for(int thread : threadsNo){
            int [] tasksNo = {thread, thread*10, WIDTH*HEIGHT};
            for(int task : tasksNo){
                for(int i=0;i<10;i++){
                    final long startTime = System.currentTimeMillis();
                    new Mandelbrot(task,thread).setVisible(true);
                    final long endTime = System.currentTimeMillis();

                    final long totalTime = endTime - startTime;

                    try{
                        writer.write(String.format("%d,%d,%d\n", thread, task, totalTime));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        try{
            writer.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
