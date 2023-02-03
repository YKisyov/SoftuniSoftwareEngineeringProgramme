package MultidimensionalArraysExercises;

import java.util.ArrayDeque;
import java.util.Scanner;

public class RadioactiveMutantVampireBunnies {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayDeque<Character> commandsToControlPlayerMovement = new ArrayDeque<Character>();
        String[] matrixSize = scan.nextLine().split("\\s");
        final int ROWS, COLS;
        ROWS = Integer.parseInt(matrixSize[0]);
        COLS = Integer.parseInt(matrixSize[1]);
        final char PLAYER_FLAG = 'P';
        final char BUNNY_FLAG = 'B';
        char[][] lair = new char[ROWS][COLS];
        int[] playerPosition = new int[2];

        for (int i = 0; i < ROWS; i++) {
            String matrixLine = scan.nextLine();
            for (int j = 0; j < COLS; j++) {
                lair[i][j] = matrixLine.charAt(j);
                if (matrixLine.charAt(j) == PLAYER_FLAG) {
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                }
            }
        }
        // printOutLair(lair);

        String commands = scan.nextLine();
        for (int i = 0; i < commands.length(); i++) {
            commandsToControlPlayerMovement.offer(commands.charAt(i));
        }
        boolean isPlayerAlive = true;
        boolean didPlayerWin = false;

        do {
                char cmd = commandsToControlPlayerMovement.poll();
                int[] newPlayerPosition = calculatePlayersPositionAfterMoveCmd(playerPosition, cmd);

                redrawBunniesByMultiplyingThem(lair, BUNNY_FLAG);
                if (isPlayerOutOfLair(newPlayerPosition, ROWS, COLS)) {

                    didPlayerWin = true;
                    //set previews turn (playerPosition var) to display "." in the lair matrix;
                    if (lair[playerPosition[0]][playerPosition[1]] != BUNNY_FLAG) {
                        lair[playerPosition[0]][playerPosition[1]] = '.';
                    }
                } else {
                    //players' NewPosition is valid = player is still in the lair
                    if (isThereBunnyAt(newPlayerPosition, lair, BUNNY_FLAG)) {
                        //there is or already there was a bunny in the player's new position, so the player dies;
                        isPlayerAlive = false;
                    } else {
                        //update old's player position with . and place PLAYER_FLAG on the newPlayerPossition
                        redrawPlayer(playerPosition, newPlayerPosition, lair, PLAYER_FLAG, BUNNY_FLAG);
                    }
                    playerPosition = newPlayerPosition;
                }
        }while (isPlayerAlive && !didPlayerWin && !commandsToControlPlayerMovement.isEmpty());

        if (isPlayerAlive) {
            printOutLair(lair);
            System.out.printf("won: %d %d%s", playerPosition[0], playerPosition[1], System.lineSeparator());
        } else {
            printOutLair(lair);
            System.out.printf("dead: %d %d%s", playerPosition[0], playerPosition[1], System.lineSeparator());
        }
    }

    private static void printOutLair(char[][] lair) {
        for (int i = 0; i < lair.length; i++) {
            for (int j = 0; j < lair[i].length; j++) {
                System.out.print(lair[i][j]);
            }
            System.out.println();
        }
        // System.out.println("==============");
    }

    private static boolean isThereBunnyAt(int[] playerPosition, char[][] lair, char BUNNY_FLAG) {
        return lair[playerPosition[0]][playerPosition[1]] == BUNNY_FLAG;
    }

    private static void redrawBunniesByMultiplyingThem(char[][] lair, char BUNNY_FLAG) {
        char flagToAVoidOverlapping = 'b';
        for (int i = 0; i < lair.length; i++) {
            for (int j = 0; j < lair[i].length; j++) {
                if (lair[i][j] == BUNNY_FLAG) {
                    if (i - 1 >= 0
                            && lair[i - 1][j] != BUNNY_FLAG) {
                        lair[i - 1][j] = flagToAVoidOverlapping;
                    }
                    if (i + 1 < lair.length
                            && lair[i + 1][j] != BUNNY_FLAG) {
                        lair[i + 1][j] = flagToAVoidOverlapping;
                    }
                    if (j - 1 >= 0
                            && lair[i][j - 1] != BUNNY_FLAG) {
                        lair[i][j - 1] = flagToAVoidOverlapping;
                    }
                    if (j + 1 < lair[i].length
                            && lair[i][j + 1] != BUNNY_FLAG) {
                        lair[i][j + 1] = flagToAVoidOverlapping;
                    }
                    // printOutLair(lair);
                }
            }
        }
        for (int i = 0; i < lair.length; i++) {
            for (int j = 0; j < lair[i].length; j++) {
                if (lair[i][j] == flagToAVoidOverlapping) {
                    lair[i][j] = BUNNY_FLAG;
                }
            }
        }
        // printOutLair(lair);

    }

    private static void redrawPlayer(int[] playersPosition, int[] newPlayerPosition, char[][] lair, char PLAYER_FLAG, char BUNNY_FLAG) {
        if (lair[playersPosition[0]][playersPosition[1]] != BUNNY_FLAG) {
            lair[playersPosition[0]][playersPosition[1]] = '.';
        }
        lair[newPlayerPosition[0]][newPlayerPosition[1]] = PLAYER_FLAG;
    }

    private static int[] calculatePlayersPositionAfterMoveCmd(int[] playersPosition, char cmd) {
        int updatedRow = 0;
        int updatedCol = 0;
        switch (cmd) {
            case 'U':
                updatedRow = playersPosition[0] - 1;
                updatedCol = playersPosition[1];
                break;
            case 'D':
                updatedRow = playersPosition[0] + 1;
                updatedCol = playersPosition[1];
                break;
            case 'L':
                updatedRow = playersPosition[0];
                updatedCol = playersPosition[1] - 1;
                break;
            case 'R':
                updatedRow = playersPosition[0];
                updatedCol = playersPosition[1] + 1;
                break;
        }
        return new int[]{updatedRow, updatedCol};
    }

    static boolean isPlayerOutOfLair(int[] newPlayerPosition, int ROWS, int COLS) {
        //Check if the newPosition is outta the lair, if so the player is winner and further actions has to taken
        return (newPlayerPosition[0] < 0 || newPlayerPosition[0] >= ROWS)
                || (newPlayerPosition[1] < 0 || newPlayerPosition[1] >= COLS);
    }
}