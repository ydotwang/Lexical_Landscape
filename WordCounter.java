
/**
 * Yuyang Wang
 * CS 231 A
 * Project 6
 * WordCounter
 * This program counts the number of words in a file
 * date 4/17/2023
 * To compile: javac WordCounter.java
 * To run: java WordCounter
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The word counter class for counting the number of words in a file and
 * building maps
 * 
 * @author Yuyang Wang
 */
public class WordCounter {
    public MapSet<String, Integer> map;
    private int totalWordCount;

    public WordCounter(String data_structure) {
        if (data_structure.equals("bst")) {
            map = new BSTMap<>();
        } else if (data_structure.equals("hashmap")) {
            map = new HashMap<>();
        } else if (data_structure.equals("avl")) {
            map = new AVLTreeMap<>();
        }
    }

    public ArrayList<String> readWords(String filename) {
        ArrayList<String> words = new ArrayList<>();
        totalWordCount = 0;

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                String[] lineWords = line.trim().split("\\s+");
                for (String word : lineWords) {
                    if (!word.isEmpty() && word.length() > 1 && word.matches("^[a-zA-Z]+$")) {
                        words.add(word.toLowerCase());
                        totalWordCount++;
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public ArrayList<String> readUniqueWords(String filename) {
        ArrayList<String> words = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                String[] lineWords = line.trim().split("\\s+");
                for (String word : lineWords) {
                    if (!word.isEmpty() && word.length() > 1 && word.matches("^[a-zA-Z]+$")) {
                        words.add(word.toLowerCase());

                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public double buildMap(ArrayList<String> words) {
        long startTime = System.currentTimeMillis();
        clearMap();
        totalWordCount = 0;

        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
            totalWordCount++;
        }

        return (System.currentTimeMillis() - startTime);
    }

    public void clearMap() {
        map.clear();
    }

    public int totalWordCount() {
        return totalWordCount;
    }

    public int uniqueWordCount() {
        return map.size();
    }

    public int getCount(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        } else {
            return 0;
        }
    }

    public double getFrequency(String word) {
        return getCount(word) / (double) totalWordCount;
    }

    public boolean writeWordCount(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(Integer.toString(totalWordCount));
            writer.newLine();

            for (MapSet.KeyValuePair<String, Integer> entry : map.entrySet()) {
                String line = entry.getKey() + " " + entry.getValue() + " " + getFrequency(entry.getKey());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing word count file: " + filename);
            return false;
        }

        return true;
    }

    public boolean readWordCount(String filename) {
        clearMap();
        totalWordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String word = parts[0];
                int count = Integer.parseInt(parts[1]);
                map.put(word, count);
                totalWordCount += count;
            }
        } catch (IOException e) {
            System.err.println("Error reading word count file: " + filename);
            return false;
        }

        return true;
    }

    public String mostFrequentWord() {
        String mostFrequentWord = null;
        int maxCount = 0;

        for (MapSet.KeyValuePair<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }

        return mostFrequentWord;
    }

}
