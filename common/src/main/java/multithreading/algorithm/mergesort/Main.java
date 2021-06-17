package multithreading.algorithm.mergesort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author huangshiwei on 2021-06-16
 */
public class Main {

    public static void main(String[] args) {
        int count = (int)1e6;
        System.out.println("count="+count);
        int[] nums = generateRandomNums(count);
        int[] nums2 = Arrays.copyOfRange(nums, 0, nums.length);

        //parallel
        long startTime = System.currentTimeMillis();
        ParallelMergeSortTask task = new ParallelMergeSortTask(nums);
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors="+processors);
        ForkJoinPool forkJoinPool = new ForkJoinPool(processors);
        forkJoinPool.invoke(task);
//        System.out.println("AfterSort nums=" + Arrays.toString(nums));
        System.out.println("Parallel elapsedTime=" + (System.currentTimeMillis() - startTime));

        //sequential
        startTime = System.currentTimeMillis();
        SequentialMergeSortService sequentialMergeSortService = new SequentialMergeSortService();
        sequentialMergeSortService.mergeSort(nums2);
//        System.out.println("AfterSort nums=" + Arrays.toString(nums2));
        System.out.println("Sequential elapsedTime=" + (System.currentTimeMillis() - startTime));
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
