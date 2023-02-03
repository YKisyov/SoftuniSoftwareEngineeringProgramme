package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class ParkingSystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] firstLine = scan.nextLine().split(" ");
        int rows = Integer.parseInt(firstLine[0]);
        int cols = Integer.parseInt(firstLine[1]);

        boolean[][] matrixOfOccupiedParkingLots = new boolean[rows][cols];

        String userInput = scan.nextLine();

        while (!userInput.equals("stop")) {
            int[] parkingData = Arrays.stream(userInput.split(" ")).
                    mapToInt(Integer::parseInt).
                    toArray();
            int entryRow = parkingData[0];
            int parkAtRow = parkingData[1];
            int parkAtCol = parkingData[2];

            if (!matrixOfOccupiedParkingLots[parkAtRow][parkAtCol]) {
                matrixOfOccupiedParkingLots[parkAtRow][parkAtCol] = true;
                int distanceDrove = calculateDistanceDroveOnEntryLane(entryRow, parkAtRow); //todo abs(parkAtRow - entryRow) + 1;
                distanceDrove += parkAtCol;
                System.out.println(distanceDrove);
            } else {
                boolean isRowFull = false;
                int parkingColOverrider = 1;

                if (parkAtCol - 2 > 0
                        && (!matrixOfOccupiedParkingLots[parkAtRow][parkAtCol - 1]
                        || !matrixOfOccupiedParkingLots[parkAtRow][parkAtCol - 2])) {
                    parkingColOverrider = parkAtCol - 2;
                }


                for (int nearestFreeSpot = parkingColOverrider; nearestFreeSpot < cols; nearestFreeSpot++) {
                    if (!matrixOfOccupiedParkingLots[parkAtRow][nearestFreeSpot]) {
                        matrixOfOccupiedParkingLots[parkAtRow][nearestFreeSpot] = true;
                        int distanceDrove = calculateDistanceDroveOnEntryLane(entryRow, parkAtRow); //todo abs(parkAtRow - entryRow) + 1;
                        distanceDrove += nearestFreeSpot;
                        System.out.println(distanceDrove);
                        break;
                    }
                    if (nearestFreeSpot == cols - 1) {
                        isRowFull = true;
                    }
                }
                if (isRowFull) {
                    System.out.printf("Row %d full\n", parkAtRow);
                }
            }
            userInput = scan.nextLine();
        }
    // printParkingLot(matrixOfOccupiedParkingLots);
    }

    private static int calculateDistanceDroveOnEntryLane(int entryRow, int parkAtRow) {
        return Math.abs((parkAtRow - entryRow)) + 1;
    }
    private static void printParkingLot(boolean[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print (matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
