import java.util.Scanner;

public class LongestSameNumber
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int index = 0;

        // current run trackers
        int currentNumber = -1;
        int currentCount = 0;
        int currentStartIndex = 0;

        // longest run trackers
        int longestNumber = -1;
        int longestCount = 0;
        int longestStartIndex = 0;

        System.out.println("Enter a sequence of integers, end with 0:");

        outerLoop: while (true)
        {
            //Read full line as string so checks and parsing can be performed
            String line = scanner.nextLine().trim();

            if (line.isEmpty())
            {
                System.out.println("Continue entering numbers, or enter 0 to finish:");
                continue;
            }

            //tokenize based on whitespace
            String[] tokens = line.split("\\s+");

            for (String token : tokens)
            {
                // only digits
                if (!token.matches("\\d+"))
                {
                    System.out.println("Ignoring non-digit token: \"" + token + "\"");
                    continue;
                }

                int num = Integer.parseInt(token);

                if (num == 0)
                {
                    break outerLoop;
                }

                if (num == currentNumber)
                {
                    currentCount++;
                } else
                {
                    currentNumber = num;
                    currentCount = 1;
                    currentStartIndex = index;
                }

                // is this the longest so far?
                if (currentCount > longestCount)
                {
                    longestCount = currentCount;
                    longestNumber = currentNumber;
                    longestStartIndex = currentStartIndex;
                }

                index++;
            }
        }

        scanner.close();

        if (longestCount > 0)
        {
            System.out.println("The longest same-number sequence starts at index " + longestStartIndex + " with "
                    + longestCount + " values of " + longestNumber + ".");
        } else
        {
            System.out.println("No valid numbers were processed.");
        }
    }
}