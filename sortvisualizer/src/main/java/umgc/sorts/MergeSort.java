package umgc.sorts;

import umgc.PrimaryController;

public class MergeSort
{
    public static void sort(int[] arr) throws InterruptedException
    {
        mergeSort(arr, 0, arr.length - 1);
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
            PrimaryController.displayArray(arr);
            Thread.sleep(10);
        }

        while (i < n1)
        {
            arr[k] = leftArray[i];
            i++;
            k++;
            //    PrimaryController.displayArray(arr);
        }

        while (j < n2)
        {
            arr[k] = rightArray[j];
            j++;
            k++;
            // PrimaryController.displayArray(arr);
        }

        PrimaryController.displayArray(arr);
    }
}
