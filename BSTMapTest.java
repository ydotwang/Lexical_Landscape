
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * BSTMapTest
 * This program tests the BSTMap class
 * date 4/17/2023
 * To compile: javac BSTMapTest.java
 * To run: java -ea BSTMapTest
 */

import java.util.Comparator;
import java.util.ArrayList;

public class BSTMapTest {
    public static void main(String[] args) {
        BSTMapTest test = new BSTMapTest();
        test.testPutAndGet();
        test.testRemove();
        test.testSize();
        test.testClear();
        test.testMaxDepth();
        test.testImbalancedNodes();
        System.out.println("All tests passed!");
    }

    public void testPutAndGet() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);

        assert words.get("ten").equals(10) : "testPutAndGet failed";
        assert words.get("five").equals(5) : "testPutAndGet failed";
        assert words.get("three").equals(3) : "testPutAndGet failed";
    }

    public void testRemove() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);

        Integer removed = words.remove("five");
        assert removed.equals(5) : "testRemove failed";
        assert words.get("five") == null : "testRemove failed";
    }

    public void testSize() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);

        assert words.size() == 3 : "testSize failed";
    }

    public void testClear() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        words.clear();

        assert words.size() == 0 : "testClear failed";
        assert words.get("ten") == null : "testClear failed";
        assert words.get("five") == null : "testClear failed";
        assert words.get("three") == null : "testClear failed";
    }

    public void testMaxDepth() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        words.put("one", 1);

        assert words.maxDepth() == 3 : "testMaxDepth failed";
    }

    public void testImbalancedNodes() {
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        words.put("one", 1);

        assert words.getNumberOfImbalancedNodes() == 0 : "testImbalancedNodes failed";
    }
}
