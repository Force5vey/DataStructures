package umgc;
 
import org.junit.jupiter.api.Test;

import umgc.sorts.MergeSort;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest
{

    @Test
    public void testAssertTrueTest()
    {
        assertTrue(1 < 3);
    }

    @Test 
    public void mergeSortTest()
    {
        MergeSort mS = new MergeSort();

        assertEquals(1, mS.mergeSort(2));
    }

}
