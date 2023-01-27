package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scan.nextLine());
        int[][] matrix = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            int[] arr = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }
        int diffOfDiagonals = sumOfPrimeDiagonal(matrix) - sumOfQuasiPrimeDiagonal(matrix);
        diffOfDiagonals = Math.abs(diffOfDiagonals);
        System.out.println(diffOfDiagonals);

    }

    static int sumOfPrimeDiagonal(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    static int sumOfQuasiPrimeDiagonal(int[][] matrix) {
        int sum = 0;
        for (int row = 0, col = matrix.length - 1 ; col >= 0; col--, row++) {
                sum += matrix[row][col];
        }
        return sum;
    }
}
