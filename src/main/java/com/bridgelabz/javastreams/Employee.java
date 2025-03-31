package com.bridgelabz.javastreams;
import java.io.Serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Employee class (Serializable)
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;  // Ensures version compatibility

    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department + ", Salary: $" + salary;
    }
}

class EmployeeFileHandler {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Pranav", "Engineering", 95000));
        employees.add(new Employee(102, "Nikhil", "Admin", 75000));
        employees.add(new Employee(103, "Raghav", "Finance", 80000));

        // Save employees to file
        saveEmployeesToFile(employees);

        // Load and display employees from file
        List<Employee> loadedEmployees = loadEmployeesFromFile();
        if (loadedEmployees != null) {
            System.out.println("\nEmployee Details:");
            loadedEmployees.forEach(System.out::println);
        }
    }

    // Save employees to file (Serialization)
    private static void saveEmployeesToFile(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("Employees saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    // Load employees from file (Deserialization)
    private static List<Employee> loadEmployeesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
        return null;
    }
}


