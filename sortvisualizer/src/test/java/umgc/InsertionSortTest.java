package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.InsertionSort;

public class InsertionSortTest
{
    @Test
    void testInsertionSort_simple() throws InterruptedException
    {
        int[] arr =
        { 5, 2, 4, 6, 1 };
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 4, 5, 6 }, arr);
    }

    @Test
    void testInsertionSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 1, 2, 3, 4, 5 };
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testInsertionSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 5, 4, 3, 2, 1 };
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }
}
