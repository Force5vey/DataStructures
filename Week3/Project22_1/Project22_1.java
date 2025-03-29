package Project22_1;

import java.util.Scanner;

public class Project22_1
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.print("Enter a string: ");
        String userInput = sc.nextLine();
        userInput.toLowerCase();

        // -- Naive Approach with String Concatenation
        long startTime = 0, endTime = 0;
        startTime = System.nanoTime();
        String naiveResult = findMaxIncreasingNaive(userInput);
        endTime = System.nanoTime();
        double naiveTimeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println();
        System.out.println("Naive Approach:");
        System.out.println("Result: " + naiveResult);
        System.out.printf("Runtime: %,.3f ms%n", naiveTimeMs);

        // -- StringBuilder approach
        startTime = System.nanoTime();
        String builderResult = findMaxIncreasingBuilder(userInput);
        endTime = System.nanoTime();
        double builderTimeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println();
        System.out.println("StringBuilder Approach:");
        System.out.println("Result: " + builderResult);
        System.out.printf("Runtime: %,.3f ms%n", builderTimeMs);
    }

    public static String findMaxIncreasingNaive(String userString)
    {
        String currentSubstring = "";
        String maxSubstring = "";

        char prevChar = 0;

        for (char c : userString.toCharArray())
        {
            if (c > prevChar)
            {
                currentSubstring += c;
            } else
            {
                if (currentSubstring.length() > maxSubstring.length())
                {
                    maxSubstring = currentSubstring;
                }

                currentSubstring = "";
                currentSubstring += c;

            }

            prevChar = c;
        }

        if (currentSubstring.length() > maxSubstring.length())
        {
            maxSubstring = currentSubstring;
        }

        return maxSubstring;
    }

    public static String findMaxIncreasingBuilder(String userString)
    {
        StringBuilder current = new StringBuilder();
        String maxSubstring = "";
        char prevChar = 0;

        for (char c : userString.toCharArray())
        {
            if (c > prevChar)
            {
                current.append(c);
            } else
            {
                if (current.length() > maxSubstring.length())
                {
                    maxSubstring = current.toString();
                }
                current.setLength(0);
                current.append(c);
            }
            prevChar = c;
        }

        if (current.length() > maxSubstring.length())
        {
            maxSubstring = current.toString();
        }
        return maxSubstring;
    }
}