package umgc.bigfilesort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExternalSortTest
{
    private static final String SOURCE_FILE = "test_largedata.dat";
    private static final String TARGET_FILE = "test_sortedfile.dat";

    @Test
    void testExternalSort_small() throws IOException
    {
        // 1) Create a small data file
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(SOURCE_FILE)))
        {
            out.writeInt(42);
            out.writeInt(7);
            out.writeInt(100);
            out.writeInt(1);
            out.writeInt(50);
        }

        // 2) Sort it
        ExternalSort.sort(SOURCE_FILE, TARGET_FILE);

        // 3) Read back the sorted file
        try (DataInputStream in = new DataInputStream(new FileInputStream(TARGET_FILE)))
        {
            int[] result = new int[5];
            for (int i = 0; i < 5; i++)
            {
                result[i] = in.readInt();
            }
            // 4) Assert sorted
            assertArrayEquals(new int[]
            { 1, 7, 42, 50, 100 }, result);
        }

        // Clean up
        Files.deleteIfExists(Paths.get(SOURCE_FILE));
        Files.deleteIfExists(Paths.get(TARGET_FILE));
    }

    @Test
    void testExternalSort_medium() throws IOException
    {
        // You can generate a bigger file for more realistic testing
        FileGenerator.generateRandomDataFile(SOURCE_FILE, 10_000, 10_000);

        // Sort
        ExternalSort.sort(SOURCE_FILE, TARGET_FILE);

        // (Optional) Validate a portion is sorted or do a full pass if you want
        try (DataInputStream in = new DataInputStream(new FileInputStream(TARGET_FILE)))
        {
            int previous = -1;
            for (int i = 0; i < 500; i++)
            {
                if (in.available() <= 0)
                    break;
                int current = in.readInt();
                assertTrue(current >= previous, "File not sorted at index " + i);
                previous = current;
            }
        }

        // Clean up
        Files.deleteIfExists(Paths.get(SOURCE_FILE));
        Files.deleteIfExists(Paths.get(TARGET_FILE));
    }

}
