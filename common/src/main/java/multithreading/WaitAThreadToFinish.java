package multithreading;

/**
 * @author huangshiwei on 2021-05-20
 */
public class WaitAThreadToFinish {

    public static void main(String[] arg){
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: start");
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread 1:: end");
        });
        t1.start();
        try {
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Main Thread:: end");
    }
}
