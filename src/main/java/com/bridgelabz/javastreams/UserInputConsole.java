package com.bridgelabz.javastreams;
import java.io.*;

public class UserInputConsole {
    public static void main(String[] args) {
        String fileName = "user_info.txt";  // Output file

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(fileName, true)) {

            // Asking for user input
            System.out.print("Enter your name: ");
            String name = reader.readLine();

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(reader.readLine());  // Convert to integer

            System.out.print("Enter your favorite programming language: ");
            String language = reader.readLine();

            // Writing data to file
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Favorite Language: " + language + "\n");

            System.out.println("\nInformation saved to " + fileName);

        } catch (IOException e) {
            System.err.println("Error reading input or writing to file: " + e.getMessage());
        }
    }
}

