public class Processor implements Runnable {
    private Buffer buffer;
    private int number;
    private double speed = Math.random() * 3;

    public Processor(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    public void run() {

        for (int i = 0; i < buffer.getBufferSize(); i++) {
            try {
                buffer.process(i, this.number, this.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
