package multithreading.queues;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author huangshiwei on 2021-06-03
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        PriorityBlockingQueue<String> priorityQueue = new PriorityBlockingQueue(2, new StringInversedComparator());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new PriorityBlockingQueueProducer(priorityQueue));
        executorService.execute(new PriorityBlockingQueueConsumer(priorityQueue));
        executorService.shutdown();
    }
}

class PriorityBlockingQueueProducer implements Runnable{

    PriorityBlockingQueue<String> priorityBlockingQueue;

    public PriorityBlockingQueueProducer(PriorityBlockingQueue<String> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        priorityBlockingQueue.put("E");
        priorityBlockingQueue.put("D");
        priorityBlockingQueue.put("A");
        priorityBlockingQueue.put("B");
        priorityBlockingQueue.put("C");
    }
}

class PriorityBlockingQueueConsumer implements Runnable{

    PriorityBlockingQueue<String> priorityBlockingQueue;

    public PriorityBlockingQueueConsumer(PriorityBlockingQueue<String> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String item = priorityBlockingQueue.take();
                System.out.println("Take item " + item);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class StringInversedComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1.compareTo(o2) < 0){
            return 1;
        } else if (o1.compareTo(o2) > 0){
            return -1;
        }
        return 0;
    }
}
