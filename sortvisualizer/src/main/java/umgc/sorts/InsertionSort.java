package umgc.sorts;

import umgc.PrimaryController;

public class InsertionSort
{
    public static void sort(int[] arr) throws InterruptedException
    {
        for (int i = 1; i < arr.length; i++)
        {
            int currentElement = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k] > currentElement; k--)
            {
                arr[k + 1] = arr[k];
                PrimaryController.displayArray(arr);
                Thread.sleep(10);
            }
            arr[k + 1] = currentElement;
        }
    }
}
