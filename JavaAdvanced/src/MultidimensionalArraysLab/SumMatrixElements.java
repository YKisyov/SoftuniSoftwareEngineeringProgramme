package MultidimensionalArraysLab;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] rowsColsDims = Arrays.stream(scan.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows, cols;
        rows = rowsColsDims[0];
        cols= rowsColsDims[1];
        
        //MatrixFiller 
        int[][] matrix = new int[rows][cols];
        for (int currentRow = 0; currentRow < matrix.length; currentRow++) {
            int[] rowElements = Arrays.stream(scan.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[currentRow] = rowElements;
        }
        
        int sumOfAllElementsInTheMatrix = 0;
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentCol = 0; currentCol < cols; currentCol++) {
                sumOfAllElementsInTheMatrix += matrix[currentRow][currentCol];
            }
        }
        System.out.printf("%d\n" + "%d\n" + "%d\n", rows, cols, sumOfAllElementsInTheMatrix);
    }
}
