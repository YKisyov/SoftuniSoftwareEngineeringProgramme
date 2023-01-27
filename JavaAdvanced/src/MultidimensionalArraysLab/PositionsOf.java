package MultidimensionalArraysLab;

import java.util.Arrays;
import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] rowColValues = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = new int[rowColValues[0]][rowColValues[1]];
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }


        boolean wasItAtLeastOneMatchingValueFound = false;
        int lookForValue = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (lookForValue == matrix[i][j]) {
                    wasItAtLeastOneMatchingValueFound = true;
                    System.out.println(i + " " + j);
                }
            }
        }
        if (!wasItAtLeastOneMatchingValueFound){
            System.out.println("not found");
        }
    }
}
