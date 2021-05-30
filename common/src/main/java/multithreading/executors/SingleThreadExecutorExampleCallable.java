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
public class SingleThreadExecutorExampleCallable {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("SingleThreadExecutorExample for CallableTask");
        List<Future<String>> resultList = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            Future<String> future = executorService.submit(new CallableTask(i));
            resultList.add(future);
        }
        for(Future<String> future: resultList){
            try {
                System.out.println("Return:: " + future.get() + ", size=" + resultList.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

    }
}

