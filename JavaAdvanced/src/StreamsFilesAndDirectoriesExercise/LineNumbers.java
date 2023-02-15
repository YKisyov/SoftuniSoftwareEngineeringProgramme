package StreamsFilesAndDirectoriesExercise;

import java.io.*;

public class LineNumbers {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("res/Files-and-Streams-Exercises-Res/inputLineNumbers.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("res/Files-and-Streams-Exercises-Res/output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int lineCounter = 0;
        String currentLine = bufferedReader.readLine();
        while (currentLine != null){
            lineCounter++;
            printWriter.printf("%d. %s\n", lineCounter, currentLine);
            System.out.printf("%d. %s\n", lineCounter, currentLine);

            currentLine = bufferedReader.readLine();
        }

            fileReader.close();
        bufferedReader.close();
        fileWriter.close();
        printWriter.close();
    }
}
