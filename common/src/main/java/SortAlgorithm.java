import java.util.Arrays;

/**
 * @author huangshiwei on 2021-04-08
 */
@SuppressWarnings("ALL")
public class SortAlgorithm {

    public static void mergeSort(int[] array, int start, int end){
        System.out.println("Start mergeSort():: [" + start + ", " + end + "]");
        if (start >= end){
            return;
        }
        int middle = (start + end)/2;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);
        merge(array, start, end, middle);
        System.out.println("End mergeSort():: [" + start + ", " + end + "]" + ", array="+Arrays.toString(array));
    }

    public static void merge(int[] array, int start, int end, int middle){
        System.out.println("Start merge():: [" + start + ", " + end + "]" + ", middle="+middle );
        int[] sorted = new int[end - start + 1];
        int first = start; // index of first half array
        int second = middle + 1; // index of second half array
        int current = 0; // index of sorted array

        //compare the beginning elements of first and second half arrays
        //pick the smaller element and save it into the new sorted array
        while (current < sorted.length){
            if (first <= middle && second <= end) {
                if (array[first] <= array[second]) {
                    sorted[current] = array[first];
                    first++;
                } else {
                    sorted[current] = array[second];
                    second++;
                }
            }else if (first > middle){
                sorted[current] = array[second];
                second++;
            }else{
                sorted[current] = array[first];
                first++;
            }
            current++;
        }

        //copy the sorted array into the original array
        current = 0;
        while (current < sorted.length){
            array[start + current] = sorted[current];
            current++;
        }

        System.out.println("End merge():: [" + start + ", " + end + "]" + ", middle="+middle + ", array=" + Arrays.toString(array) + ", sorted=" + Arrays.toString(sorted));
    }




    public static void quickSort(int[] array, int start, int end){
        System.out.println("Start quickSort():: [" + start + ", " + end + "]");
        if (start >= end){
            System.out.println("End quickSort():: [" + start + ", " + end + "]");
            return;
        }
        int pick = partition(array, start, end);
        quickSort(array, start, pick - 1);
        quickSort(array, pick + 1, end);
        System.out.println("End quickSort():: [" + start + ", " + end + "]");
    }

    public static int partition(int[] array, int start, int end){
        int pickedNumber = array[end];
        int left = start - 1; // last index of left side array, i.e., array of numbers smaller than the picked one
        int current = start;
        while (current <= end){
            if (array[current] <= pickedNumber){
                left++;
                int temp = array[left];
                array[left] = array[current];
                array[current] = temp;
            }
            current++;
        }
        return left;
    }
}
