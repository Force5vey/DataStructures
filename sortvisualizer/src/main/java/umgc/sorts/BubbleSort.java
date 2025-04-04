package umgc.sorts;

import umgc.PrimaryController;

public class BubbleSort
{
    public static void sort(int[] arr) throws InterruptedException
    {
        boolean swapped;
        do{
            swapped = false;
            for(int i = 0; i < arr.length -1; i++)
            {
                if(arr[i] > arr[i +1])
                {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swapped = true;
                }
                PrimaryController.displayArray(arr);
                Thread.sleep(10); // Adjust or remove for animation speed
            }
        } while(swapped);
    }
}
