package MultidimensionalArraysLab;

import java.util.Scanner;

public class FindTheRealQueen {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final int MATRIX_SIZE = 8;
        final char TARGETED_FIGURE = 'q';
        char[][] chessBoard = new char[8][8];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            String[] lineWithChars = scan.nextLine().split("\\s+");
            char[] arr = new char[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                arr[j] = lineWithChars[j].charAt(0);
            }
            chessBoard[i] = arr;
        }

        for (int currentRow = 0; currentRow < chessBoard.length; currentRow++) {
            startAgain:
            for (int currentCol = 0; currentCol < chessBoard[currentRow].length; currentCol++) {
                char currentFigure = chessBoard[currentRow][currentCol];
                if (currentFigure == TARGETED_FIGURE) {

                    if (isTargetedFigurePresentOnlyOnceInThisRow(chessBoard, currentRow, TARGETED_FIGURE)
                            && isTargetedFigurePresentOnlyOnceInThisCol(chessBoard, currentCol, TARGETED_FIGURE)
                            && isTargetedFigurePresentOnlyOnceInPrimeDiagonal(chessBoard, currentRow, currentCol, TARGETED_FIGURE)
                            && isTargetedFigurePresentOnlyOnceInQuasiPrimeDiagonal(chessBoard, currentRow, currentCol, TARGETED_FIGURE)) {

                        System.out.printf("%s %s\n", currentRow, currentCol);
                        return;
                    }
                    //check how many times we find the MATCHING_FIGURE in the ROW that the currentFigure belongs to
                    //isTargetedFigurePresentOnlyOnceInThisRow(chessBoard, currentRow, TARGETED_FIGURE);

                    //check how many times we find the MATCHING_FIGURE in the COL that MATCHING_FIGURE belongs
                    //isTargetedFigurePresentOnlyOnceInThisCol(chessBoard, currentCol, TARGETED_FIGURE);

                    //check how many times we find the MATCHING_FIGURE in the Prime diagonal that MATCHING_FIGURE belongs to
                    //isTargetedFigurePresentOnlyOnceInPrimeDiagonal(chessBoard, currentRow, currentCol, TARGETED_FIGURE);

                    //check how many times we find the MATCHING_FIGURE in the Prime diagonal that MATCHING_FIGURE belongs to
                    //isTargetedFigurePresentOnlyOnceInQuasiPrimeDiagonal(chessBoard, currentRow, currentCol, TARGETED_FIGURE);


                }
            }
        }
    }

    private static boolean isTargetedFigurePresentOnlyOnceInQuasiPrimeDiagonal(char[][] chessBoard, int currentRow, int currentCol, char targetedFigure) {
        //checks how many times we find the MATCHING_FIGURE in the Quasi-prime diagonal that MATCHING_FIGURE belongs to
        // A quasiPrime diagonal is the opposite of the Prime diagonal and its tilt looks like a forwardSlash - "/"
        int countOccasions = 0;
        int[] startingPointOfQuasiDiagonal = calculateStarPositionOfQuasiSecondaryDiagonal(currentRow, currentCol, chessBoard.length);
        int i = startingPointOfQuasiDiagonal[0];
        int j = startingPointOfQuasiDiagonal[1];

        do {
            try {
                if (chessBoard[i][j] == targetedFigure) {
                    countOccasions++;
                    if (countOccasions > 1) {
                        return false;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            i++;
            j--;
        } while (true);
        //System.out.println("found only once in the belonging Prime diagonal: " + currentRow + " " + currentCol);
        return true;
    }

    static boolean isTargetedFigurePresentOnlyOnceInPrimeDiagonal(char[][] chessBoard, int currentRow, int currentCol, char targetedFigure) {
        //checks how many times we find the MATCHING_FIGURE in the Prime diagonal that MATCHING_FIGURE belongs to
        int countOccasions = 0;
        int[] startingPointOfPrimeDiagonal = calculateStartPositionOfPrimaryDiagonal(currentRow, currentCol);
        int i = startingPointOfPrimeDiagonal[0];
        int j = startingPointOfPrimeDiagonal[1];

        if (chessBoard[i][j] == targetedFigure) {
            countOccasions++;
            //Starting char is already checked;
        }

        while (i < chessBoard.length - 1 && j < chessBoard[0].length - 1) {
            if (chessBoard[i + 1][j + 1] == targetedFigure) {
                countOccasions++;
            }
            if (countOccasions > 1) {
                return false;
            }
            i++;
            j++;
        }
        //System.out.println("found only once in the belonging Prime diagonal: " + currentRow + " " + currentCol);
        return true;
    }


    static boolean isTargetedFigurePresentOnlyOnceInThisCol(char[][] chessBoard, int currentCol, char TARGETED_FIGURE) {
        int countOccasions = 0;
        for (int rowsInThisField = 0; rowsInThisField < chessBoard.length; rowsInThisField++) {
            if (chessBoard[rowsInThisField][currentCol] == TARGETED_FIGURE) {
                countOccasions++;
            }
            if (countOccasions > 1) {
                return false;
            }
        }
        //System.out.println(TARGETED_FIGURE + " was found only once in column: " + currentCol);
        return true;
    }

    static boolean isTargetedFigurePresentOnlyOnceInThisRow(char[][] chessBoard, int currentRow, char TARGETED_FIGURE) {
        int countOccasions = 0;
        for (int colsInThisRecord = 0; colsInThisRecord < chessBoard.length; colsInThisRecord++) {
            if (chessBoard[currentRow][colsInThisRecord] == TARGETED_FIGURE) {
                countOccasions++;
                if (countOccasions > 1) {
                    return false;
                }
            }
        }
        //System.out.println("Is present only once in the belonging Row: " + " " + currentRow);
        return true;
    }

    static int[] calculateStartPositionOfPrimaryDiagonal(int i, int j) {
        if (i == 0 || j == 0) {
            return new int[]{i, j};
        } else {
            return calculateStartPositionOfPrimaryDiagonal(i - 1, j - 1);
        }
    }

    static int[] calculateStarPositionOfQuasiSecondaryDiagonal(int i, int j, int rowsSize) {
        if (i == 0 || j == rowsSize - 1) {
            //System.out.println("Quasi diagonal starts at: " + i + " " + j);
            return new int[]{i, j};
        } else return calculateStarPositionOfQuasiSecondaryDiagonal(i - 1, j + 1, rowsSize);
    }

}

