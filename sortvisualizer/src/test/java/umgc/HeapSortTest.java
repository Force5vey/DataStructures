package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.HeapSort;

public class HeapSortTest
{
    @Test
    void testHeapSort_simple() throws InterruptedException
    {
        Integer[] arr =
        { -44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53 };

        HeapSort.heapSort(arr);

        assertArrayEquals(new Integer[]
        { -44, -5, -4, -3, 0, 1, 1, 2, 3, 3, 4, 5, 53 }, arr);

    }
}
