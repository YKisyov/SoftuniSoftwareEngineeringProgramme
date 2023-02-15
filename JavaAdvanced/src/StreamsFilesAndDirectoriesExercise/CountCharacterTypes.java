package StreamsFilesAndDirectoriesExercise;

import java.io.*;

public class CountCharacterTypes {
    static int countVowels = 0;
    static int countPunctuation = 0;
    static int countOtherCharsButWhiteSpaces = 0;

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("res/Files-and-Streams-Exercises-Res/input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("res/Files-and-Streams-Exercises-Res/output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String line = bufferedReader.readLine();
        while (line != null) {
            updateCountersForEachLine(line);
            line = bufferedReader.readLine();

        }

        printWriter.printf("Vowels: %d\n", countVowels);
        printWriter.printf("Other symbols: %d\n", countOtherCharsButWhiteSpaces);
        printWriter.printf("Punctuation: %d\n", countPunctuation);

        System.out.printf("Vowels: %d\n", countVowels);
        System.out.printf("Other symbols: %d\n", countOtherCharsButWhiteSpaces);
        System.out.printf("Punctuation: %d", countPunctuation);

        fileReader.close();
        bufferedReader.close();
        fileWriter.close();
        printWriter.close();

    }

    static void updateCountersForEachLine(String str) {
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {

                case 'a':
                case 'o':
                case 'e':
                case 'u':
                case 'i':
                    countVowels++;
                    break;
                case '.':
                case ',':
                case '!':
                case '?':
                case '(':
                case ')':
                    countPunctuation++;
                    break;
                case ' ':
                    break;
                default:
                    countOtherCharsButWhiteSpaces++;
                    break;
            }
        }
    }

}