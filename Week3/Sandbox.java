package Week3;

public class Sandbox
{
    public static void main(String[] args)
    {
        getTime(1_000_000L);
        getTime(10_000_000L);
        getTime(100_000_000L);
        getTime(1_000_000_000L);
        getTime(10_000_000_000L);
    }

    public static void getTime(long n)
    {
        long startTime = System.currentTimeMillis();
        long k = 0;
        long b = 10;
        for (long i = 1; i <= n; i++)
        {
            k = k + 5;
            b += k / 2;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Execution time for n = " + n + " is " + (endTime - startTime) + " ms.");
        System.out.println("K: " + k);
        System.out.println("B: " + b);

        System.out.println();

    }
}
