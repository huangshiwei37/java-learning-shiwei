package multithreading;

/**
 * @author huangshiwei on 2021-05-20
 */
public class DaemonThread {

    public static void main(String[] arg){
        Thread daemonWorkerThread = new Thread(()->{
            while(true){
                System.out.println("Daemon worker thread is running ...");
            }
        });

        Thread normalWorkerThread = new Thread(()->{
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Normal worker thread finished.");
        });

        daemonWorkerThread.setDaemon(true);
        daemonWorkerThread.start();
        normalWorkerThread.start();
    }
}


