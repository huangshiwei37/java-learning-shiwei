package multithreading.algorithm.mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author huangshiwei on 2021-06-15
 */
public class SequentialMergeSortService {

    public void mergeSort(int[] nums){
        if (nums.length <= 1){
            return;
        }

        int middle = nums.length/2;
        int[] left = Arrays.copyOfRange(nums, 0, middle);
        int[] right = Arrays.copyOfRange(nums, middle, nums.length);
        mergeSort(left);
        mergeSort(right);
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
