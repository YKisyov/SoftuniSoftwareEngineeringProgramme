package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] matrixDims = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        String[][] matrix = new String[matrixDims[0]][matrixDims[1]];

        for (int i = 0; i < matrixDims[0]; i++) {
            String[] lineOfInts = scan.nextLine().split(" ");
            matrix[i] = lineOfInts;
        }

        String cmd = scan.nextLine();
        while (!cmd.split("\\s")[0].equals("END")) {
            String[] tokenizedCmd = cmd.split("\\s");
            if (tokenizedCmd[0].equals("swap") && tokenizedCmd.length == 5) {
                String element1, element2;
                try {
                    int element1coordinateI = Integer.parseInt(tokenizedCmd[1]);
                    int element1coordinateJ = Integer.parseInt(tokenizedCmd[2]);
                    int element2coordinateI = Integer.parseInt(tokenizedCmd[3]);
                    int element2coordinateJ = Integer.parseInt(tokenizedCmd[4]);

                    element1 = matrix[Integer.parseInt(tokenizedCmd[1])][Integer.parseInt(tokenizedCmd[2])];
                    element2 = matrix[Integer.parseInt(tokenizedCmd[3])][Integer.parseInt(tokenizedCmd[4])];

                    matrix[element2coordinateI][element2coordinateJ] = element1;
                    matrix[element1coordinateI][element1coordinateJ] = element2;

                    for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[0].length; j++) {
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                    }

                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {

                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input!");
            }
            cmd = scan.nextLine();
        }
    }

}
