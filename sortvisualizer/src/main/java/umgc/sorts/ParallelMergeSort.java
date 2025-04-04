package umgc.sorts;

import java.util.concurrent.RecursiveAction;
import umgc.PrimaryController;

public class ParallelMergeSort extends RecursiveAction
{
    private static final int THRESHOLD = 500;
    private int[] arr;
    private int left;
    private int right;

    public ParallelMergeSort(int[] arr, int left, int right)
    {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute()
    {
        if (right - left < THRESHOLD)
        {
            try
            {
                mergeSort(arr, left, right);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        } else
        {
            int mid = (left + right) / 2;
            ParallelMergeSort leftTask = new ParallelMergeSort(arr, left, mid);
            ParallelMergeSort rightTask = new ParallelMergeSort(arr, mid + 1, right);
            invokeAll(leftTask, rightTask);

            try
            {
                merge(arr, left, mid, right);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void mergeSort(int[] arr, int left, int right) throws InterruptedException
    {
        if (left < right)
        {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) throws InterruptedException
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++)
        {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++)
        {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2)
        {
            if (leftArray[i] <= rightArray[j])
            {
                arr[k] = leftArray[i];
                i++;
            } else
            {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
            // If displayed from multiple threads, it can flicker a lot:
            PrimaryController.displayArray(arr);
        }

        while (i < n1)
        {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
        PrimaryController.displayArray(arr);
    }
}
