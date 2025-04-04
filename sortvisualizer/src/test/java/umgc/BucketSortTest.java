package umgc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import umgc.sorts.BucketSort;

public class BucketSortTest
{
    @Test
    void testBucketSort_simple() throws InterruptedException
    {
        int[] arr =
        { 29, 10, 14, 37, 13 };
        BucketSort.sort(arr);
        assertArrayEquals(new int[]
        { 10, 13, 14, 29, 37 }, arr);
    }

    @Test
    void testBucketSort_alreadySorted() throws InterruptedException
    {
        int[] arr =
        { 2, 4, 6, 8, 10 };
        BucketSort.sort(arr);
        assertArrayEquals(new int[]
        { 2, 4, 6, 8, 10 }, arr);
    }

    @Test
    void testBucketSort_reverseSorted() throws InterruptedException
    {
        int[] arr =
        { 10, 8, 6, 4, 2 };
        BucketSort.sort(arr);
        assertArrayEquals(new int[]
        { 2, 4, 6, 8, 10 }, arr);
    }
}
