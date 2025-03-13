import java.util.Scanner;
import java.util.*;

public class PriorityQueueProject
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        PriorityQueue<Integer> setA = new PriorityQueue<Integer>();
        PriorityQueue<Integer> setB = new PriorityQueue<>();

        System.out.print("Enter integers for priority queue 1: ");

        //First Set
        String input = sc.nextLine();
        String[] tokens = input.split(" ");

        try
        {
            for (String num : tokens)
            {
                setA.offer((Integer.parseInt(num)));
            }

        } catch (NumberFormatException ex)
        {
            System.out.println("[Error][Invalid Number][First Set]: " + ex.getMessage());
            System.exit(1);
        }

        System.out.print("Enter integers for priority queue 2: ");

        //Second Set
        input = sc.nextLine();
        tokens = input.split(" ");

        try
        {
            for (String num : tokens)
            {
                setB.offer((Integer.parseInt(num)));
            }

        } catch (NumberFormatException ex)
        {
            System.out.println("[Error][Invalid Number][Second Set]: " + ex.getMessage());
            System.exit(1);
        }

        // --- Union Values
        PriorityQueue<Integer> setAUnion = new PriorityQueue<Integer>();
        for (Integer num : setA)
        {
            setAUnion.offer(num);
        }

        for (Integer val : setB)
        {
            setAUnion.offer(val);
        }

        System.out.println("The union of the two priority queues is");
        while (setAUnion.size() > 0)
        {
            System.out.print(setAUnion.remove() + " ");
        }

        // --- Set DIFFERENCE 
        System.out.println("\nThe difference of the two priority queues is: ");

        PriorityQueue<Integer> setADifference = new PriorityQueue<>(setA);

        Iterator<Integer> iterator = setADifference.iterator();
        while (iterator.hasNext())
        {
            if (setB.contains(iterator.next()))
            {
                iterator.remove();
            }
        }

        while (!setADifference.isEmpty())
        {
            System.out.print(setADifference.poll() + " ");
        }

        // --- Set Intersection
        System.out.println("\nThe intersection of the two priroty queues is");

        PriorityQueue<Integer> setAIntersection = new PriorityQueue<>(setA);
        Iterator<Integer> iIterator = setAIntersection.iterator();

        while (iIterator.hasNext())
        {
            if (!setB.contains(iIterator.next()))
            {
                iIterator.remove();
            }
        }

        while (!setAIntersection.isEmpty())
        {
            System.out.print(setAIntersection.poll() + " ");
        }

    }

}
