package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshiwei on 2021-06-02
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int jobsInParallel = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(jobsInParallel, () ->{
            System.out.println("Finished " + jobsInParallel + " jobs ...");
        });

        ExecutorService executorService = Executors.newFixedThreadPool(jobsInParallel);
        for (int i=1; i<=15; i++){
            executorService.execute(new CyclicBarrierWorker(i, cyclicBarrier));
        }

        executorService.shutdown();

        System.out.println("End????");
    }

}

class CyclicBarrierWorker implements Runnable{

    private int id;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Worker " + id + " is running");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("After await() of Worker " + id);
    }
}
