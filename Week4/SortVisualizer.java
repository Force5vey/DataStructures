import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SortVisualizer
{
    public static void main(String[] args) throws InterruptedException
    {
        int[] array = new Random().ints(30, 1, 50).toArray();
        bubbleSort(array);

        Thread.sleep(1500);

        int[] array2 = new Random().ints(30, 1, 100).toArray();
        insertionSort(array2);

        Thread.sleep(1500);

        int[] array3 = new Random().ints(30, 1, 100).toArray();
        selectionSort(array3);

        Thread.sleep(1500);

        int[] array4 = new Random().ints(100, 1, 100).toArray();
        mergeSort(array4);

        int[] array5 = new Random().ints(600, 1, 100).toArray();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new ParallelMergeSort(array5, 0, array5.length - 1));

        Thread.sleep(1500);
        int[] array6 = new Random().ints(30, 1, 100).toArray();
        quickSort(array6);
    }

    public static void quickSort(int[] list) throws InterruptedException
    {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) throws InterruptedException
    {
        if (last > first)
        {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    private static int partition(int[] list, int first, int last) throws InterruptedException
    {
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low)
        {
            while (low <= high && list[low] <= pivot)
            {
                low++;
            }

            while (low <= high && list[high] > pivot)
            {
                high--;
            }

            if (high > low)
            {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;

                displayArray(list);
                Thread.sleep(20);
            }
        }

        while (high > first && list[high] >= pivot)
        {
            high--;
        }

        if (pivot > list[high])
        {
            list[first] = list[high];
            list[high] = pivot;

            displayArray(list);
            Thread.sleep(20);

            return high;
        } else
        {
            return first;
        }
    }

    public static void mergeSort(int[] arr) throws InterruptedException
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
            displayArray(arr);
            //Thread.sleep(10);        
        }

        while (i < n1)
        {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        displayArray(arr);
        //Thread.sleep(10);

        while (j < n2)
        {
            arr[k] = rightArray[j];
            j++;
            k++;

            displayArray(arr);
            //Thread.sleep(10);
        }
    }

    static void selectionSort(int[] arr) throws InterruptedException
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[j] < arr[minIndex])
                {
                    minIndex = j;
                }
                displayArray(arr);
                // Thread.sleep(10);
            }

            if (minIndex != i)
            {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            displayArray(arr);
        }
    }

    static void insertionSort(int[] list) throws InterruptedException
    {
        for (int i = 1; i < list.length; i++)
        {
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--)
            {
                list[k + 1] = list[k];

                displayArray(list);
                // Thread.sleep(10);
            }
            list[k + 1] = currentElement;
        }
    }

    static void bubbleSort(int[] arr) throws InterruptedException
    {
        boolean swapped;
        do
        {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++)
            {
                if (arr[i] > arr[i + 1])
                {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
                displayArray(arr);
                // Thread.sleep(10);
            }
        } while (swapped);
    }

    static void displayArray(int[] arr)
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int val : arr)
        {
            System.out.printf("%2d: %s%n", val, "#".repeat(val));
        }
    }

    static class ParallelMergeSort extends RecursiveAction
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
    }
}
