package umgc.searches;

import umgc.models.TreeNode;

public class BST<E extends Comparable<E>> implements Iterable<E>
{
    protected TreeNode<E> root;
    protected int size = 0;

    public BST()
    {

    }

    @Override
    public java.util.Iterator<E> iterator()
    {
        return new BSTIterator();
    }

    public TreeNode<E> getRoot()
    {
        return root;
    }

    public boolean search(E e)
    {
        TreeNode<E> current = root;
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            } else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            } else
            {
                return true; // found
            }
        }
        return false;
    }

    public boolean insert(E e)
    {
        if (root == null)
        {
            root = new TreeNode<>(e);
        } else
        {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
            {
                if (e.compareTo(current.element) < 0)
                {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0)
                {
                    parent = current;
                    current = current.right;
                } else
                {
                    return false; // duplicate nodes not inserted
                }
            }
            if (e.compareTo(parent.element) < 0)
            {
                parent.left = new TreeNode<>(e);
            } else
            {
                parent.right = new TreeNode<>(e);
            }
        }
        size++;
        return true;
    }

    public boolean delete(E e)
    {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0)
            {
                parent = current;
                current = current.right;
            } else
            {
                break; // found
            }
        }

        if (current == null)
        {
            return false; // not found
        }

        // case: current has no left child
        if (current.left == null)
        {
            if (parent == null)
            {
                root = current.right;
            } else
            {
                if (e.compareTo(parent.element) < 0)
                {
                    parent.left = current.right;
                } else
                {
                    parent.right = current.right;
                }
            }
        } else
        {
            // case 2: current has a left child 

            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null)
            {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost)
            {
                parentOfRightMost.right = rightMost.left;
            } else
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    private class BSTIterator implements java.util.Iterator<E>
    {
        private java.util.Stack<TreeNode<E>> stack = new java.util.Stack<>();

        public BSTIterator()
        {
            TreeNode<E> current = root;
            while (current != null)
            {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        @Override
        public E next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }
            TreeNode<E> node = stack.pop();
            E result = node.element;

            if (node.right != null)
            {
                TreeNode<E> current = node.right;
                while (current != null)
                {
                    stack.push(current);
                    current = current.left;
                }
            }
            return result;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("remove() not supported. Use BST.delete() instead");
        }
    }
}
