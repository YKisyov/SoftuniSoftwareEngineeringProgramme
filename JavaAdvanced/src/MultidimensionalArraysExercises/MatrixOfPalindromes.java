package MultidimensionalArraysExercises;

import java.util.Scanner;

public class MatrixOfPalindromes {
    static char[] alphabet = new char[26];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        int cols = scan.nextInt();
        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < alphabet.length; i++) {
            char nextChar = (char) ('a' + i);
            alphabet[i] = nextChar;
        }
        fillTheMatrix(matrix);
        printTheMatrix(matrix);

    }

    static void fillTheMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            int i = row;
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = alphabet[row] + "" + alphabet[i] + "" + alphabet[row] + "";
                i++;
            }
        }
    }

    static void printTheMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");

            }
            System.out.println();
        }
    }


}




