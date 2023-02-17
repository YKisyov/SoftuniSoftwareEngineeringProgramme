package ExamPreps;

import java.util.Scanner;

public class RallyRacing {
    static int matrixSize;
    static final char CAR_MARK = 'C';
    static final char FINISH_LINE_MARK = 'F';
    static final char ROAD_MARK = '.';
    static final char TUNNEL_MARK = 'T';

    public static void main(String[] args) {
        int[] currentCarPosition = new int[]{0, 0};
        boolean didThisCarFinish = false;
        int drivenKilometers = 0;

        Scanner scan = new Scanner(System.in);
        matrixSize = Integer.parseInt(scan.nextLine());
        String trackedCarRegNumber = scan.nextLine();

        char[][] matrix = new char[matrixSize][matrixSize];
        for (int i = 0; i < matrix.length; i++) {
            String[] lineArr = scan.nextLine().split(" ");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = lineArr[j].charAt(0);
            }
        }

        // printTheTrace(matrix);

        int[] newCarPosition = new int[]{0, 0};
        String command = scan.nextLine();
        matrix[0][0] = CAR_MARK;
        while (!didThisCarFinish && !command.equals("End")) {
            if (command.equals("up") || command.equals("right") || command.equals("down") || command.equals("left")) {

                newCarPosition = calculateNewPosition(command, currentCarPosition);

                char roadTile = matrix[newCarPosition[0]][newCarPosition[1]];
                if (roadTile == ROAD_MARK) {
                    drivenKilometers += 10;
                    updateMap(matrix, currentCarPosition, newCarPosition);
                    currentCarPosition = newCarPosition;
                }
                if (roadTile == FINISH_LINE_MARK) {
                    drivenKilometers += 10;
                    didThisCarFinish = true;
                    updateMap(matrix, currentCarPosition, newCarPosition);
                    currentCarPosition = newCarPosition;
                }
                if (roadTile == TUNNEL_MARK) {
                    drivenKilometers += 30;
                    updateMap(matrix, currentCarPosition, newCarPosition);
                    currentCarPosition = newCarPosition;
                    //Now there is only one 'T' and it represents the exit point;
                    newCarPosition = getTunnelExitPosition(matrix, newCarPosition);
                    updateMap(matrix, currentCarPosition, newCarPosition);
                    currentCarPosition = newCarPosition;
                }
            /*
            System.out.printf("===>  %d km  <===\n", drivenKilometers);
            printTheTrace(matrix);
            */

            }
            command = scan.nextLine();
        }

        if (didThisCarFinish) {
            System.out.printf("Racing car %s finished the stage!\n", trackedCarRegNumber);
        } else {
            System.out.printf("Racing car %s DNF.\n", trackedCarRegNumber);
        }
        System.out.printf("Distance covered %d km.\n", drivenKilometers);
        printTheTrace(matrix);

    }

    private static int[] getTunnelExitPosition(char[][] matrix, int[] noUpdate) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] == TUNNEL_MARK) {
                    return new int[]{i, j};
                }
            }
        }
        return noUpdate;
    }

    private static void updateMap(char[][] matrix, int[] currentCarPosition, int[] newCarPosition) {
        matrix[currentCarPosition[0]][currentCarPosition[1]] = ROAD_MARK;
        matrix[newCarPosition[0]][newCarPosition[1]] = CAR_MARK;
    }

    private static void printTheTrace(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static int[] calculateNewPosition(String command, int[] currentCarPosition) {
        int row = currentCarPosition[0];
        int col = currentCarPosition[1];
        switch (command) {
            case "up":
                row--;
                break;
            case "down":
                row++;
                break;
            case "left":
                col--;
                break;
            case "right":
                col++;
                break;
        }
        return new int[]{row, col};
    }

}
