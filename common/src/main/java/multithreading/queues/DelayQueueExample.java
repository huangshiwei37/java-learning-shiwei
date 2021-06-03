package multithreading.queues;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author huangshiwei on 2021-06-03
 */
public class DelayQueueExample {
    public static void main(String[] args) {
        DelayQueue<DelayedObject> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedObject(200, 1));
        delayQueue.put(new DelayedObject(100, 2));
        delayQueue.put(new DelayedObject(300, 3));

        while(!delayQueue.isEmpty()){
            try {
                DelayedObject delayedObject = delayQueue.take();
                System.out.println("Take object id " + delayedObject.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DelayedObject implements Delayed {

    private long delayMilliseconds;
    private long createdTime;
    private int id;

    public DelayedObject(long delayMilliseconds, int id) {
        this.delayMilliseconds = delayMilliseconds;
        this.createdTime = System.currentTimeMillis();
        this.id = id;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long elapsedTime = System.currentTimeMillis() - createdTime;
        return unit != null? unit.convert(delayMilliseconds - elapsedTime, TimeUnit.MILLISECONDS): 0;
    }

    @Override
    public int compareTo(Delayed o) {
        if (delayMilliseconds < ((DelayedObject) o).getDelayMilliseconds()){
            return -1;
        }else if (delayMilliseconds > ((DelayedObject) o).getDelayMilliseconds()){
            return 1;
        }else {
            return 0;
        }
    }

    public long getDelayMilliseconds() {
        return delayMilliseconds;
    }

    public int getId() {
        return id;
    }
}
