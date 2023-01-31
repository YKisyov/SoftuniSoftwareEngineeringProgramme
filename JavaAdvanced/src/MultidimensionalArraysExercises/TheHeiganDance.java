package MultidimensionalArraysExercises;

import java.util.ArrayDeque;
import java.util.Scanner;

public class TheHeiganDance {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] gameBoard = new int[15][15];
        int[] playerPosition = new int[]{7, 7};
        gameBoard[7][7] = -1;
        String nameOfLastSpellThatKilledPlayer = "";

        double playerHP = 18_500.d;
        double heiganHP = 3_000_000.d;


        double playerAttackValuePerTurn = .0d;
        double cloudPlagueAttack = 3_500.d;
        double eruptionAttack = 6_000.d;
        boolean isBossAlive = true;
        boolean isPlayerAlive = true;
        ArrayDeque<Double> queueOfPostponedDamagePlayerHasToBare = new ArrayDeque<>();
        playerAttackValuePerTurn = Double.parseDouble(scan.nextLine());

        while (isBossAlive && isPlayerAlive ){
            String[] command = commandDispatcher(scan.nextLine());
            String spellType = command[0];
            int castTheSpellAtRow = Integer.parseInt(command[1]);
            int castTheSpellAtColumn = Integer.parseInt(command[2]);
            int[][] cellsUnderAttack = applyCastedSpellToLayerOverGameboard(castTheSpellAtRow, castTheSpellAtColumn);


            heiganHP -= playerAttackValuePerTurn;
            if(heiganHP <= 0){
                isBossAlive = false;
                continue;
            }

            if (queueOfPostponedDamagePlayerHasToBare.size() > 0){
                playerHP -= calculatePenaltyDamageFromPastTurn(queueOfPostponedDamagePlayerHasToBare);
                //System.out.println("Player was hit by postTurnSpell and currently has: " + playerHP + "hp") ;
                if (playerHP <= 0){
                    nameOfLastSpellThatKilledPlayer = "Plague Cloud";
                    isPlayerAlive = false;
                    continue;
                }
            }
            switch (spellType){
                case "Eruption":
                    if (isPlayerInRangeOfTheAttack(cellsUnderAttack, gameBoard)){
                        int[] newPlayerPosition = playerTriesToAvoideSpellMyMovingToNewLocation(playerPosition, cellsUnderAttack);
                        if (newPlayerPosition[0] == playerPosition[0]
                        && newPlayerPosition[1] == playerPosition[1]){
                            //Player takes the damage;
                            playerHP -= eruptionAttack;
                        } else {
                            updateGameboard(newPlayerPosition, playerPosition, gameBoard);
                            playerPosition = newPlayerPosition;
                            //player is at a new place and didn't suffer any damage from the current attack.
                        }
                        if (playerHP < 0){
                            nameOfLastSpellThatKilledPlayer = "Eruption";
                            isPlayerAlive = false;
                            continue;
                        }
                    }
                    break;
                case "Cloud":
                    if (isPlayerInRangeOfTheAttack(cellsUnderAttack, gameBoard)){
                        int[] newPlayerPosition = playerTriesToAvoideSpellMyMovingToNewLocation(playerPosition, cellsUnderAttack);
                        if (newPlayerPosition[0] == playerPosition[0]
                                && newPlayerPosition[1] == playerPosition[1]){
                            //Player takes the damage from the curren spell/attack
                            playerHP -= cloudPlagueAttack;
                            //curse the player to bear the same amount of CloudPlague damage on the very next turn.
                            queueOfPostponedDamagePlayerHasToBare.offerLast(cloudPlagueAttack);
                        } else {
                            updateGameboard(newPlayerPosition,playerPosition, gameBoard);
                            playerPosition = newPlayerPosition;
                        }
                        if (playerHP <= 0){
                            nameOfLastSpellThatKilledPlayer = "Plague Cloud"; //from the 1st phase of the damage split;
                            isPlayerAlive = false;
                            continue;
                        }
                    }
                    break;
            }
            //System.out.printf("Player's HP is %.2f%n", playerHP);
            //System.out.println("Player's current Position is: "+playerPosition[0] +", " + playerPosition[1] );
        }

        if (!isBossAlive){
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f\n", heiganHP);
        }
        if  (!isPlayerAlive){
            System.out.printf("Player: Killed by %s\n", nameOfLastSpellThatKilledPlayer);
        } else {
            System.out.println("Player: " + (int)playerHP);
        }
        System.out.printf("Final position: %d, %d\n", playerPosition[0], playerPosition[1]);




    }

    private static void updateGameboard(int[] newPlayerPosition, int[] playerPosition, int[][] gameBoard) {
        gameBoard[playerPosition[0]][playerPosition[1]] = 0;
        gameBoard[newPlayerPosition[0]][newPlayerPosition[1]] = -1;
    }


    private static double calculatePenaltyDamageFromPastTurn(ArrayDeque<Double> postTurnDamageToPlayer) {
        if(!postTurnDamageToPlayer.isEmpty()){
            return postTurnDamageToPlayer.pollFirst();
        }
        return 0;
    }

    private static int[] playerTriesToAvoideSpellMyMovingToNewLocation(int[] playerPosition, int[][] cellsUnderAttack) {
            int playerRow = playerPosition[0];
            int playerCol = playerPosition[1];
                //Test the upper cell for boundaries compatibility and peace;
                if (playerRow-1 >= 0
                        && cellsUnderAttack[playerRow-1][playerCol] != 1){
                    //the upper cell does exist in the gameBoard and is not under attack.
                    playerRow--;
                    return new int[]{playerRow, playerCol};
                    //the upper cell is under attack or outta boundaries let's try the next direction: move to the right:
                } else if (playerCol+1 < cellsUnderAttack[0].length
                        && cellsUnderAttack[playerRow][playerCol+1] != 1){
                        //now try to move to the right
                        //It's a safe zone so player stops moving around;
                        playerCol++;
                        return new int[]{playerRow, playerCol};
                } else if (playerRow+1 < cellsUnderAttack.length
                        &&cellsUnderAttack[playerRow+1][playerCol] != 1){
                        playerRow++;
                        return new int[]{playerRow, playerCol};
                } else if (playerCol-1 >= 0 && cellsUnderAttack[playerRow][playerCol-1] != 1){
                    playerCol--;
                    return new int[]{playerRow, playerCol};
                }
            return playerPosition;
    }

    private static boolean isPlayerInRangeOfTheAttack(int[][] cellsUnderAttack, int[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length ; j++) {
                if (gameBoard[i][j] == -1 && cellsUnderAttack[i][j] == 1){
                        return true;
                }
            }
        }
        return false;
    }

    private static int[][] applyCastedSpellToLayerOverGameboard(int castTheSpellAtRow, int castTheSpellAtColumn) {
        int[][] cellsUnderAttack = new int[15][15];
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    cellsUnderAttack[castTheSpellAtRow+i][castTheSpellAtColumn+j] = 1;
                }catch (IndexOutOfBoundsException e){
                    //Outta bounds;
                }

            }

        }
        return cellsUnderAttack;
    }

    static String[] commandDispatcher(String rowInput){
        return rowInput.split("\\s+");
    }
}
