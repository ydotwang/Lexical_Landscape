
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * comparing the performance of BSTMap and AVLTreeMap
 * This program compares the performance of BSTMap and AVLTreeMap
 * date 4/17/2023
 * To compile: javac AVLvsBSTComparison.java
 * To run: java AVLvsBSTComparison
 */
import java.util.Random;

/**
 * This class compares the performance of BSTMap and AVLTreeMap
 * by inserting and retrieving elements
 * from both maps.
 * The results are printed to the console.
 */
public class AVLvsBSTComparison {

    public static void main(String[] args) {
        long startTime, endTime;
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<>();
        Random random = new Random();

        // Insert elements into the BSTMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            bstMap.put("key" + random.nextInt(10000), i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("BSTMap put: " + (endTime - startTime) + " ms");

        // Insert elements into the AVLTreeMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            avlTreeMap.put("key" + random.nextInt(10000), i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("AVLTreeMap put: " + (endTime - startTime) + " ms");

        // Retrieve elements from the BSTMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            bstMap.get("key" + random.nextInt(10000));
        }
        endTime = System.currentTimeMillis();
        System.out.println("BSTMap get: " + (endTime - startTime) + " ms");

        // Retrieve elements from the AVLTreeMap
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            avlTreeMap.get("key" + random.nextInt(10000));
        }
        endTime = System.currentTimeMillis();
        System.out.println("AVLTreeMap get: " + (endTime - startTime) + " ms");
    }
}
