package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.MergeSort;

public class MergeSortTest
{
    @Test
    void testMergeSort_simple() throws InterruptedException
    {
        int[] arr =
        { 10, 5, 2, 7, 3 };
        MergeSort.sort(arr);
        assertArrayEquals(new int[]
        { 2, 3, 5, 7, 10 }, arr);
    }

    @Test
    void testMergeSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 1, 2, 3, 4 };
        MergeSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4 }, arr);
    }

    @Test
    void testMergeSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 9, 7, 5, 3, 1 };
        MergeSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 3, 5, 7, 9 }, arr);
    }

}
