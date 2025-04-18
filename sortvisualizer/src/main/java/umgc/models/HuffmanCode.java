package umgc.models;

import umgc.models.Heap;

public class HuffmanCode
{
    public static class Tree implements Comparable<Tree>
    {
        public Node root;

        public Tree(Tree t1, Tree t2)
        {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        public Tree(int weight, char element)
        {
            root = new Node(weight, element);
        }

        @Override
        public int compareTo(Tree t)
        {
            // Min-heap: smaller weight = higher priority
            return Integer.compare(this.root.weight, t.root.weight);
        }

        public static class Node
        {
            public char element;
            public int weight;
            public Node left, right;
            public String code = "";

            public Node()
            {
            }

            public Node(int weight, char element)
            {
                this.weight = weight;
                this.element = element;
            }
        }
    }

    public static int[] getCharacterFrequency(String text)
    {
        int[] counts = new int[256];
        for (int i = 0; i < text.length(); i++)
            counts[(int) text.charAt(i)]++;
        return counts;
    }

    public static Tree getHuffmanTree(int[] counts)
    {
        Heap<Tree> heap = new Heap<>();
        for (int i = 0; i < counts.length; i++)
        {
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char) i));
        }
        while (heap.getSize() > 1)
        {
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();
            heap.add(new Tree(t1, t2));
        }
        return heap.getSize() == 1 ? heap.remove() : null;
    }

    public static String[] getCode(Tree.Node root)
    {
        if (root == null)
            return null;
        String[] codes = new String[256];
        assignCode(root, codes);
        return codes;
    }

    private static void assignCode(Tree.Node root, String[] codes)
    {
        if (root.left != null)
        {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);
            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        } else
        {
            codes[(int) root.element] = root.code;
        }
    }
}
