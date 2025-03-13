(Perform set operations on priority queues)

Write a program that creates two priority queues for integers. Prompt the user to enter two lines of integers.

Read each line of integers as a string and extract the integers from the string and add the integers to the two queues, respectively. Display their union, difference, and intersection. Note that the priority queue can have duplicates. The union of two priority queues may have duplicates. Display all output in increasing order.

Sample Run

Enter integers for priority queue 1: 1 9 2 30 21 4 1
Enter integers for priority queue 2: 12 19 2 10 12 1
The union of the two priority queues is 
1 1 1 2 2 4 9 10 12 12 19 21 30 
The difference of the two priority queues is 
4 9 21 30 
The intersection of the two priority queues is 
1 1 2



####


ok, had to do a short refresher on the set _[things]_ for union difference and intersection. union makes sense and with priority queues duplicates are allowed. For difference it isn't unique to both, it is what is in set a that isn't in set b(this allows for unique numbers in b to not be shown in the difference because it is only from A). Further, for intersection my understanding is min(n, m). so for the sample output, intersection should be 1,2. since they give 1,1,2...I amgoing to make the program preserve teh count of set a so much as it exists in set b.


My logical steps:

Read input from user.
This will utilize nextint from scanner. a robust program would do input checking and reprompt but for this exercise I will assume proper input and simply catch errors and abort if found.

Parse input. as I read integers from the input I will put them into a PriorityQueue<Integer>. This will be two distinct queues, Set a and Set b.

comput union. this is all numbers from both sets, priority queue will automatically sort so should be able to add set be and a to a union queue and then print those results.

Compute Difference.

Create a copy of set a. then iterate through a removing anything that exists in set b. then print those results.

Compute the intersection. Intersection as I know it would not include duplicates, only one of each number that is unique to A that isn't in set b. However, the sample in the problem instructions shows 1,1,2 so I will keep the number of iterations in set a of a given value so long as it exists in set b.

Display results.


####

Dev Notes.

I named my project PriorityQueue...and had some initial issues because it didn't click that I named my class the same as the java library. The compiler was thinking I was trying to paramaterize the class, I renamed my project (and thus main class) to PriorityQueueProject and then that resolved the issues with creating a new PriorityQueue object.


Working through the input. I am running into a bit of trouble because between intermediate programming last semester and this class (my only java use is in classes) I have been doing A LOT of C# and C# is quite similar to java but just different enough to mess me up.

I initially started gathering next ints, then reread the problem instructions, and pulled in the full line as a string and am using String.split(" ") to get an array of String then parsing the values while adding to the priority queue. I would normally validate input but for the sake of this project I am just doing a try and if any number format exception is found I am printing the error and closing the program. This method is very strict, it errors out even if there are two spaces between numbers. 

With this, I have a 'debug' print of the values when I enter them. I can enter the values then with the priority queue printing to screen and they are auto sorted.

Moving to the union, this is straight forward, just offer(value) each value of setB into setA, and it will sort and print all values.

I have union printing, easy peasy, just add set a and set b to a new queue and it sorts and adds values and I can print each. I used a new PriorityQueue so I could maintain the values in each set. 

moving on to the difference. I will need a copy of setA (so I don't lose the values) and then remove everything that isn't also in setB. I'm going to try out:

iterate though setA, do a check if setB contains that value, if it does, remove from set a copy, when finished I can print all values in set a copy.

Ran into a problem with my initial try where I was iterating over setA and if it is in setB it would remove from teh copy of setA: setADifference. But since PriorityQueue doesn't have set order like List, this is creating unexpected behavior and ultimately an empty list for me. I am going to utilize an iterator and go from there. Working through the Iterator, much like the text examples handled the issue and is able to handle the removes while iterating through the queue

Moving on to display the intersection.

This, according the sample output, will display all values (even if duplicates) of set a if it is also in set b.

I created a copy of the original setA again. Again using an iterator object, I loop through the copy set. if seB does NOT contain the item (at least one instance) then it is removed, this handles duplicates correctly due to it iterating throgh the entire list.


Submitted my initial rough in to the auto-grader. It passed all tests.

Now, since this is a discussion post, I want to put a little additional effort than just getting green on tests. As you see in my code from my just first show step through, it is repeating a lot that could possibly be made more efficient. Also, you will notice as I went I updated how I was making the copies etc. So, going to work through this a bit, clean it up, standardize some of the practices and check out the text and the java docs to ensure I am using available methods to make it better:

<First Shot Rough Draft (passes tests)>

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



 a few things I'm going to improve from the rough draft:

Better handling of input, use regex to pick out numbers and not rely on strictly single space.
Reduce redundant loops and copies







