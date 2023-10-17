
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * comparing the performance of ArrayList Map and AVLTreeMap
 * This program compares the performance of ArrayList Map and AVLTreeMap
 * date 4/17/2023
 * To compile: javac MapComparison.java
 * To run: java MapComparison
 */

import java.util.ArrayList;

/**
 * This class compares the performance of ArrayListMap and AVLTreeMap
 * by inserting and retrieving elements
 * from both maps.
 * The results are printed to the console.
 */
public class MapComparison {

    public static void main(String[] args) {
        long startTime, endTime;
        ArrayListMap<String, Integer> arrayListMap = new ArrayListMap<>();
        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<>();

        // Insert elements into the ArrayListMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayListMap.put("key" + i, i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayListMap put: " + (endTime - startTime) + " ms");

        // Insert elements into the AVLTreeMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            avlTreeMap.put("key" + i, i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("AVLTreeMap put: " + (endTime - startTime) + " ms");

        // Retrieve elements from the ArrayListMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayListMap.get("key" + i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayListMap get: " + (endTime - startTime) + " ms");

        // Retrieve elements from the AVLTreeMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            avlTreeMap.get("key" + i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("AVLTreeMap get: " + (endTime - startTime) + " ms");
    }
}
