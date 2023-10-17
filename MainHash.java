
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * Running the program using HashMap
 * This program runs the program using HashMap
 * date 4/17/2023
 * To compile: javac MainHash.java
 * To run: java -Xmx4g MainHash <input_file> <output_file>
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.FileWriter;

/**
 * The main class for running the HashMap
 * 
 * @author Yuyang Wang
 */
public class MainHash {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Usage: java -Xmx4g Main <input_file> <output_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        Set<String> commonWords = readCommonWords("common.txt");

        WordCounter wordCounter = new WordCounter("hashmap");
        ArrayList<String> words = wordCounter.readWords(inputFile);

        double timeTaken = wordCounter.buildMap(words);

        int maxDepth = wordCounter.map.maxDepth();
        System.out.println("Max depth: " + maxDepth);

        wordCounter.writeWordCount(outputFile);

        ArrayList<String> uniqueWords = wordCounter.readUniqueWords(outputFile);

        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : uniqueWords) {
            if (!commonWords.contains(word)) {
                filteredWords.add(word);
            }
        }

        FileWriter writer = new FileWriter("filtered.txt");
        for (String word : filteredWords) {
            writer.write(word + " " + wordCounter.getCount(word) + System.lineSeparator());
        }
        writer.close();

        System.out.println("Total words: " + wordCounter.totalWordCount());
        System.out.println("Unique words: " + wordCounter.uniqueWordCount());
        System.out.println("Filtered words: " + filteredWords.size());
        System.out.println("Time taken to build the map: " + (timeTaken) + "ms");

        wordCounter.readWordCount("filtered.txt");

        String mostFrequentWord = wordCounter.mostFrequentWord();
        System.out.println(
                "Most frequent word: " + mostFrequentWord + " frequency (" + wordCounter.getFrequency(mostFrequentWord)
                        + " )" + "    appears  " + wordCounter.getCount(mostFrequentWord) + "times");

        System.out.println("Interesting word: obama" + " frequency (" + wordCounter.getFrequency("obama")
                + " )" + "   appears  " + wordCounter.getCount("obama") + "times");
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
