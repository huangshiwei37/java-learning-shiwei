package multithreading;

/**
 * @author huangshiwei on 2021-06-02
 */
public class StopThreadExampleViaVolatileVariable implements Runnable{

    private volatile boolean running;
    private Thread thread;

    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        running = false;
        thread.interrupt();
    }


    @Override
    public void run() {
        while (running){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
