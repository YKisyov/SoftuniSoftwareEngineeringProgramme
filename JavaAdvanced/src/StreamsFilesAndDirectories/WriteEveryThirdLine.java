package StreamsFilesAndDirectories;

import java.io.*;

public class WriteEveryThirdLine {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "res/input.txt";
        String outPutFilePath = "res/05.WriteEveryThirdLineOutput.txt";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        PrintWriter printWriter = new PrintWriter(new FileWriter(outPutFilePath));
            int lineCounter = 1;
            printWriter.println(bufferedReader.readLine());
            while (bufferedReader.)


        bufferedReader.close();
        printWriter.close();
    }
}
