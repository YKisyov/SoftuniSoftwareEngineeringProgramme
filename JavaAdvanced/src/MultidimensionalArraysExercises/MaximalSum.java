package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int innerMatrixSize = 3; //by 3 as per requirement
        int[] matrixSize = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = new int[matrixSize[0]][matrixSize[1]];

        for (int i = 0; i < matrixSize[0]; i++) {
            int[] arr = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }

        //Init data:
        int everKnownMaxSum, currentMaxSum;
        int[] upperLeftCoordinatesOfTheBiggestKnownSum = new int[]{0, 0};
        everKnownMaxSum = calculateSum(0, 0, innerMatrixSize, matrix);

        for (int row = 0; row <= matrix.length - 1; row++) {
            for (int col = 0; col <= matrix[row].length - 1; col++) {
                currentMaxSum = calculateSum(row, col, innerMatrixSize, matrix);
                if (currentMaxSum > everKnownMaxSum) {  //TODO add >= in case we are looking for the most reaceunt sum
                    everKnownMaxSum = currentMaxSum;
                    upperLeftCoordinatesOfTheBiggestKnownSum[0] = row;
                    upperLeftCoordinatesOfTheBiggestKnownSum[1] = col;
                }
            }
        }

        System.out.println("Sum = " + everKnownMaxSum);
        for (int i = upperLeftCoordinatesOfTheBiggestKnownSum[0]; i < upperLeftCoordinatesOfTheBiggestKnownSum[0] + innerMatrixSize; i++) {
            for (int j = upperLeftCoordinatesOfTheBiggestKnownSum[1]; j < upperLeftCoordinatesOfTheBiggestKnownSum[1] + innerMatrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    static int calculateSum(int statingRow, int startingCol, int innerMatrixSize, int[][] matrix) {
        int sum = 0;
        if ((statingRow + innerMatrixSize - 1 <= matrix.length - 1)
                && (startingCol + innerMatrixSize - 1 <= matrix[0].length - 1)) {
            for (int i = statingRow; i <= statingRow + innerMatrixSize - 1; i++) {
                for (int j = startingCol; j <= startingCol + innerMatrixSize - 1; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }
        return 0;
    }
}
