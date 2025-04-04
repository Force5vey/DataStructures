package umgc.sorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import umgc.PrimaryController;

public class BucketSort
{

    public static void sort(int[] arr) throws InterruptedException
    {
        int minVal = arr[0];
        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++)
        {
            if (arr[i] < minVal)
            {
                minVal = arr[i];
            } else if (arr[i] > maxVal)
            {
                maxVal = arr[i];
            }
        }

        // Can also be a fixed count if we know the data range
        // This will do a 'general / simple # of buckets based on size
        int bucketCount = Math.max((maxVal - minVal) / 10 + 1, 1);

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++)
        {
            buckets.add(new ArrayList<>());
        }

        //Scatter
        for (int value : arr)
        {
            int bucketIndex = (value - minVal) / 10;
            buckets.get(bucketIndex).add(value);
        }

        int index = 0;
        for (List<Integer> bucket : buckets)
        {
            insertionSort(bucket);

            for (int val : bucket)
            {
                arr[index++] = val;

                PrimaryController.displayArray(arr);
                Thread.sleep(10);
            }
        }
    }

    private static void insertionSort(List<Integer> bucket)
    {
        for (int i = 1; i < bucket.size(); i++)
        {
            int current = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > current)
            {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }

            bucket.set(j + 1, current);
        }
    }
}
