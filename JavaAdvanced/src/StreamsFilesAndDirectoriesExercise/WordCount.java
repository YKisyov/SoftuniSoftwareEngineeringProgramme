package StreamsFilesAndDirectoriesExercise;

import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        FileReader fileReader1 = new FileReader("res/Files-and-Streams-Exercises-Res/words.txt");
        FileReader fileReader2 = new FileReader("res/Files-and-Streams-Exercises-Res/text.txt");

        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

        FileWriter fileWriter = new FileWriter("res/Files-and-Streams-Exercises-Res/results.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        HashMap<String, Integer> mapCounter = new HashMap<>();
        Set<String> setOfUniqueWords = buildSetOfUniqueWords(bufferedReader1);

        for (String word : setOfUniqueWords) {
            mapCounter.put(word, 0);
        }

        String lineFromFile2 = bufferedReader2.readLine();
        while (lineFromFile2 != null) {
            String[] wordsArr = lineFromFile2.split(" ");
            for (int i = 0; i < wordsArr.length; i++) {
                if (mapCounter.containsKey(wordsArr[i])) {
                    mapCounter.replace(wordsArr[i], mapCounter.get(wordsArr[i]) + 1);
                }
            }
            lineFromFile2 = bufferedReader2.readLine();
        }

        // mapCounter.entrySet().removeIf(entry -> entry.getValue() == 0);
        mapCounter.entrySet()
                .stream()
                .sorted((set2, set1) -> (set1.getValue()).compareTo(set2.getValue()))
                .forEach(set -> {
                    System.out.printf("%s - %d\n", set.getKey(), set.getValue());
                    printWriter.printf("%s - %d\n", set.getKey(), set.getValue());
                });
        fileWriter.close();
        fileReader1.close();
        fileReader2.close();
        bufferedReader1.close();
        bufferedReader2.close();
    }

    public static Set<String> buildSetOfUniqueWords(BufferedReader br) throws IOException {
        Set<String> setOfUniqueWords = new HashSet<>();
        String currentLine = br.readLine();
        while (currentLine != null) {
            String[] wordsPerLine = currentLine.split(" ");
            for (String word : wordsPerLine) {
                setOfUniqueWords.add(word);
            }
            currentLine = br.readLine();
        }
        return setOfUniqueWords;
    }
}