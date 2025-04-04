package umgc.sorts;

import umgc.PrimaryController;

public class QuickSort
{
    public static void sort(int[] arr) throws InterruptedException
    {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int first, int last) throws InterruptedException
    {
        if (last > first)
        {
            int pivotIndex = partition(arr, first, last);
            quickSort(arr, first, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, last);
        }
    }

    private static int partition(int[] arr, int first, int last) throws InterruptedException
    {
        int pivot = arr[first];
        int low = first + 1;
        int high = last;

        while (high > low)
        {
            while (low <= high && arr[low] <= pivot)
            {
                low++;
            }
            while (low <= high && arr[high] > pivot)
            {
                high--;
            }

            if (high > low)
            {
                swap(arr, low, high);
                PrimaryController.displayArray(arr);
                Thread.sleep(10);
            }
        }

        while (high > first && arr[high] >= pivot)
        {
            high--;
        }

        if (pivot > arr[high])
        {
            arr[first] = arr[high];
            arr[high] = pivot;
            PrimaryController.displayArray(arr);
            Thread.sleep(10);
            return high;
        } else
        {
            return first;
        }
    }

    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
