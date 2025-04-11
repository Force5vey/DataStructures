import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collection;
import java.lang.reflect.Array;

public class Exercise
{
    public static void main(String[] args)
    {
        new Exercise();
    }

    public Exercise()
    {
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
        System.out.print("Enter five numbers: ");
        Scanner input = new Scanner(System.in);
        double[] v = new double[5];
        for (int i = 0; i < 5; i++)
        {
            v[i] = input.nextDouble();
        }

        list.add(v[1]);
        list.add(v[2]);
        list.add(v[3]);
        list.add(v[4]);
        list.add(0, v[0]);
        list.add(2, 10.55);
        list.remove(3);

        ListIterator<Double> iterator1 = list.listIterator();
        System.out.print("The list in forward order: ");
        while (iterator1.hasNext())
        {
            System.out.print(iterator1.next() + " ");
        }

        ListIterator<Double> iterator2 = list.listIterator(list.size());
        System.out.print("\nThe list in backward order: ");
        while (iterator2.hasPrevious())
        {
            System.out.print(iterator2.previous() + " ");
        }

        input.close();
    }
}

interface MyList<E> extends Collection<E>
{
    public void add(int index, E e);

    public E get(int index);

    public int indexOf(Object e);

    public int lastIndexOf(Object e);

    public E remove(int index);

    public E set(int index, E e);

    @Override
    public default boolean add(E e)
    {
        add(size(), e);
        return true;
    }

    @Override
    public default boolean isEmpty()
    {
        return size() == 0;
    }

    @Override
    public default boolean remove(Object e)
    {
        int index = indexOf(e);
        if (index >= 0)
        {
            remove(index);
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public default boolean containsAll(Collection<?> c)
    {
        for (Object item : c)
        {
            if (!contains(item))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c)
    {
        for (E item : c)
        {
            add(item);
        }
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c)
    {
        boolean changed = false;
        for (Object item : c)
        {
            while (contains(item))
            {
                remove(item);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public default boolean retainAll(Collection<?> c)
    {
        boolean changed = false;
        Iterator<E> it = this.iterator();
        while (it.hasNext())
        {
            E element = it.next();
            if (!c.contains(element))
            {
                it.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public default Object[] toArray()
    {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this)
        {
            array[i++] = e;
        }
        return array;
    }

    @Override @SuppressWarnings("unchecked")
    public default <T> T[] toArray(T[] array)
    {
        if (array.length < size())
        {
            array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size());
        }

        int i = 0;
        for (E e : this)
        {
            array[i++] = (T) e;
        }

        while (i < array.length)
        {
            array[i++] = null;
        }
        return array;
    }
}

class TwoWayLinkedList<E> implements MyList<E>
{
    private Node<E> head, tail;
    private int size = 0;

    public TwoWayLinkedList()
    {
    }

    public TwoWayLinkedList(E[] objects)
    {
        for (E e : objects)
        {
            add(e);
        }
    }

    public E getFirst()
    {
        if (size == 0)
        {
            return null;
        } else
        {
            return head.element;
        }
    }

    public E getLast()
    {
        if (size == 0)
        {
            return null;
        } else
        {
            return tail.element;
        }
    }

    public void addFirst(E e)
    {
        Node<E> newNode = new Node<>(e);
        if (size == 0)
        {
            head = tail = newNode;
        } else
        {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e)
    {
        Node<E> newNode = new Node<>(e);
        if (size == 0)
        {
            head = tail = newNode;
        } else
        {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0)
        {
            addFirst(e);
        } else if (index == size)
        {
            addLast(e);
        } else
        {
            Node<E> newNode = new Node<>(e);
            Node<E> current = getNodeAtIndex(index);

            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;

            size++;
        }
    }

    public E removeFirst()
    {
        if (size == 0)
        {
            return null;
        } else
        {
            E temp = head.element;
            head = head.next;
            size--;
            if (head == null)
            {
                tail = null;
            } else
            {
                head.previous = null;
            }
            return temp;
        }
    }

    public E removeLast()
    {
        if (size == 0)
        {
            return null;
        } else if (size == 1)
        {
            E temp = tail.element;
            head = tail = null;
            size = 0;
            return temp;
        } else
        {
            E temp = tail.element;
            tail = tail.previous;
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        if (index == 0)
        {
            return removeFirst();
        } else if (index == size - 1)
        {
            return removeLast();
        } else
        {
            Node<E> current = getNodeAtIndex(index);
            E temp = current.element;

            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return temp;
        }
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return getNodeAtIndex(index).element;
    }

    private Node<E> getNodeAtIndex(int index)
    {
        if (index < size / 2)
        {
            Node<E> current = head;
            for (int i = 0; i < index; i++)
            {
                current = current.next;
            }
            return current;
        } else
        {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--)
            {
                current = current.previous;
            }
            return current;
        }
    }

    @Override
    public int indexOf(Object e)
    {
        int index = 0;
        for (Node<E> current = head; current != null; current = current.next)
        {
            if ((e == null && current.element == null) || (e != null && e.equals(current.element)))
            {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object e)
    {
        int index = size - 1;
        for (Node<E> current = tail; current != null; current = current.previous)
        {
            if ((e == null && current.element == null) || (e != null && e.equals(current.element)))
            {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public E set(int index, E e)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        Node<E> current = getNodeAtIndex(index);
        E old = current.element;
        current.element = e;
        return old;
    }

    public void clear()
    {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(Object e)
    {
        return indexOf(e) != -1;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        while (current != null)
        {
            result.append(current.element);
            current = current.next;
            if (current != null)
            {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<E> iterator()
    {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator()
    {
        return new LinkedListIterator(0);
    }

    public ListIterator<E> listIterator(int index)
    {
        return new LinkedListIterator(index);
    }

    private static class Node<E>
    {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e)
        {
            element = e;
        }
    }

    private class LinkedListIterator implements ListIterator<E>
    {
        private Node<E> cursor;
        private int index;
        private Node<E> lastReturned;

        public LinkedListIterator()
        {
            this(0);
        }

        public LinkedListIterator(int index)
        {
            if (index < 0 || index > size)
            {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            this.index = index;
            if (index == size)
            {
                cursor = null;
            } else
            {
                cursor = getNodeAtIndex(index);
            }
        }

        @Override
        public boolean hasNext()
        {
            return (cursor != null);
        }

        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }
            lastReturned = cursor;
            E e = cursor.element;
            cursor = cursor.next;
            index++;
            return e;
        }

        @Override
        public boolean hasPrevious()
        {
            if (cursor == null && size > 0)
            {
                return true;
            }
            return (cursor != null && cursor.previous != null);
        }

        @Override
        public E previous()
        {
            if (!hasPrevious())
            {
                throw new java.util.NoSuchElementException();
            }
            if (cursor == null)
            {
                cursor = tail;
                index = size - 1;
            } else
            {
                cursor = cursor.previous;
                index--;
            }
            lastReturned = cursor;
            return cursor.element;
        }

        @Override
        public int nextIndex()
        {
            return index;
        }

        @Override
        public int previousIndex()
        {
            return index - 1;
        }

        @Override
        public void remove()
        {
            if (lastReturned == null)
            {
                throw new IllegalStateException("No element to remove");
            }
            Node<E> nodeToRemove = lastReturned;
            Node<E> prevNode = nodeToRemove.previous;
            Node<E> nextNode = nodeToRemove.next;

            if (prevNode == null)
            {
                head = nextNode;
                if (head != null)
                {
                    head.previous = null;
                }
            } else
            {
                prevNode.next = nextNode;
            }

            if (nextNode == null)
            {
                tail = prevNode;
                if (tail != null)
                {
                    tail.next = null;
                }
            } else
            {
                nextNode.previous = prevNode;
            }

            size--;

            if (cursor == nodeToRemove)
            {
                cursor = nextNode;
            }
            lastReturned = null;
        }

        @Override
        public void set(E e)
        {
            if (lastReturned == null)
            {
                throw new IllegalStateException("No element to set");
            }
            lastReturned.element = e;
        }

        @Override
        public void add(E e)
        {
            Node<E> newNode = new Node<>(e);

            if (cursor == null)
            {
                if (size == 0)
                {
                    head = tail = newNode;
                } else
                {
                    tail.next = newNode;
                    newNode.previous = tail;
                    tail = newNode;
                }
            } else
            {
                Node<E> prevNode = cursor.previous;
                newNode.next = cursor;
                cursor.previous = newNode;
                newNode.previous = prevNode;

                if (prevNode == null)
                {
                    head = newNode;
                } else
                {
                    prevNode.next = newNode;
                }
            }
            size++;
            index++;
            lastReturned = null;
        }
    }
}
