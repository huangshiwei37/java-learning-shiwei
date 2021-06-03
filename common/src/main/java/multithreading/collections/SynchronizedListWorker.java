package multithreading.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huangshiwei on 2021-06-02
 * SynchronizedList
 * SynchronizedRandomAccessList
 */
public class SynchronizedListWorker{

    List<Integer> nums = Collections.synchronizedList(new ArrayList<>(2000));

    public void execute() {
        for(int i=0; i<1000; i++){
            nums.add(i);
        }
    }

    public int getNumbersSize(){
        return nums.size();
    }
}
