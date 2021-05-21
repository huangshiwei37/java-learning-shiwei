package multithreading.synchronization;

/**
 * @author huangshiwei on 2021-05-20
 */
public class SyncExampleCounter {

    private static int counter = 0;

    private static synchronized void incrementCounter(){
        System.out.println("counter="+counter);
        counter++;
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Thread1 i=" + i);
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
//                    counter++;
                    incrementCounter();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    System.out.println("Thread2 i=" + i);
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
//                    counter++;
                    incrementCounter();
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
        System.out.println("Main Thread finished:: counter="+counter);
    }


}
