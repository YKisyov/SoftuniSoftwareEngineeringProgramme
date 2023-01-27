package MultidimensionalArraysLab;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        int[][] sqrMatrix = new int[size][size];
        //fill the matrix
        for (int i = 0; i < size; i++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            sqrMatrix[i] = arr;
        }

        //PrintPrimeDiagonal
        for (int row = 0, col = 0; row < size; row++, col++) {
            System.out.print(sqrMatrix[row][col] + " ");
        }
        System.out.println();
        //Print the nonPrime diagonal:
        for (int row = size - 1, col = 0; row >= 0; row--, col++) {
            System.out.print(sqrMatrix[row][col] + " ");
        }
    }
}
