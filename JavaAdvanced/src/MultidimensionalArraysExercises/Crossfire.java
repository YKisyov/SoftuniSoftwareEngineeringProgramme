package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class Crossfire {
    static int DESTROYED_CELL_FLAG;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] matrixSize = Arrays.stream(scan.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        DESTROYED_CELL_FLAG = (matrixSize[0] * matrixSize[1]) + 1; //Plays crucial role in collapsing the game board
        int[][] battleField = new int[matrixSize[0]][matrixSize[1]];
        fillMatrixWithSequentialNumbers(battleField, 1);
        String userCmd = scan.nextLine();
        while (!userCmd.equals("Nuke it from orbit")) {
            int[] tokenizedCommand = Arrays.stream(userCmd.split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int targetRowCoordinate, targetColCoordinate, radius;
            targetRowCoordinate = tokenizedCommand[0];
            targetColCoordinate = tokenizedCommand[1];
            radius = tokenizedCommand[2];

            if (!isTokenValid(tokenizedCommand, matrixSize)) {
                userCmd = scan.nextLine();
                continue;
            }

            executeCrossfireAttack(targetRowCoordinate, targetColCoordinate, radius, battleField, matrixSize);
            collapseBattleFieldByPseudoRemovingDestroyedCells(battleField);

            userCmd = scan.nextLine();
        }
        printOutCurrentMatrixState(battleField);
    }

    private static boolean isTokenValid(int[] tokenizedCommand, int[] matrixSize) {
        if (tokenizedCommand[0] >= 0
                && tokenizedCommand[1] >= 0
                && tokenizedCommand[2] >= 0
                && tokenizedCommand[0] <= matrixSize[0]
                && tokenizedCommand[1] <= matrixSize[1]) {
            return true;
        }
        return false;
    }

    private static void executeCrossfireAttack(int targetRowCoordinate, int targetColCoordinate, int radius, int[][] battleField, int[] matrixSize) {

        int[] verticalFireLineCoordinates =
                calculateIndexSafeVerticalFirelineCoordinates(targetRowCoordinate, targetColCoordinate, radius, matrixSize);

        int[] horizontalFireLineCoordinates =
                calculateIndexSafeHorizontalFirelineCoordinates(targetRowCoordinate, targetColCoordinate, radius, matrixSize);

        fireVertically(verticalFireLineCoordinates, targetColCoordinate, battleField);
        fireHorizontally(horizontalFireLineCoordinates, targetRowCoordinate, battleField);

    }

    private static void collapseBattleFieldByPseudoRemovingDestroyedCells(int[][] battleField) {
        //This method directly mutates the battleField!!!
        for (int row = 0; row < battleField.length; row++) {
            int[] currentRowToBeShiftedToTheLeft = battleField[row];

            /*All "destroyed" cells are now marked with an integer bigger than any of the elements that are still in game
            in order to "update" the battlefield we will left shift all element by sorting each row;*/

            //Arrays.sort(currentRowToBeShiftedToTheLeft);
            //battleField[row] = currentRowToBeShiftedToTheLeft;
        }

        //removing any rows whos' element might be marked as DESTROYED_CELL_FLAG by sorting all columns;
        //printOutCurrentMatrixState(battleField);
        for (int col = 0; col < battleField[0].length; col++) {
            //TODO
            int[] extractedCol = getColumn(battleField, col);
            Arrays.sort(extractedCol);
            for (int i = 0; i < battleField.length; i++) {
                //Overwrite the col-column
                battleField[i][col] = extractedCol[i];
            }
        }
        //todo mask with comments if this horizontal collapse works;
        //printOutCurrentMatrixState(battleField);
    }

    public static int[] getColumn(int[][] array, int columnIndex) {
        /*This method is inspired by some code from stackOverflow and is not mine */
        int[] column = new int[array[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][columnIndex];
        }
        return column;
    }

    private static void fireHorizontally(int[] horizontalFireLineCoordinates, int targetRowCoordinate, int[][] battleField) {
        int lockTheGunAt = targetRowCoordinate;
        for (int gunHorizontalProjectile = horizontalFireLineCoordinates[0];
             gunHorizontalProjectile <= horizontalFireLineCoordinates[1]; gunHorizontalProjectile++) {
            battleField[targetRowCoordinate][gunHorizontalProjectile] = DESTROYED_CELL_FLAG;
        }
        //printOutCurrentMatrixState(battleField);
    }

    private static void fireVertically(int[] verticalFireLineCoordinates, int targetColCoordinate, int[][] battleField) {
        int lockTheGunAt = targetColCoordinate;
        for (int gunVerticalProjectile = verticalFireLineCoordinates[0];
             gunVerticalProjectile <= verticalFireLineCoordinates[1]; gunVerticalProjectile++) {
            battleField[gunVerticalProjectile][lockTheGunAt] = DESTROYED_CELL_FLAG;
        }
        //printOutCurrentMatrixState(battleField);
    }

    private static int[] calculateIndexSafeVerticalFirelineCoordinates(int targetRowCoordinate, int targetColCoordinate, int radius, int[] matrixSize) {
        int verticalRowStartPosition = targetRowCoordinate - radius;
        if (verticalRowStartPosition < 0) {
            verticalRowStartPosition = 0;
        }
        //check the remaining down part of the vertical fire-line:
        int verticalRowEndPosition = targetRowCoordinate + radius;
        if (verticalRowEndPosition > matrixSize[0] - 1) {
            verticalRowEndPosition = matrixSize[0] - 1;
        }
        return new int[]{verticalRowStartPosition, verticalRowEndPosition};
    }

    private static int[] calculateIndexSafeHorizontalFirelineCoordinates(int targetRowCoordinate, int targetColCoordinate, int radius, int[] matrixSize) {
        int horizontalFirelineStartPosition = targetColCoordinate - radius;
        if (horizontalFirelineStartPosition < 0) {
            horizontalFirelineStartPosition = 0;
        }
        //check the remaining down part of the horizontal fire-line:
        int horizontalFirelineEndPosition = targetColCoordinate + radius;
        if (horizontalFirelineEndPosition > matrixSize[1] - 1) {
            horizontalFirelineEndPosition = matrixSize[1] - 1;
        }
        return new int[]{horizontalFirelineStartPosition, horizontalFirelineEndPosition};
    }


    private static void fillMatrixWithSequentialNumbers(int[][] matrix, int fillerInitialValue) {
        int fillerValue = fillerInitialValue;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = fillerValue;
                fillerValue++;
            }
        }
        //printOutCurrentMatrixState(matrix);
    }

    private static void printOutCurrentMatrixState(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            boolean didYouPrintAnyValidCells = false;
            for (int col = 0; col < matrix[0].length; col++) {

                if (matrix[row][col] != DESTROYED_CELL_FLAG) { // Omit Destroyed elements
                    System.out.print(matrix[row][col] + " ");
                    didYouPrintAnyValidCells = true;
                }
            }
            if (didYouPrintAnyValidCells) {
                System.out.println();
            }
        }
    }

}
