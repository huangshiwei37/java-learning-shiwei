package multithreading.executors.task;

/**
 * @author huangshiwei on 2021-05-30
 */
public class RunnableTask implements Runnable {

    private int id;

    public RunnableTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Running task " + id + " by thread " + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
