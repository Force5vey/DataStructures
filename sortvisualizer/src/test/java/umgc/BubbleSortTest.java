package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.BubbleSort;

public class BubbleSortTest
{
    @Test
    void testBubbleSort_simple() throws InterruptedException
    {
        int[] arr =
        { 5, 3, 1, 4, 2 };
        BubbleSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testBubbleSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 1, 2, 3, 4, 5 };
        BubbleSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }

    @Test
    void testBubbleSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 5, 4, 3, 2, 1 };
        BubbleSort.sort(arr);
        assertArrayEquals(new int[]
        { 1, 2, 3, 4, 5 }, arr);
    }
}
