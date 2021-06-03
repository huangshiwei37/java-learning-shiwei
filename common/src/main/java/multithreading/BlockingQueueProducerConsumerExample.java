package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huangshiwei on 2021-06-03
 */
public class BlockingQueueProducerConsumerExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        executorService.execute(new BlockingQueueProducer(blockingQueue));
        executorService.execute(new BlockingQueueConsumer(blockingQueue));
        executorService.shutdown();
    }

}

class BlockingQueueProducer implements Runnable {

    private BlockingQueue<Integer> blockingQueue;
    private int item = 0;

    public BlockingQueueProducer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                blockingQueue.put(item);
                System.out.println("Add item " + item);
                Thread.sleep(100);
                item++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BlockingQueueConsumer implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public BlockingQueueConsumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int item = blockingQueue.take();
                System.out.println("Remove item " + item);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
