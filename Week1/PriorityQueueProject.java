import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Iterator;

public class PriorityQueueProject
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        PriorityQueue<Integer> setA = new PriorityQueue<>();
        PriorityQueue<Integer> setB = new PriorityQueue<>();

        // --- Read and validate user input ---
        System.out.print("Enter integers for priority queue 1: ");
        readValidNumbers(setA);

        System.out.print("Enter integers for priority queue 2: ");
        readValidNumbers(setB);

        // --- Compute and display set operations ---
        System.out.print("The union of the two priority queues is: ");
        PriorityQueue<Integer> setAUnion = new PriorityQueue<>(setA);
        setAUnion.addAll(setB);
        printQueue(setAUnion);

        System.out.print("\nThe difference of the two priority queues is: ");
        PriorityQueue<Integer> setADifference = new PriorityQueue<>(setA);
        removeMatchingElements(setADifference, setB);
        printQueue(setADifference);

        System.out.print("\nThe intersection of the two priority queues is: ");
        PriorityQueue<Integer> setAIntersection = new PriorityQueue<>(setA);
        retainMatchingElements(setAIntersection, setB);
        printQueue(setAIntersection);
    }

    private static void readValidNumbers(PriorityQueue<Integer> queue)
    {
        while (true)
        {
            String input = sc.nextLine().trim();
            String[] tokens = input.split("[^0-9]+"); //Split by any non-digit

            boolean validNumberFound = false;
            for (String token : tokens)
            {
                if (!token.isEmpty())
                {
                    try
                    {
                        queue.offer(Integer.parseInt(token));
                        validNumberFound = true;
                    } catch (NumberFormatException ignored)
                    {
                        // Ignoring non-number values
                    }
                }
            }

            if (validNumberFound)
            {
                break;
            } else
            {
                System.out.print("[ERROR] No valid numbers found. Please re-enter: ");
            }
        }
    }

    private static void removeMatchingElements(PriorityQueue<Integer> sourceQueue,
            PriorityQueue<Integer> comparisonQueue)
    {
        Iterator<Integer> iterator = sourceQueue.iterator();
        while (iterator.hasNext())
        {
            if (comparisonQueue.contains(iterator.next()))
            {
                iterator.remove();
            }
        }
    }

    private static void retainMatchingElements(PriorityQueue<Integer> sourceQueue,
            PriorityQueue<Integer> comparisonQueue)
    {
        Iterator<Integer> iterator = sourceQueue.iterator();
        while (iterator.hasNext())
        {
            if (!comparisonQueue.contains(iterator.next()))
            {
                iterator.remove();
            }
        }
    }

    private static void printQueue(PriorityQueue<Integer> queue)
    {
        while (!queue.isEmpty())
        {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }
}
