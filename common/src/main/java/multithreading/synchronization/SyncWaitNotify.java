package multithreading.synchronization;

/**
 * @author huangshiwei on 2021-05-21
 */
public class SyncWaitNotify {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class Processor {
    public void produce() throws InterruptedException{
        synchronized (this){
            System.out.println("produce() is running ...");
            this.wait(); // It releases the intrinsic lock after calling wait() and it will re-obtain the intrinsic lock after another thread notifies it
            System.out.println("produce() ends");
        }
    }

    public void consume() throws InterruptedException{
        synchronized (this){
            System.out.println("consume() is running ...");
            this.notify(); // it doesn't notify immediately util the end of the synchronized block or calling wait() explicitly
            Thread.sleep(2000);
            System.out.println("consume() ends");
        } // release lock after executing the block only or call wait() explicitly
    }

}

