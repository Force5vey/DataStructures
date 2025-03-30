// Code copied from discussion <class mate> 
// just checking it out, made no changes so far.
public class CustomLinkedList<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E>
    {
        E element;
        Node<E> next;

        public Node(E element)
        {
            this.element = element;
        }
    }

    public void addFirst(E e)
    {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null)
        {
            tail = head;
        }
    }

    public void addLast(E e)
    {
        Node<E> newNode = new Node<>(e);
        if (tail == null)
        {
            head = tail = newNode;
        } else
        {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E removeFirst()
    {
        if (size == 0)
            return null;
        else
        {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null)
                tail = null;
            return temp.element;
        }
    }

    public int size()
    {
        return size;
    }

    public static void main(String[] args)
    {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.addFirst("Hello");
        list.addLast("World");
        System.out.println("First element: " + list.removeFirst());
        System.out.println("New first element: " + list.head.element);
    }
}