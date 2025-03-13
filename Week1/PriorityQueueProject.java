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

        // display union

        PriorityQueue<Integer> setAUnion = new PriorityQueue<Integer>();
        for (Integer num : setA)
        {
            setAUnion.offer(num);
        }

        for (Integer val : setB)
        {
            setAUnion.offer(val);
        }

        System.out.println("Set A Union: ");
        while (setAUnion.size() > 0)
        {
            System.out.print(setAUnion.remove() + " ");
        }

        System.out.println("\nSet A Difference: ");

        PriorityQueue<Integer> setADifference = new PriorityQueue<Integer>();
        for (Integer num : setA)
        {
            setADifference.offer(num);
        }

        for (Integer val : setA)
        {
            if (setB.contains(val))
            {
                setADifference.remove(val);
            }
        }

        while (setADifference.size() > 0)
        {
            System.out.println(setADifference.remove() + " ");
        }

    }

}
