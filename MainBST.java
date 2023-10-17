
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * Run the program using BST 
 * This program runs the program using BST
 * date 4/17/2023
 * To compile: javac MainBST.java
 * To run: java -Xmx4g MainBST <input_file> <output_file>
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The main class for running the BSTMap
 * 
 * @author Yuyang Wang
 */
public class MainBST {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -Xmx4g Main <input_file> <output_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        Set<String> commonWords = readCommonWords("common.txt");

        WordCounter wordCounter = new WordCounter("bst");
        ArrayList<String> words = wordCounter.readWords(inputFile);

        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : words) {
            if (!commonWords.contains(word)) {
                filteredWords.add(word);
            }
        }

        double timeTaken = wordCounter.buildMap(words);

        System.out.println("Number of unbalanced nodes: " + wordCounter.map.getNumberOfImbalancedNodes());

        int maxDepth = wordCounter.map.maxDepth();
        System.out.println("Max depth: " + maxDepth);

        System.out.println("Total words: " + wordCounter.totalWordCount());
        System.out.println("Unique words: " + wordCounter.uniqueWordCount());
        System.out.println("Time taken to build the map: " + (timeTaken) + "ms");

        wordCounter.clearMap();
        wordCounter.buildMap(filteredWords);
        String mostFrequentWord = wordCounter.mostFrequentWord();
        System.out.println(
                "Most frequent word: " + mostFrequentWord + " frequency (" + wordCounter.getFrequency(mostFrequentWord)
                        + " )" + "    appears  " + wordCounter.getCount(mostFrequentWord) + "times");

        System.out.println("Interesting word: obama" + " frequency (" + wordCounter.getFrequency("obama")
                + " )" + "   appears  " + wordCounter.getCount("obama") + "times");

        wordCounter.writeWordCount(outputFile);
    }

    private static Set<String> readCommonWords(String filename) {
        Set<String> commonWords = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commonWords.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading common words file: " + filename);
        }

        return commonWords;
    }
}
