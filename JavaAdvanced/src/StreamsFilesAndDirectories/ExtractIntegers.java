package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) throws IOException {
        String resPathForInputFile = "res/input.txt";
        String resPathForOutputFile = "res/04.ExtractIntegersOutput.txt";
        Scanner scanner = new Scanner(new FileInputStream(resPathForInputFile));
        PrintWriter fileOut = new PrintWriter(resPathForOutputFile);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                fileOut.println(scanner.nextInt());
            }
        scanner.next();
        }

        scanner.close();
        fileOut.close();


    }
}
