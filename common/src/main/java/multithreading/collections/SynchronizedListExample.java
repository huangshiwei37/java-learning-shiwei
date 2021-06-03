package multithreading.collections;

/**
 * @author huangshiwei on 2021-06-02
 */
public class SynchronizedListExample {
    public static void main(String[] args) {
        SynchronizedListWorker worker = new SynchronizedListWorker();
        Thread t1 = new Thread(worker:: execute);
        Thread t2 = new Thread(worker:: execute);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Result:: size=" + worker.getNumbersSize());
    }
}
