package MultidimensionalArraysLab;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimOfFirstMatrix = Arrays.stream(scanner.nextLine().split("\\s+")).
                mapToInt(Integer::parseInt)
                .toArray();
        int[][] firstMatrix = new int[dimOfFirstMatrix[0]][dimOfFirstMatrix[1]];

        for (int i = 0; i < dimOfFirstMatrix[0]; i++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            firstMatrix[i] = arr;
        }

        int[] dimOfSecondMatrix = Arrays.stream(scanner.nextLine().split("\\s+")).
                mapToInt(Integer::parseInt)
                .toArray();
        int[][] secondMatrix = new int[dimOfSecondMatrix[0]][dimOfSecondMatrix[1]];

        for (int i = 0; i < dimOfSecondMatrix[0]; i++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            secondMatrix[i] = arr;
        }
        System.out.println(areMatrixEqual(firstMatrix, secondMatrix) ? "equal" : "not equal");
    }

    static boolean areMatrixEqual(int[][] matrixA, int[][] matrixB) {
        if (matrixA.length != matrixB.length ||
                matrixA[0].length != matrixB[0].length) {
            return false;
        } else {
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixA[i].length; j++) {
                    if (matrixA[i][j] != matrixB[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
