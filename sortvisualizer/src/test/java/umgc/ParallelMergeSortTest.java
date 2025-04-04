package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.ParallelMergeSort;

import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortTest
{
    @Test
    void testParallelMergeSort_simple()
    {
        int[] arr =
        { 5, 2, 3, 1, 4 };
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new ParallelMergeSort(arr, 0, arr.length - 1));
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testParallelMergeSort_alreadySorted()
    {
        int[] arr =
        { 1, 2, 3, 4 };
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new ParallelMergeSort(arr, 0, arr.length - 1));
        assertArrayEquals(new int[]
        { 1, 2, 3, 4 }, arr);
    }

    @Test
    void testParallelMergeSort_reverseSorted()
    {
        int[] arr =
        { 5, 4, 3, 2, 1 };
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new ParallelMergeSort(arr, 0, arr.length - 1));
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }
}
