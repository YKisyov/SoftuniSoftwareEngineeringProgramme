package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        String resPath = "res/input.txt";

        try {
            FileInputStream fileInputStream = new FileInputStream(resPath);
            try {
                int singleByte = fileInputStream.read();
                while (singleByte >= 0) {
                    System.out.printf("%s ", Integer.toBinaryString(singleByte));
                    singleByte = fileInputStream.read();
                }
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
