package multithreading.executors;

import multithreading.executors.task.RunnableTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author huangshiwei on 2021-05-30
 */
public class ScheduledThreadPoolExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i=0; i < 10; i++) {
            executorService.scheduleAtFixedRate(new RunnableTask(i), 1000, 5000, TimeUnit.MILLISECONDS);
        }
        System.out.println("ScheduledThreadPoolExecutorExample");
//        executorService.shutdown();
    }
}

