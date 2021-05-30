package multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshiwei on 2021-05-30
 */
public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0; i < 10; i++) {
            executorService.execute(new Task(i));
        }
        System.out.println("SingleThreadExecutorExample");
        executorService.shutdown();
    }
}

class Task implements Runnable{

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Running task " + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
