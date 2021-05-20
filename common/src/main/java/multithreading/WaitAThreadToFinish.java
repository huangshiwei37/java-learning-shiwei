package multithreading;

/**
 * @author huangshiwei on 2021-05-20
 */
public class WaitAThreadToFinish {

    public static void main(String[] arg){
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: start downloadImage...");
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread 1:: end downloadImage");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2: start processImage...");
            System.out.println("Thread 2:: end processImage");
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Main Thread:: end");
    }
}
