package MultidimensionalArraysLab;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rowSize = Integer.parseInt(scan.nextLine());
        int colSize = Integer.parseInt(scan.nextLine());
        char[][] m1, m2;
        m1 = new char[rowSize][colSize];
        m2 = new char[rowSize][colSize];

        matrixFiller(scan, rowSize, colSize, m1);
        matrixFiller(scan, rowSize, colSize, m2);


        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (m1[i][j] != m2[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print(m1[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void matrixFiller(Scanner scan, int rowSize, int colSize, char[][] m2) {
        for (int currentRow = 0; currentRow < rowSize; currentRow++) {
            String[] str = scan.nextLine().split("\\s+");
            char[] arr = new char[colSize];
            for (int currentCol = 0; currentCol < colSize; currentCol++) {
                arr[currentCol] = str[currentCol].charAt(0);
            }
            m2[currentRow] = arr;
        }
    }
}