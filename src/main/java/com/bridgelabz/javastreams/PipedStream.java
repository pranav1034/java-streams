package com.bridgelabz.javastreams;

import java.io.*;

class WriterThread extends Thread {
    private PipedOutputStream pos;

    public WriterThread(PipedOutputStream pos) {
        this.pos = pos;
    }

    @Override
    public void run() {
        try {
            String message = "Hello World";
            pos.write(message.getBytes()); // Convert string to bytes and write
            pos.close(); // Close stream after writing
        } catch (IOException e) {
            System.err.println("WriterThread Error: " + e.getMessage());
        }
    }
}

class ReaderThread extends Thread {
    private PipedInputStream pis;

    public ReaderThread(PipedInputStream pis) {
        this.pis = pis;
    }

    @Override
    public void run() {
        try {
            int data;
            System.out.print("Reader Thread Received: ");
            while ((data = pis.read()) != -1) { // Read byte by byte
                System.out.print((char) data);  // Convert byte to char
            }
            System.out.println(); // New line after reading
            pis.close(); // Close stream after reading
        } catch (IOException e) {
            System.err.println("ReaderThread Error: " + e.getMessage());
        }
    }
}

public class PipedStream {
    public static void main(String[] args) {
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos); // Connect input to output

            // Create and start threads
            WriterThread writer = new WriterThread(pos);
            ReaderThread reader = new ReaderThread(pis);

            writer.start();
            reader.start();

        } catch (IOException e) {
            System.err.println("Error connecting pipes: " + e.getMessage());
        }
    }
}

