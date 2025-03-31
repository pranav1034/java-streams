package com.bridgelabz.javastreams;

import java.io.*;

class StudentDataBinary {
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        Student[] students = {
                new Student(101, "Pranav", 9.8),
                new Student(102, "Nikhil", 9.5),
                new Student(103, "Aman", 8.9)
        };

        // Write student data to binary file
        writeStudentData(students);

        // Read and display student data from binary file
        readStudentData();
    }

    // Method to write student data using DataOutputStream
    private static void writeStudentData(Student[] students) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Student student : students) {
                dos.writeInt(student.getRollNumber()); // Writing roll number (int)
                dos.writeUTF(student.getName());       // Writing name (String)
                dos.writeDouble(student.getGpa());     // Writing GPA (double)
            }
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing student data: " + e.getMessage());
        }
    }

    // Method to read student data using DataInputStream
    private static void readStudentData() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("Student Details from File:");
            while (dis.available() > 0) { // Read till end of file
                int rollNumber = dis.readInt();  // Read roll number
                String name = dis.readUTF();     // Read name
                double gpa = dis.readDouble();   // Read GPA
                System.out.println("Roll No: " + rollNumber + ", Name: " + name + ", GPA: " + gpa);
            }
        } catch (IOException e) {
            System.err.println("Error reading student data: " + e.getMessage());
        }
    }
}

public class Student {
    private int rollNumber;
    private String name;
    private double gpa;

    public Student(int rollNumber, String name, double gpa) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.gpa = gpa;
    }

    public int getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
}

