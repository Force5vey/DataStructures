package umgc.models;

public class TreeNode<E>
{
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E e)
    {
        element = e;
    }
}
