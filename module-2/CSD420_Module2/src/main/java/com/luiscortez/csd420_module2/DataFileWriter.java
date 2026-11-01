
/*
 * Author: Luis Leonardo Cortez Velez
 * University: Bellevue University
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 2.2 - Binary I/O Programming Assignment
 * Date: November 1, 2026
 * File: DataFileWriter.java
 *
 * Description:
 * This program generates two arrays:
 *   1. Five random integer values.
 *   2. Five random double values.
 *
 * The program writes the arrays to a binary data file
 * named cortez_datafile.dat.
 *
 * If the file does not exist, it is created.
 * If the file exists, new data is appended without
 * overwriting the previously stored information.
 *
 * Java Concepts:
 * - Arrays and random number generation
 * - Binary file output using DataOutputStream
 * - FileOutputStream with append functionality
 * - Methods and loops
 * - Exception handling using try-with-resources
 */

package com.luiscortez.csd420_module2;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DataFileWriter {

    // Name of the binary data file.
    private static final String FILE_NAME =
            "cortez_datafile.dat";

    // Number of elements in each array.
    private static final int ARRAY_SIZE = 5;

    public static void main(String[] args) {

        // Create arrays for integers and double values.
        int[] integerValues = new int[ARRAY_SIZE];
        double[] doubleValues = new double[ARRAY_SIZE];

        // Generate random numbers for both arrays.
        generateRandomValues(integerValues, doubleValues);

        // Display the generated values before saving.
        System.out.println("CSD420 - Module 2.2");
        System.out.println("Binary File Writer");
        System.out.println("------------------------------");

        System.out.println("\nGenerated Integer Values:");
        for (int number : integerValues) {
            System.out.println(number);
        }

        System.out.println("\nGenerated Double Values:");
        for (double number : doubleValues) {
            System.out.printf("%.4f%n", number);
        }

        // Save the generated values to the binary file.
        writeDataToFile(integerValues, doubleValues);
    }

    /**
     * Generates five random integers and five random
     * double values using a Random object.
     *
     * @param integers Array for generated integer values.
     * @param doubles Array for generated double values.
     */
    private static void generateRandomValues(
            int[] integers, double[] doubles) {

        Random random = new Random();

        for (int i = 0; i < ARRAY_SIZE; i++) {

            // Generate integers between 1 and 100.
            integers[i] = random.nextInt(100) + 1;

            // Generate doubles between 0.0 and 100.0.
            doubles[i] = random.nextDouble() * 100;
        }
    }

    /**
     * Writes the integer and double arrays to a binary
     * file. Existing data is preserved through append mode.
     *
     * Each saved record contains five integers followed
     * by five doubles.
     *
     * @param integers Integer array to save.
     * @param doubles Double array to save.
     */
    private static void writeDataToFile(
            int[] integers, double[] doubles) {

        // The true argument enables append mode.
        try (DataOutputStream output =
                new DataOutputStream(
                        new FileOutputStream(FILE_NAME, true))) {

            // Write all five integers.
            for (int number : integers) {
                output.writeInt(number);
            }

            // Write all five doubles.
            for (double number : doubles) {
                output.writeDouble(number);
            }

            System.out.println(
                    "\nData successfully written to: "
                    + FILE_NAME);

            System.out.println(
                    "Existing file data has been preserved.");

        } catch (IOException exception) {

            // Report file writing errors to the console.
            System.err.println(
                    "Error writing data to the binary file: "
                    + exception.getMessage());
        }
    }
}
