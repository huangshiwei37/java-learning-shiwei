package multithreading.executors.task;

import java.util.concurrent.Callable;

/**
 * @author huangshiwei on 2021-05-30
 */
public class CallableTask implements Callable {

    private int id;

    public CallableTask(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000);
            System.out.println("Running task " + id + " by thread " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return "taskId=" + id;
    }
}
