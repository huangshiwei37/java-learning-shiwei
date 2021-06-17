package multithreading.algorithm.mergesort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author huangshiwei on 2021-06-15
 */
public class ParallelMergeSortTask extends RecursiveAction {

    private int[] nums;
    private SequentialMergeSortService sequentialMergeSortService = new SequentialMergeSortService();

    public ParallelMergeSortTask(int[] nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
        if(nums.length <= 1000){
            sequentialMergeSortService.mergeSort(nums);
            return;
        }
        int middle = nums.length/2;
        int[] left = Arrays.copyOfRange(nums, 0, middle);
        int[] right = Arrays.copyOfRange(nums, middle, nums.length);

        ParallelMergeSortTask leftSort = new ParallelMergeSortTask(left);
        ParallelMergeSortTask rightSort = new ParallelMergeSortTask(right);
        ForkJoinTask.invokeAll(leftSort, rightSort);

        merge(left, right, nums);
    }

    private void merge(int[] left, int[] right, int[] nums){
        int i = 0;
        int j = 0;

        for (int k = 0; k < nums.length; k++){
            if (i >= left.length){
                nums[k] = right[j];
                j++;
            }else if (j >= right.length){
                nums[k] = left[i];
                i++;
            }else {
                if (left[i] <= right[j]){
                    nums[k] = left[i];
                    i++;
                }else{
                    nums[k] = right[j];
                    j++;
                }
            }
        }
    }
}
