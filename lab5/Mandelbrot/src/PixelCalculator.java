import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PixelCalculator implements Callable<List<Result>>{

    private final int MAX_ITER;
    private final double ZOOM;
    private double zx, zy, cX, cY, tmp;
    private int xStart, yStart, xEnd, yEnd, step;

    public PixelCalculator(int xStart, int yStart, int xEnd, int yEnd, int step, int MAX_ITER, double ZOOM){
        this.xStart=xStart;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.xEnd=xEnd;
        this.step=step;
        this.MAX_ITER = MAX_ITER;
        this.ZOOM = ZOOM;
    }

    @Override
    public List<Result> call() {
        List<Result> results = new ArrayList<>();
        int y;
        int x=xStart;

         for (y = yStart; y < yEnd; y+=(x/xEnd)) {
             for (x = x%xEnd; x < xEnd; x += step) {
                 zx = zy = 0;
                 cX = (x - 400) / ZOOM;
                 cY = (y - 300) / ZOOM;
                 int iter = MAX_ITER;
                 while (zx * zx + zy * zy < 4 && iter > 0) {
                     tmp = zx * zx - zy * zy + cX;
                     zy = 2.0 * zx * zy + cY;
                     zx = tmp;
                     iter--;
                 }
                 results.add(new Result(iter, x, y));
             }
         }

        return results;
    }
}
