import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER=570;
    private final double ZOOM = 150;
    private BufferedImage I;

    public Mandelbrot(int iterations, int threads) {
        super("Mandelbrot Set");
        setBounds(3, 3, 8, 4);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<List<Result>>> results = new ArrayList<>();

        for(int i=0;i<iterations;i++){
            Callable<List<Result>> task = new PixelCalculator(
                    i%getWidth(), i/getWidth(),
                    getWidth(), getHeight(),
                    iterations, MAX_ITER, ZOOM);
            Future<List<Result>> future = executor.submit(task);
            results.add(future);
        }

        for(Future<List<Result>> future : results){
            if(!future.isCancelled()){
                try {
                    for(Result result : future.get()) {
                        I.setRGB(result.x, result.y, result.iter | (result.iter << 8));
                    }
                }
                catch (ExecutionException | InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        executor.shutdown();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

}
