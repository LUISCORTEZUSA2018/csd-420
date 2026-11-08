
/*
 * Author: Luis Leonardo Cortez Velez
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 4.2 - LinkedList Performance
 * Date: November 7, 2026
 *
 * Description:
 * Compares the performance of Iterator traversal
 * and get(index) traversal using a LinkedList
 * containing 50,000 and 500,000 integers.
 */

package com.luiscortez.csd420_module4;

import java.util.Iterator;
import java.util.LinkedList;

public class CSD420_Module4 {

    public static void main(String[] args) {
        testTraversal(50000);
        testTraversal(500000);
    }

    /**
     * Tests both traversal methods with the same data.
     *
     * @param size number of elements to insert
     */
    public static void testTraversal(int size) {

        LinkedList<Integer> numbers = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }

        System.out.println("\nTesting LinkedList with "
                + size + " integers");

        // Iterator moves sequentially through the nodes.
        // Its traversal complexity is O(n).
        long startIterator = System.nanoTime();

        long iteratorSum = 0;
        Iterator<Integer> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            iteratorSum += iterator.next();
        }

        long iteratorTime = System.nanoTime() - startIterator;

        // get(index) searches for each indexed element.
        // Repeated indexed access has O(n^2) complexity
        // for a LinkedList, making it much slower.
        long startGet = System.nanoTime();

        long getSum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            getSum += numbers.get(i);
        }

        long getTime = System.nanoTime() - startGet;

        System.out.println("Iterator time: "
                + iteratorTime / 1_000_000.0 + " ms");

        System.out.println("get(index) time: "
                + getTime / 1_000_000.0 + " ms");

        // Verify both methods processed all values.
        long expectedSum = (long) size * (size - 1) / 2;

        if (iteratorSum == expectedSum
                && getSum == expectedSum) {
            System.out.println("Verification: PASSED");
        } else {
            System.err.println("Verification: FAILED");
        }

        /*
         * Results explanation:
         *
         * Iterator traversal is generally faster
         * because it visits each node only once.
         *
         * get(index) is slower because a LinkedList
         * must traverse nodes to locate each index.
         *
         * As the list grows from 50,000 to 500,000
         * elements, indexed access becomes
         * considerably more expensive.
         */
    }
}
