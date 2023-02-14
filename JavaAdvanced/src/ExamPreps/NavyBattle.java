package ExamPreps;

import java.util.Scanner;

public class NavyBattle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isSubmarineSank = false;
        char BATTLE_CRUISER_FLAG = 'C';
        char NAVAL_MANE_FLAG = '*';
        final int TOTAL_BATTLE_CRUISERS_WHO_HAS_TO_BE_DESTROYED_TO_ACCOMPLISH_MISSION = 3;
        final int MAX_HIT_COUNT_THAT_WILL_SANK_THE_SUBMARINE = 3;
        int destroyedBattleCruisers = 0;
        int hitsByNavalMines = 0;
        int submarineYPosition = 0;
        int submarineXPosition = 0;


        int seaSize = Integer.parseInt(scan.nextLine());
        char[][] sea = new char[seaSize][seaSize];
        for (int i = 0; i < seaSize; i++) {
            String lineInput = scan.nextLine();
            for (int j = 0; j < seaSize; j++) {
                sea[i][j] = lineInput.charAt(j);
                if (sea[i][j] == 'S') {
                    submarineYPosition = i;
                    submarineXPosition = j;
                }
            }
        }

        do {
            String command = scan.nextLine();
            int[] newXYPosition = calculateNewPosition(command, submarineYPosition, submarineXPosition);
            int newY = newXYPosition[0];
            int newX = newXYPosition[1];
            if (sea[newY][newX] == BATTLE_CRUISER_FLAG) {
                eraseSubmarineOldFlag(sea, submarineYPosition, submarineXPosition);
                markSubmarineNewFlag(sea, newY, newX);
                destroyedBattleCruisers++;
                submarineYPosition = newY;
                submarineXPosition = newX;
            } else {
                if (sea[newY][newX] == NAVAL_MANE_FLAG) {
                    hitsByNavalMines++;
                    if (hitsByNavalMines >= MAX_HIT_COUNT_THAT_WILL_SANK_THE_SUBMARINE) {
                        isSubmarineSank = true;
                        printFinalGameScore(isSubmarineSank, newY, newX);
                    }
                    eraseSubmarineOldFlag(sea, submarineYPosition, submarineXPosition);
                    markSubmarineNewFlag(sea, newY, newX);
                    submarineYPosition = newY;
                    submarineXPosition = newX;
                }
            }
            if (sea[newX][newY] == '-') {
                eraseSubmarineOldFlag(sea, submarineYPosition, submarineXPosition);
                markSubmarineNewFlag(sea, newY, newX);
                submarineYPosition = newY;
                submarineXPosition = newX;
            }
        } while (destroyedBattleCruisers < TOTAL_BATTLE_CRUISERS_WHO_HAS_TO_BE_DESTROYED_TO_ACCOMPLISH_MISSION
                && !isSubmarineSank);
        printSea(sea);
    }

    private static void printSea(char[][] sea) {
        for (int i = 0; i < sea.length; i++) {
            for (int j = 0; j < sea.length; j++) {
                System.out.print(sea[i][j]);
            }
            System.out.println();
        }
    }

    private static void printFinalGameScore(boolean isSubmarineSank, int newY, int newX) {
        if (isSubmarineSank) {
            System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n", newY, newX);
        } else {
            System.out.printf("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
        }
    }


    private static void markSubmarineNewFlag(char[][] sea, int newY, int newX) {
        sea[newY][newX] = 'S';
    }

    private static void eraseSubmarineOldFlag(char[][] sea, int submarineYPosition, int submarineXPosition) {
        sea[submarineYPosition][submarineXPosition] = '-';
    }

    static int[] calculateNewPosition(String command, int currentPositionY, int currentPositionX) {
        int y = currentPositionY;
        int x = currentPositionX;
        switch (command) {
            case "up":
                y++;
                break;
            case "down":
                y--;
                break;
            case "left":
                x--;
                break;
            case "right":
                x++;
                break;
        }
        return new int[]{y, x};
    }
}