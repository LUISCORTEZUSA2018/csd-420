
/*
 * Author: Luis Leonardo Cortez Velez
 * University: Bellevue University
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 2.2 - Binary I/O Programming Assignment
 * Date: November 1, 2026
 * File: DataFileReader.java
 *
 * Description:
 * Reads and displays previously stored integer and double
 * arrays from the binary file cortez_datafile.dat.
 *
 * Each data group contains five integers followed by
 * five double values.
 */

package com.luiscortez.csd420_module2;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataFileReader {

    private static final String FILE_NAME = "cortez_datafile.dat";
    private static final int ARRAY_SIZE = 5;

    public static void main(String[] args) {

        File dataFile = new File(FILE_NAME);

        System.out.println("CSD420 - Module 2.2");
        System.out.println("Binary File Reader");
        System.out.println("------------------------------");

        if (!dataFile.exists()) {
            System.out.println("File not found: " + FILE_NAME);
            return;
        }

        int groupCount = 0;

        try (DataInputStream input = new DataInputStream(
                new FileInputStream(dataFile))) {

            while (true) {

                int[] integers = new int[ARRAY_SIZE];
                double[] doubles = new double[ARRAY_SIZE];

                try {
                    for (int i = 0; i < ARRAY_SIZE; i++) {
                        integers[i] = input.readInt();
                    }

                    for (int i = 0; i < ARRAY_SIZE; i++) {
                        doubles[i] = input.readDouble();
                    }

                } catch (EOFException e) {
                    break;
                }

                groupCount++;

                System.out.println("\nData Group " + groupCount);

                System.out.println("Integer Values:");
                for (int number : integers) {
                    System.out.println(number);
                }

                System.out.println("Double Values:");
                for (double number : doubles) {
                    System.out.printf("%.4f%n", number);
                }
            }

            System.out.println("\n------------------------------");
            System.out.println("Total groups read: " + groupCount);
            System.out.println("File reading completed successfully.");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
