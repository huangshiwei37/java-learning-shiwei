package multithreading.synchronization;

/**
 * @author huangshiwei on 2021-05-20
 * It is good to use different object/class intrinsic locks for two indepedent methods
 * because they are able to be executed at the same time by multiple threads.
 */
public class SyncExampleCounterWithCustomObject {

    private static int counter1 = 0;
    private static int counter2 = 0;
    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    private static void incrementCounter1(){
        synchronized (object1) {
            System.out.println("counter1=" + counter1);
            counter1++;
        }
    }

    private static void incrementCounter2(){
        synchronized (object2) {
            System.out.println("counter2=" + counter2);
            counter2++;
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Thread1 i=" + i);
                    incrementCounter1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Thread2 i=" + i);
                    incrementCounter2();
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Main Thread finished:: counter1="+ counter1);
        System.out.println("Main Thread finished:: counter2="+ counter2);
    }


}
