package multithreading.synchronization;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangshiwei on 2021-05-23
 */
public class ReentrantLockProducerConsumer {

    public static void main(String[] args) {
        ReentrantProducerConsumerService service = new ReentrantProducerConsumerService();
        Thread producerThread = new Thread(() -> {
            try {
                service.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                service.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }


}

class ReentrantProducerConsumerService {

    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final Condition lockCond = lock.newCondition();
    private static final int MAX_NUMBER = 5;
    private static final ArrayList<Integer> products = new ArrayList();
    private static int product = 0;

    public void produce() throws InterruptedException{
        lock.lock();
        try {
            while (true) {
                if (products.size() >= MAX_NUMBER) {
                    lockCond.await();
                } else {
                    products.add(product);
                    System.out.println("Add " + product);
                    product++;
                    lockCond.signal();
                }
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void consume() throws InterruptedException{
        lock.lock();
        try {
            while (true) {
                if (products.size() <= 0) {
                    product = 0;
                    lockCond.await();
                } else {
                    Integer removedItem = products.remove(products.size() - 1);
                    System.out.println("Remove " + removedItem);
                    lockCond.signal();
                }
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
