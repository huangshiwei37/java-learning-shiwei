package multithreading.synchronization;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * @author huangshiwei on 2021-05-23
 */
public class SyncProducerConsumer {

    public static void main(String[] args) {
        ProducerConsumerService service = new ProducerConsumerService();
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

class ProducerConsumerService{

    private static final Object lock = new Object();
    private static final int MAX_NUMBER = 5;
    private static final ArrayList<Integer> products = new ArrayList();
    private static int product = 0;

    public void produce() throws InterruptedException{
        synchronized (lock){
            while(true){
                if (products.size() >= MAX_NUMBER){
                    lock.wait();
                }else{
                    products.add(product);
                    System.out.println("Add "+ product);
                    product++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException{
        synchronized (lock) {
            while(true){
                if (products.size() <= 0){
                    product = 0;
                    lock.wait();
                }else{
                    Integer removedItem = products.remove(products.size() - 1);
                    System.out.println("Remove " +removedItem);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

}
