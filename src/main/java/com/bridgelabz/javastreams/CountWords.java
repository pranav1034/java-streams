package com.bridgelabz.javastreams;

import java.io.*;
import java.util.*;

public class CountWords {
    public static void main(String[] args) {
        String filePath = "input.txt"; // Replace with your actual file

        Map<String, Integer> wordCount = new HashMap<>();

        // Read file and count word occurrences
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert to lowercase and remove punctuation
                line = line.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");

                // Split into words
                String[] words = line.split("\\s+");

                // Count word frequency
                for (String word : words) {
                    if (!word.isEmpty()) { // Ignore empty strings
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Sort words by frequency in descending order
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Display total word count
        int totalWords = wordCount.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total Words: " + totalWords);

        // Display top 5 most frequent words
        System.out.println("\nTop 5 Most Frequent Words:");
        for (int i = 0; i < Math.min(5, sortedWords.size()); i++) {
            System.out.println(sortedWords.get(i).getKey() + " → " + sortedWords.get(i).getValue() + " times");
        }
    }
}

