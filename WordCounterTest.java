
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * WordCounterTest
 * This program tests the WordCounter class
 * date 4/17/2023
 * To compile: javac WordCounterTest.java
 * To run: java -ea WordCounterTest
 */

import java.util.ArrayList;

public class WordCounterTest {
    public static void main(String[] args) {
        WordCounterTest test = new WordCounterTest();
        test.testReadWords();
        test.testBuildMap();
        test.testClearMap();
        test.testGetCount();
        test.testGetFrequency();
        System.out.println("All tests passed!");
    }

    public void testReadWords() {
        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = wordCounter.readWords("test.txt");
        int expected = 9;
        int actual = words.size();

        assert expected == actual : "testReadWords failed";
    }

    public void testBuildMap() {
        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");

        double buildTime = wordCounter.buildMap(words);
        int expected = 2;
        int actual = wordCounter.uniqueWordCount();

        assert expected == actual : "testBuildMap failed";
    }

    public void testClearMap() {
        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");

        wordCounter.buildMap(words);
        wordCounter.clearMap();
        int expected = 0;
        int actual = wordCounter.uniqueWordCount();

        assert expected == actual : "testClearMap failed";
    }

    public void testGetCount() {
        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");

        wordCounter.buildMap(words);
        int expected = 2;
        int actual = wordCounter.getCount("apple");

        assert expected == actual : "testGetCount failed";
    }

    public void testGetFrequency() {
        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");

        wordCounter.buildMap(words);
        double expected = 2.0 / 3.0;
        double actual = wordCounter.getFrequency("apple");

        assert Math.abs(expected - actual) < 1e-9 : "testGetFrequency failed";
    }

}
