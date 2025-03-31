package com.bridgelabz.javastreams;

import java.io.*;

public class LineByLine {
    public static void main(String[] args) {
        String filePath = "input.txt"; // Replace with your actual file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) { // Read line by line
                if (line.toLowerCase().contains("error")) { // Case-insensitive check
                    System.out.println(line); // Print matching lines
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

