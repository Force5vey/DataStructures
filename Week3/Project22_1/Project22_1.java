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

        StringAnalyzer analyzer = new StringAnalyzer();

        long startTime = 0, endTime = 0;

        startTime = System.nanoTime();

        String subString = analyzer.getConsecutiveSubString(userInput);

        endTime = System.nanoTime();

        System.out.print("Maximum consecutive increasingly ordered substring is ");
        System.out.println(subString);

        System.out.println();

        System.out.println("Runtime: " + (endTime - startTime) / 1000 + " microseconds");
    }
}

class StringAnalyzer
{
    public String getConsecutiveSubString(String userString)
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
}
