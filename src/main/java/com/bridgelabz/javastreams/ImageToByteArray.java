package com.bridgelabz.javastreams;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class ImageToByteArray {
    public static void main(String[] args) {
        String inputImage = "input_image.jpg";   // Input file
        String outputImage = "output_image.jpg"; // Output file

        try {
            // Convert image to byte array
            byte[] imageBytes = imageToByteArray(inputImage);

            // Convert byte array back to image
            byteArrayToImage(imageBytes, outputImage);

            // Verify if both files are identical
            if (verifyFiles(inputImage, outputImage)) {
                System.out.println("The output image is identical to the original.");
            } else {
                System.out.println("The files are different.");
            }
        } catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }

    // Method to convert image to byte array
    public static byte[] imageToByteArray(String imagePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(imagePath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    // Method to write byte array back to image
    public static void byteArrayToImage(byte[] imageData, String outputPath) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bais.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    // Method to verify if two files are identical
    public static boolean verifyFiles(String file1, String file2) throws IOException {
        byte[] file1Bytes = Files.readAllBytes(new File(file1).toPath());
        byte[] file2Bytes = Files.readAllBytes(new File(file2).toPath());
        return Arrays.equals(file1Bytes, file2Bytes);
    }
}

