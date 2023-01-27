package MultidimensionalArraysExercises;

import java.util.ArrayList;
import java.util.Scanner;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        cmd = cmd.substring(cmd.indexOf('(') + 1, cmd.indexOf(')'));
        int rotateAt = Integer.parseInt(cmd);
        if (rotateAt > 360) {
            rotateAt = rotateAt / 90;
            rotateAt %= 4;
        }

        ArrayList<String> storedInput = new ArrayList<>();
        String matrixContent;
        matrixContent = scan.nextLine();
        while (!matrixContent.equals("END")) {
            storedInput.add(matrixContent);
            matrixContent = scan.nextLine();
        }
        ;

        int rows = storedInput.size();
        int cols = storedInput.get(0).length();
        for (String entry : storedInput) {
            if (entry.length() > cols) {
                cols = entry.length();
            }
        }

        //getting away from the jagged matrix by using a rectangular one;
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            String entry = storedInput.get(i);
            for (int j = 0; j < matrix[0].length; j++) {
                if (j < entry.length()) {
                    matrix[i][j] = entry.charAt(j);
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

        switch (rotateAt) {
            case 0:
                printTheOriginalMatrix(matrix);
                //print the matrix //todo;
                break;

            case 90:
            case 1:
                printRotationAt90DegClockwise(matrix);
                break;
            case 180:
            case 2:
                printRotationAt180DegClockwise(matrix);
                break;

            case 270:
            case 3:
                printRotationAt270DegClockwise(matrix);
                break;
        }
    }

    private static void printTheOriginalMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    static void printRotationAt90DegClockwise(char[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            for (int k = matrix.length - 1; k >= 0; k--) {
                System.out.print(matrix[k][j]);
            }
            System.out.println();
        }
    }

    static void printRotationAt180DegClockwise(char[][] matrix) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }

    static void printRotationAt270DegClockwise(char[][] matrix) {

        for (int crawlingProgress = matrix[0].length - 1; crawlingProgress >= 0; crawlingProgress--) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][crawlingProgress]);
            }
            System.out.println();
        }
    }


}

