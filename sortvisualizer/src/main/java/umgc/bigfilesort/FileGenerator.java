package umgc.bigfilesort;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Utility class to generate a large binary file containing random integers
 */
public class FileGenerator
{
    /**
    * Generates a file with the given name, containing the specified number of random integers,
    * each in the range [0, maxRandom).
    *
    * @param filename   the name of the file to create
    * @param numInts    how many integers to write
    * @param maxRandom  upper bound for random ints
    * @throws IOException if file I/O fails
    */
    public static void generateRandomDataFile(String fileName, int numInts, int maxRandom) throws IOException
    {
        try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName))))
        {
            Random rand = new Random();
            for (int i = 0; i < numInts; i++)
            {
                output.writeInt(rand.nextInt(maxRandom));
            }
        }
    }
}
