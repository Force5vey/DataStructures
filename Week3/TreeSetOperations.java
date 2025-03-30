import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeSetOperations
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        TreeSet<String> set1 = readSetFromInput(input, "Enter strings for the first set: ");
        TreeSet<String> set2 = readSetFromInput(input, "Enter strings for the second set: ");

        TreeSet<String> union = union(set1, set2);
        TreeSet<String> difference = difference(set1, set2);
        TreeSet<String> intersection = intersection(set1, set2);
        TreeSet<String> symmetricDiff = symmetricDifference(set1, set2);

        System.out.println("The union of the two sets is " + formatForDisplay(union));
        System.out.println("The difference of the two sets is " + formatForDisplay(difference));
        System.out.println("The intersection of the two sets is " + formatForDisplay(intersection));
        System.out.println("The symmetric difference of the two sets is " + formatForDisplay(symmetricDiff));
    }

    private static TreeSet<String> readSetFromInput(Scanner input, String prompt)
    {
        System.out.print(prompt);
        String line = input.nextLine();
        String[] words = line.split("\\s+");

        TreeSet<String> set = new TreeSet<>();
        for (String word : words)
        {
            set.add(word.toLowerCase());
        }
        return set;
    }

    private static TreeSet<String> union(TreeSet<String> set1, TreeSet<String> set2)
    {
        TreeSet<String> result = new TreeSet<>(set1);
        result.addAll(set2);
        return result;
    }

    private static TreeSet<String> difference(TreeSet<String> set1, TreeSet<String> set2)
    {
        TreeSet<String> result = new TreeSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    private static TreeSet<String> intersection(TreeSet<String> set1, TreeSet<String> set2)
    {
        TreeSet<String> result = new TreeSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    private static TreeSet<String> symmetricDifference(TreeSet<String> set1, TreeSet<String> set2)
    {
        TreeSet<String> union = union(set1, set2);
        TreeSet<String> intersection = intersection(set1, set2);

        TreeSet<String> symDiff = new TreeSet<>(union);
        symDiff.removeAll(intersection);
        return symDiff;
    }

    private static String formatForDisplay(TreeSet<String> set)
    {
        return set.stream().map(TreeSetOperations::toTitleCase).collect(Collectors.toList()).toString();
    }

    private static String toTitleCase(String word)
    {
        if (word.isEmpty())
        {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

}
