import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author huangshiwei on 2021-04-08
 */
public class SortAlgorithmTest {

    @Test
    public void testMergeSort(){
        int[] array = new int[]{2, 4, 1, 6, 8, 5, 3, 9, 7};
        SortAlgorithm.mergeSort(array, 0, array.length - 1);
        System.out.println("result="+ Arrays.toString(array));
    }

    @Test
    public void testQuickSort(){
        int[] array = new int[]{2, 4, 1, 6, 8, 5, 3, 9, 7};
        SortAlgorithm.quickSort(array, 0, array.length - 1);
        System.out.println("result="+Arrays.toString(array));
    }
}
