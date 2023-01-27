package MultidimensionalArraysExercises;

import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] inputAsStrArr = scan.nextLine().split(", ");
        int matrixSize = Integer.parseInt(inputAsStrArr[0]);
        char fillerType = inputAsStrArr[1].charAt(0);
        int[][] matrix = new int[matrixSize][matrixSize];

        if (fillerType == 'A'){
            fillUsingMethodA(matrix);
        }
        if (fillerType == 'B'){
            fillUsingMethodB(matrix);
        }
        printOutTheMatrix(matrix);
    }

    private static void printOutTheMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fillUsingMethodA(int[][] matrix) {
        int incrementedValue = 1;
        for (int col = 0; col < matrix.length; col++) {
            for(int row = 0; row < matrix[col].length; row++){
                matrix[row][col] = incrementedValue++;
            }
        }

    }

    private static void fillUsingMethodB(int[][] matrix) {
        int incrementedValue = 1;
        for (int col = 0; col < matrix.length; col++) {
            if (col % 2 == 0)
            for(int row = 0; row < matrix[col].length; row++){
                matrix[row][col] = incrementedValue++;
            } else{
                for(int row = matrix.length-1; row >= 0; row--){
                    matrix[row][col] = incrementedValue++;
                }
            }
        }
    }

}
