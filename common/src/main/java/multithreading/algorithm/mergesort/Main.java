package multithreading.algorithm.mergesort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author huangshiwei on 2021-06-16
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = generateRandomNums(10000);
        long startTime = System.currentTimeMillis();
//        System.out.println("BeforeSort nums=" + Arrays.toString(nums));

        ParallelMergeSortTask task = new ParallelMergeSortTask(nums);
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors="+processors);
        ForkJoinPool forkJoinPool = new ForkJoinPool(processors);
        forkJoinPool.invoke(task);

//        System.out.println("AfterSort nums=" + Arrays.toString(nums));
        System.out.println("Parallel elapsedTime=" + (System.currentTimeMillis() - startTime));
    }

    private static int[] generateRandomNums(int length){
        Random random = new Random();
        int[] nums = new int[length];
        for (int i=0; i < length; i++){
            nums[i] = random.nextInt(10);
        }
        return nums;
    }
}
