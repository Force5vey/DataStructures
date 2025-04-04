package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.QuickSort;

public class QuickSortTest
{
    @Test
    void testQuickSort_simple() throws InterruptedException
    {
        int[] arr =
        { 3, 1, 2, 5, 4 };
        QuickSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testQuickSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 1, 2, 3, 4, 5 };
        QuickSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testQuickSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 5, 4, 3, 2, 1 };
        QuickSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }
}
