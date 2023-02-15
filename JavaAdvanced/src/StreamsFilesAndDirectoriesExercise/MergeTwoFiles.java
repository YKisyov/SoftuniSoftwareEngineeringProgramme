package StreamsFilesAndDirectoriesExercise;

import java.io.*;

public class MergeTwoFiles {
    public static void main(String[] args) throws IOException {
        FileReader fileReader1, fileReader2;
        fileReader1 = new FileReader("res/Files-and-Streams-Exercises-Res/inputOne.txt");
        fileReader2 = new FileReader("res/Files-and-Streams-Exercises-Res/inputTwo.txt");
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

        FileWriter fileWriter = new FileWriter("res/Files-and-Streams-Exercises-Res/outputZZTop.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        concatTowFiles(bufferedReader1, bufferedReader2, printWriter);

        fileReader1.close();
        fileReader2.close();
        bufferedReader1.close();
        bufferedReader2.close();
        fileWriter.close();
        printWriter.close();
    }

    public static void concatTowFiles(BufferedReader firstFile, BufferedReader secondFile, PrintWriter concatinatedFile) throws IOException {
        String content = firstFile.readLine();
        while (content != null) {
            concatinatedFile.println(content);
            content = firstFile.readLine();
        }
        content = secondFile.readLine();
        while (content != null) {
            concatinatedFile.println(content);
            content = secondFile.readLine();
        }
    }

}
