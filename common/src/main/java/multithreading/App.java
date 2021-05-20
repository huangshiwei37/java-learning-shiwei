package multithreading;

import com.sun.jdi.request.ThreadDeathRequest;

/**
 * @author huangshiwei on 2021-05-20
 */
public class App {

    public static void main(String[] arg) {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        t1.start();
        t2.start();
    }

}

class Runner1 implements Runnable{
    public void run() {
        for (int i=0; i< 10; i++){
            System.out.println("Runner1 " + i);
        }
    }
}


class Runner2 implements Runnable{
    public void run() {
        for (int i=0; i< 10; i++){
            System.out.println("Runner2 " + i);
        }
    }
}
