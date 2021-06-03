package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshiwei on 2021-06-02
 */
public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i=0; i<20; i++){
            executorService.execute(new CountDownLatchWorker(i, countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish 5 jobs");

        executorService.shutdown();

        System.out.println("End????");
    }

}

class CountDownLatchWorker implements Runnable{

    private int id;
    private CountDownLatch countDownLatch;

    public CountDownLatchWorker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("End Worker " + id + " is running");
    }
}
