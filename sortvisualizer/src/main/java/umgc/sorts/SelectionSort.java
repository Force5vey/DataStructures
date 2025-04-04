package umgc.sorts;

import umgc.PrimaryController;

public class SelectionSort
{
    public static void sort(int[] arr) throws InterruptedException
    {
        for (int i = 0; i < arr.length -1 ; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j <arr.length; j++)
            {
                if(arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
                PrimaryController.displayArray(arr);
                Thread.sleep(10);
            }

            if(minIndex != i)
            {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            PrimaryController.displayArray(arr);
        }
    }
}
