import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Collections;

public class Sandbox
{
    public static void main(String[] args)
    {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Write Documentation", 3));
        tasks.add(new Task("Setup project", 2));
        tasks.add(new Task("Write code", 1));
        tasks.add(new Task("Urgent Bug Fix", 1));
        tasks.add(new Task("Review code", 4));

        Collections.sort(tasks);

        System.out.println("Sorted Tasks:");
        printTasks(tasks);

        int foundIndex = binarySearch(tasks, 3);
        if (foundIndex != -1)
        {
            System.out.println("\nFound task at index " + foundIndex + ": " + tasks.get(foundIndex));
        } else
        {
            System.out.println("\nTask with given priority not found.");
        }
    }

    public static int binarySearch(ArrayList<Task> tasks, int priority)
    {
        int low = 0;
        int high = tasks.size() - 1;

        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            Task midTask = tasks.get(mid);

            if (midTask.priority == priority)
            {
                return mid;
            } else if (midTask.priority < priority)
            {
                low = mid + 1;
            } else
            {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void printTasks(ArrayList<Task> tasks)
    {
        for (Task task : tasks)
        {
            System.out.println(task);
        }
    }
}

class Task implements Comparable<Task>
{
    String name;
    int priority;

    public Task(String name, int priority)
    {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString()
    {
        return "[" + priority + "] " + name;
    }

    @Override
    public int compareTo(Task other)
    {
        return Integer.compare(this.priority, other.priority);
    }
}