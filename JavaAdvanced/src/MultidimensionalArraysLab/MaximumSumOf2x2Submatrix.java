package MultidimensionalArraysLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOf2x2Submatrix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] dims = Arrays.stream(scan.nextLine().split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rowsSize, colsSize;
        rowsSize = dims[0];
        colsSize = dims[1];

        int[][] mainMatrix = new int[rowsSize][colsSize];
        for (int currentRow = 0; currentRow < rowsSize; currentRow++) {
            int[] extractedNumbers = Arrays.stream(scan.nextLine().split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            mainMatrix[currentRow] = extractedNumbers;
        }

        final int INNER_MATRIX_ROWS_SIZE = 2;
        final int INNER_MATRIX_COLS_SIZE = 2;

        int safetyRowCorrection = INNER_MATRIX_ROWS_SIZE;
        int safetyColCorrection = INNER_MATRIX_COLS_SIZE;

        int[][] suspectedMoldWithMaxValues = new int[INNER_MATRIX_ROWS_SIZE][INNER_MATRIX_ROWS_SIZE];
        int maxMoldSumEverKnown = 0;
        //Assume that the very first submatrix has the biggest sum;
        for (int i = 0; i < INNER_MATRIX_ROWS_SIZE; i++) {
            for (int j = 0; j < INNER_MATRIX_COLS_SIZE; j++) {
                maxMoldSumEverKnown += mainMatrix[i][j];
                suspectedMoldWithMaxValues[i][j] = mainMatrix[i][j];
            }
        }


        int currentMoldsSum = 0;
        ArrayList<Integer> listOfAllValuesOfTemporaryMoldMatrix = new ArrayList<>(INNER_MATRIX_ROWS_SIZE * INNER_MATRIX_COLS_SIZE);
        for (int currentRow = 0; currentRow <= mainMatrix.length - safetyRowCorrection; currentRow++) {
            for (int currentCol = 0; currentCol <= mainMatrix[currentRow].length - safetyColCorrection; currentCol++) {
                 for (int i = 0; i < INNER_MATRIX_ROWS_SIZE; i++) {
                    for (int j = 0; j < INNER_MATRIX_COLS_SIZE; j++) {
                        currentMoldsSum += mainMatrix[currentRow + i][currentCol + j];
                        listOfAllValuesOfTemporaryMoldMatrix.add(mainMatrix[currentRow + i][currentCol + j]);
                    }
                }
                if (currentMoldsSum > maxMoldSumEverKnown) {
                  /*A new maximum is found! So we have to 1) update the maxMoldSumEverKnown with the currenMoldSum
                 and rebuild the suspectedMoldWithMaxValues by updating it from index[0][0] */

                    maxMoldSumEverKnown = currentMoldsSum;
                    var listIterator = listOfAllValuesOfTemporaryMoldMatrix.listIterator();
                    for (int i = 0; i < INNER_MATRIX_ROWS_SIZE; i++) {
                        for (int j = 0; j < INNER_MATRIX_COLS_SIZE; j++) {
                            suspectedMoldWithMaxValues[i][j] = listIterator.next();
                        }
                    }
                }
                currentMoldsSum = 0;
                listOfAllValuesOfTemporaryMoldMatrix.clear();
            }
        }

        for (int i = 0; i < suspectedMoldWithMaxValues.length; i++) {
            for (int j = 0; j < suspectedMoldWithMaxValues[i].length; j++) {
                System.out.print(suspectedMoldWithMaxValues[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maxMoldSumEverKnown);

    }
}

