package com.bridgelabz.javastreams;
import java.io.*;

public class FileHandling {
    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream("input.txt");
            FileOutputStream fos = new FileOutputStream("output.txt");
            int byteData;
            while((byteData = fis.read())!= -1){
                fos.write(byteData);
                System.out.print((char)byteData);
            }
        }
        catch(IOException e){
            System.out.println("File not found");
        }
    }
}

