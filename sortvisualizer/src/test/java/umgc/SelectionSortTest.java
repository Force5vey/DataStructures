package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.SelectionSort;

public class SelectionSortTest
{
    @Test
    void testSelectionSort_simple() throws InterruptedException
    {
        int[] arr =
        { 3, 1, 4, 2 };
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4 }, arr);
    }

    @Test
    void testSelectionSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 1, 2, 3, 4, 5 };
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testSelectionSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 5, 4, 3, 2, 1 };
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }
}
