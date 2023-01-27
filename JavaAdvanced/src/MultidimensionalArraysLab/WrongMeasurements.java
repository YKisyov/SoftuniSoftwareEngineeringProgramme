package MultidimensionalArraysLab;

import java.util.Arrays;
import java.util.Scanner;

public class WrongMeasurements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rowsMaxSize = Integer.parseInt(scan.nextLine());
        int[][] matrix = new int[rowsMaxSize][];

        for (int i = 0; i < rowsMaxSize; i++) {
            int[] arr = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }
        int[][] outputMatrix = new int[rowsMaxSize][matrix[0].length];

        int[] indexOfWrongValue = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int wrongValue = matrix[indexOfWrongValue[0]][indexOfWrongValue[1]];

        for (int currentRow = 0; currentRow < matrix.length; currentRow++) {
            for (int currenCol = 0; currenCol < matrix[currentRow].length; currenCol++) {
                if (matrix[currentRow][currenCol] == wrongValue) {
                    int correctedValue = calculateSumOfSurroundingUpDownLeftRightElements(currentRow, currenCol, matrix);
                    outputMatrix[currentRow][currenCol] = correctedValue;
                }
                 else {
                    outputMatrix[currentRow][currenCol] = matrix[currentRow][currenCol];
                }
            }
        }

        for (int i = 0; i < outputMatrix.length; i++) {
            for (int j = 0; j < outputMatrix[0].length; j++) {
                System.out.print(outputMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static int calculateSumOfSurroundingUpDownLeftRightElements(int currentRow, int currenCol, int[][] matrix) {
        int sum = 0;
        int wrongValue = matrix[currentRow][currenCol];

        //safely add the upper element;
        if (currentRow - 1 >= 0) {
            int upperElementValue = matrix[currentRow - 1][currenCol];
            sum += upperElementValue == wrongValue ? 0 : upperElementValue;
        }
        //safely add the element situated down of the wrong one;
        if (currentRow + 1 <= matrix.length - 1) {
            int lowerElementValue = matrix[currentRow + 1][currenCol];
            sum += lowerElementValue == wrongValue ? 0 : lowerElementValue;
        }

        //safely add the element situated left of the wrong one;
        if (currenCol - 1 >= 0) {
            int leftElementValue = matrix[currentRow][currenCol - 1];
            sum += leftElementValue == wrongValue ? 0 : leftElementValue;
        }

        //safely add the element situated right of the wrong one;
        if (currenCol + 1 <= matrix[currentRow].length - 1) {
            int rightElementValue = matrix[currentRow][currenCol + 1];
            sum += rightElementValue == wrongValue ? 0 : rightElementValue;
        }

        return sum;
    }
}
