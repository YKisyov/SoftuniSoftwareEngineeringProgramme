package StreamsFilesAndDirectoriesExercise;

import java.io.*;

public class AllCapitals {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("res/Files-and-Streams-Exercises-Res/input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("res/Files-and-Streams-Exercises-Res/output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String line = bufferedReader.readLine();
        while (line != null){
           printWriter.println(line.toUpperCase());
           line = bufferedReader.readLine();
        }


        fileReader.close();
        bufferedReader.close();
        fileWriter.close();
        printWriter.close();
    }
}
