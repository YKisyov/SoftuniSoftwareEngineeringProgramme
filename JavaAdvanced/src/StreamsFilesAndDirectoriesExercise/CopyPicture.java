package StreamsFilesAndDirectoriesExercise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyPicture {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("res/Files-and-Streams-Exercises-Res/CC_license_chooser_v2.png");

        FileOutputStream fileOutputStream = new FileOutputStream("res/Files-and-Streams-Exercises-Res/Copy_of_CC_license_chooser_v2.png");

        byte[] buffer = new byte[1024];

        while (fileInputStream.read(buffer) != -1) {
            fileOutputStream.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

}
