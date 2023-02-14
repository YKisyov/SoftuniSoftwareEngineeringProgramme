package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteToFile {
    public static void main(String[] args) throws IOException {
        String resPath = "res/input.txt";

        FileInputStream fileInputStream = new FileInputStream(resPath);
        ArrayList<Character> punctuationDefinitionList = new ArrayList<>(
                Arrays.asList(',', '.', '?', '!'));

        String resPathToFileOutput = "res/output.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(resPathToFileOutput);

        int singleByteAsInt;

        while (fileInputStream.available() > 0) {
            singleByteAsInt = fileInputStream.read();
            if (punctuationDefinitionList.contains((char) singleByteAsInt)) {
                continue;
            }
            fileOutputStream.write(singleByteAsInt);
            System.out.print( (char) singleByteAsInt);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }

}
