package WorkingWithAbstractionLab.StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        String userInput = scanner.nextLine();
        while (!userInput.equals("Exit")) {
            studentSystem.analyzeUserInput(userInput);
            userInput = scanner.nextLine();
        }
    }
}
