package multithreading.executors;

import multithreading.executors.task.CallableTask;
import multithreading.executors.task.RunnableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author huangshiwei on 2021-05-30
 */
public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("SingleThreadExecutorExample for RunnableTask");
        for (int i=0; i < 10; i++) {
            executorService.execute(new RunnableTask(i));
        }
        executorService.shutdown();
    }
}

