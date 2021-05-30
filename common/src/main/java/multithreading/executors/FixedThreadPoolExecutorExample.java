package multithreading.executors;

import multithreading.executors.task.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshiwei on 2021-05-30
 */
public class FixedThreadPoolExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i=0; i < 10; i++) {
            executorService.execute(new RunnableTask(i));
        }
        System.out.println("FixedThreadPoolExecutorExample");
        executorService.shutdown();
    }
}

