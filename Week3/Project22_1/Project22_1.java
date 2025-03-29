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
