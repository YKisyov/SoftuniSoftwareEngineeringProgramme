import java.io.*;

public class SumLine {
    public static void main(String[] args) throws IOException {
        String filePathAndName = "res/Files-and-Streams-Exercises-Res/input.txt";
        FileReader fileReader = new FileReader(filePathAndName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int sumOfCharValuesPerLine = 0;
        String currentLine = bufferedReader.readLine();
        do {
            for (int i = 0; i < currentLine.length(); i++) {
                sumOfCharValuesPerLine += currentLine.charAt(i);
            }

            System.out.println(sumOfCharValuesPerLine);
            sumOfCharValuesPerLine = 0;
            currentLine = bufferedReader.readLine();
        } while (currentLine != null);


        fileReader.close();
    }
}