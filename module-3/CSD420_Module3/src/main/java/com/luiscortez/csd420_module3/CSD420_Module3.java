
/**
 * Author: Luis Leonardo Cortez Velez
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 3.2 - Generics Programming Assignment
 * Date: November 7, 2026
 *
 * Program Description:
 * This program demonstrates the use of Java generics by
 * creating an ArrayList containing 50 random integers
 * between 1 and 20. A generic static method removes
 * duplicate values and returns a new ArrayList while
 * preserving the original order of the unique elements.
 *
 * The program displays both ArrayLists and their sizes
 * to demonstrate successful duplicate removal.
 */

package com.luiscortez.csd420_module3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for the CSD420 Module 3.2 assignment.
 *
 * @author Luis Leonardo Cortez Velez
 */
public class CSD420_Module3 {

    /**
     * Creates a new ArrayList without duplicate elements.
     * The original ArrayList is not modified.
     *
     * @param <E> the generic element type
     * @param list the original ArrayList
     * @return a new ArrayList containing unique elements
     * @throws IllegalArgumentException if the input list is null
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        if (list == null) {
            throw new IllegalArgumentException(
                    "The input ArrayList cannot be null.");
        }

        ArrayList<E> uniqueList = new ArrayList<>();

        // Check each element and prevent duplicate entries.
        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        return uniqueList;
    }

    /**
     * Executes the program and displays the results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        try {
            ArrayList<Integer> originalList = new ArrayList<>();
            Random random = new Random();

            // Generate exactly 50 random integers from 1 to 20.
            for (int i = 0; i < 50; i++) {
                originalList.add(random.nextInt(20) + 1);
            }

            // Invoke the generic method to remove duplicates.
            ArrayList<Integer> uniqueList =
                    removeDuplicates(originalList);

            // Display the original ArrayList.
            System.out.println("CSD420 - Advanced Java Programming");
            System.out.println("Module 3.2 - Generics Assignment");
            System.out.println("Author: Luis Leonardo Cortez Velez");
            System.out.println("----------------------------------");

            System.out.println("\nOriginal ArrayList:");
            System.out.println(originalList);
            System.out.println(
                    "Original List Size: " + originalList.size());

            // Display the ArrayList without duplicate values.
            System.out.println("\nArrayList Without Duplicates:");
            System.out.println(uniqueList);
            System.out.println(
                    "Unique List Size: " + uniqueList.size());

            // Verify the original list contains 50 elements.
            if (originalList.size() == 50) {
                System.out.println(
                        "\nVerification: 50 values generated.");
            }

            // Verify that the new list contains unique values.
            if (uniqueList.size()
                    == removeDuplicates(uniqueList).size()) {
                System.out.println(
                        "Verification: No duplicate values found.");
            }

        } catch (IllegalArgumentException exception) {

            System.err.println(
                    "Input Error: " + exception.getMessage());
        }
    }
}
