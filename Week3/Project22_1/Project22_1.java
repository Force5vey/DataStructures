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

        String subString = analyzer.getConsecutiveSubString(userInput);

        System.out.print("Maximum consecutive increasingly ordered substring is ");
        System.out.println(subString);

        // Prompt for input
        // - ignoring edge cases, just string, ignore anything after any spaces
        // Variables
        // - store string
        // - store substring that is being built during the traversall
        // - store substring - that is the max one until a longer one is found
        // Traverse the string
        // - compare current to previous
        // - if greater than previous (lexi/alphabetical (will force all to lower)) add to substring (current)
        // - if not, compare to the max substring, if longer, replace
        // - reset current and continue traversing
        // - final comparison at end against stored max substring

        // print results
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
            if (c >= prevChar)
            {
                currentSubstring += c;
            } else
            {
                if (currentSubstring.length() > maxSubstring.length())
                {
                    maxSubstring = currentSubstring;

                    currentSubstring = "";
                }
            }

            prevChar = c;
        }

        return maxSubstring;
    }
}
