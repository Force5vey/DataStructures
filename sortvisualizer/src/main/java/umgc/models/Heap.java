package umgc.models;

import java.util.ArrayList;
import java.util.*;

public class Heap<E>
{
    private ArrayList<E> list = new ArrayList<>();
    private Comparator<? super E> c;

    public Heap()
    {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    public Heap(Comparator<E> c)
    {
        this.c = c;
    }

    public Heap(E[] objects)
    {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
        {
            add(objects[i]);
        }
    }

    public void add(E newObject)
    {
        list.add(newObject);
        int currentIndex = list.size() - 1;

        while (currentIndex > 0)
        {
            int parentIndex = (currentIndex - 1) / 2;

            if (c.compare(list.get(currentIndex), list.get(parentIndex)) > 0)
            {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else
            {
                break;
            }

            currentIndex = parentIndex;
        }
    }

    public E remove()
    {
        if (list.size() == 0)
        {
            return null;
        }

        E removeObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size())
        {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            if (leftChildIndex >= list.size())
            {
                break;
            }

            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size())
            {
                if (c.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0)
                {
                    maxIndex = rightChildIndex;
                }
            }

            if (c.compare(list.get(currentIndex), list.get(maxIndex)) < 0)
            {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
            {
                break;
            }
        }
        return removeObject;
    }

    public int getSize()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.size() == 0;
    }
}
