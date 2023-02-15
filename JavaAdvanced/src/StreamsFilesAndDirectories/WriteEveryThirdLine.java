package StreamsFilesAndDirectories;

import java.io.*;

public class WriteEveryThirdLine {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "res/input.txt";
        String outPutFilePath = "res/05.WriteEveryThirdLineOutput.txt";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        PrintWriter printWriter = new PrintWriter(new FileWriter(outPutFilePath));
        int count = 0;

        String currentLine = "";
        do {
            currentLine = bufferedReader.readLine();
            count++;
            if (count % 3 == 0) {
                System.out.println(currentLine);
                printWriter.println(currentLine);
            }
        } while (currentLine != null);

        bufferedReader.close();
        printWriter.close();
    }
}
