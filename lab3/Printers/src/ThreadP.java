public class ThreadP extends Thread{

    private PrintersMonitor printersMonitor;

    public ThreadP(PrintersMonitor printersMonitor){
        this.printersMonitor=printersMonitor;
    }

    public void run(){

        while(true){
            try {
                Thread.sleep((long)(Math.random() * 1000));
                Printer printer = printersMonitor.takePrinter();
                printer.print();
                printersMonitor.returnPrinter(printer);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
