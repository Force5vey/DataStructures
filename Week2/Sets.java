package Week2;

import java.util.*;

public class Sets
{
    public static void main(String[] args)
    {
        Set<A> set = new LinkedHashSet<>();
        set.add(new A());
        set.add(new A(3));
        set.add(new A(2));
        set.add(new A(5));
        System.out.println(set);

        //
        Set<String> newSet = new LinkedHashSet<>();

        newSet.add("London");
        newSet.add("Paris");
        newSet.add("New York");
        newSet.add("San Francisco");
        newSet.add("Beijing");
        newSet.add("New York");

        System.out.println();
        System.out.println(newSet);

        //

        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        set1.add("New York");
        LinkedHashSet<String> set2 = (LinkedHashSet<String>) (set1.clone());
        set1.add("Atlanta");
        set2.add("Dallas");

        System.out.println();
        System.out.println(set2);

        LinkedHashSet<String> set3 = new LinkedHashSet<>();
        set3.add("New York");
        LinkedHashSet<String> set4 = set3;
        set3.add("Atlanta");
        set4.add("Dallas");

        System.out.println();
        System.out.println(set4);

        //

        HashSet<String> set5 = new HashSet<>();
        set5.add("red");

        Set<String> set6 = (HashSet<String>) (set5.clone());
        

        System.out.println(set6);
    }
}

class A
{
    int r = 1;

    public A()
    {
    }

    public A(int val)
    {
        this.r = val;
    }

    public String toString()
    {
        return r + "";
    }

    public int hashCode()
    {
        return r;
    }
}