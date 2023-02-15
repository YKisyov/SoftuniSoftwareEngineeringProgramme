package StreamsFilesAndDirectoriesExercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SumBytes {

    public static void main(String[] args) throws IOException {
        String filePathAndName = "res/Files-and-Streams-Exercises-Res/input.txt";
        FileReader fileReader = new FileReader(filePathAndName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        BigInteger sumOfTotalCharValues = new BigInteger("0");
        String currentLine = bufferedReader.readLine();
        do {
            for (int i = 0; i < currentLine.length(); i++) {
                sumOfTotalCharValues = sumOfTotalCharValues.add(BigInteger.valueOf(currentLine.charAt(i)));
            }
            currentLine = bufferedReader.readLine();
        } while (currentLine != null);
        System.out.println(sumOfTotalCharValues);

        fileReader.close();
    }
}

