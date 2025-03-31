package com.bridgelabz.javastreams;
import java.io.*;

public class FileCopy {
    private static final int BUFFER_SIZE = 4096; // 4 KB

    public static void main(String[] args) {
        String sourceFile = "input.txt";
        String destBuffered = "dest_buffered.dat";
        String destUnbuffered = "dest_unbuffered.dat";

        // Copy using Buffered Streams
        long bufferedTime = copyFileWithBufferedStreams(sourceFile, destBuffered);
        System.out.println("Buffered Streams Copy Time: " + bufferedTime + " ns (" + (bufferedTime / 1000000) + " ms)");

        // Copy using Unbuffered Streams
        long unbufferedTime = copyFileWithUnbufferedStreams(sourceFile, destUnbuffered);
        System.out.println("Unbuffered Streams Copy Time: " + unbufferedTime + " ns (" + (unbufferedTime / 1000000) + " ms)");
    }

    public static long copyFileWithBufferedStreams(String source, String destination) {
        long startTime = System.nanoTime();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Error during buffered copy: " + e.getMessage());
        }
        return System.nanoTime() - startTime;
    }

    public static long copyFileWithUnbufferedStreams(String source, String destination) {
        long startTime = System.nanoTime();
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Error during unbuffered copy: " + e.getMessage());
        }
        return System.nanoTime() - startTime;
    }
}

