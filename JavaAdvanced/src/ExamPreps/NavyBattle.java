package ExamPreps;

import java.util.Scanner;

public class NavyBattle {
    private static int matrixSize;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isSubmarineSank = false;
        char BATTLE_CRUISER_FLAG = 'C';
        char NAVAL_MINE_FLAG = '*';
        final int TOTAL_BATTLE_CRUISERS_WHO_HAS_TO_BE_DESTROYED_TO_ACCOMPLISH_MISSION = 3;
        final int MAX_HIT_COUNT_THAT_WILL_SINK_THE_SUBMARINE = 3;
        int destroyedBattleCruisers = 0;
        int hitsByNavalMines = 0;
        int submarineRowPosition = 0;
        int submarineColPosition = 0;


        matrixSize = Integer.parseInt(scan.nextLine());
        char[][] sea = new char[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            String lineInput = scan.nextLine();
            for (int j = 0; j < matrixSize; j++) {
                sea[i][j] = lineInput.charAt(j);
                if (sea[i][j] == 'S') {
                    submarineRowPosition = i;
                    submarineColPosition = j;
                }
            }
        }

        String command = scan.nextLine();
        int newRow = -1, newCol = -1;

        while (destroyedBattleCruisers < TOTAL_BATTLE_CRUISERS_WHO_HAS_TO_BE_DESTROYED_TO_ACCOMPLISH_MISSION
                && !isSubmarineSank) {


            int[] newRowColPosition = calculateNewPosition(command, submarineRowPosition, submarineColPosition);
            newRow = newRowColPosition[0];
            newCol = newRowColPosition[1];
            if (checkPositionValidity(newRowColPosition)) {

                if (sea[newRow][newCol] == BATTLE_CRUISER_FLAG) {
                    eraseSubmarineOldFlag(sea, submarineRowPosition, submarineColPosition);
                    markSubmarineNewFlag(sea, newRow, newCol);
                    destroyedBattleCruisers++;
                    submarineRowPosition = newRow;
                    submarineColPosition = newCol;
                } else {
                    if (sea[newRow][newCol] == NAVAL_MINE_FLAG) {
                        hitsByNavalMines++;
                        if (hitsByNavalMines >= MAX_HIT_COUNT_THAT_WILL_SINK_THE_SUBMARINE) {
                            isSubmarineSank = true;
                        }
                        eraseSubmarineOldFlag(sea, submarineRowPosition, submarineColPosition);
                        markSubmarineNewFlag(sea, newRow, newCol);
                        submarineRowPosition = newRow;
                        submarineColPosition = newCol;
                    }
                }
                if (sea[newRow][newCol] == '-') {
                    eraseSubmarineOldFlag(sea, submarineRowPosition, submarineColPosition);
                    markSubmarineNewFlag(sea, newRow, newCol);
                    submarineRowPosition = newRow;
                    submarineColPosition = newCol;
                }

              /*        todo remove this
                System.out.println("==================");
                printSea(sea);
                System.out.println("=================");
               */
            } else {
                System.out.println("Submarine outta range");
                break;
            }
            command = scan.nextLine();
        }

        printFinalGameScore(isSubmarineSank, newRow, newCol);
        printSea(sea);
    }

    private static boolean checkPositionValidity(int[] newXYPosition) {
        int x = newXYPosition[0];
        int y = newXYPosition[1];
        return (x >= 0 && x < matrixSize)
                && (y >= 0 && y < matrixSize);
    }

    private static void printSea(char[][] sea) {
        for (int i = 0; i < sea.length; i++) {
            for (int j = 0; j < sea.length; j++) {
                System.out.print(sea[i][j]);
            }
            System.out.println();
        }
    }

    private static void printFinalGameScore(boolean isSubmarineSank, int newX, int newY) {
        if (isSubmarineSank) {
            System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n", newX, newY);
        } else {
            System.out.printf("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!\n");
        }
    }


    private static void markSubmarineNewFlag(char[][] sea, int newRow, int newCol) {
        sea[newRow][newCol] = 'S';
    }

    private static void eraseSubmarineOldFlag(char[][] sea, int submarineRowPosition, int submarineColPosition) {
        sea[submarineRowPosition][submarineColPosition] = '-';
    }

    static int[] calculateNewPosition(String command, int currentRowPosition, int currentColPosition) {
        int row = currentRowPosition;
        int col = currentColPosition;
        switch (command) {
            case "up":
                row = row - 1;
                break;
            case "down":
                row = row + 1;
                break;
            case "left":
                col = col - 1;
                break;
            case "right":
                col = col + 1;
                break;
        }
        return new int[]{row, col};
    }
}