package ExamPreps;

import java.util.Scanner;

public class FormulaOne {
    /*
     * speed coding for time
     * start at 16:40h
     * */
    static int matrixSize = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        matrixSize = Integer.parseInt(scan.nextLine());
        int totalCommandsLeft = Integer.parseInt(scan.nextLine());

        final char PLAYER_MARK = 'P';
        final char NORMAL_MARK = '.';
        final char TRAP_MARK = 'T';
        final char FINISH_MARK = 'F';
        final char BONUS_MARK = 'B';

        int[] currCarPosition = new int[2];
        int[] newCarPosition = new int[2];

        boolean isCarFinished = false;
        char[][] matrix = new char[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            String row = scan.nextLine();
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = row.charAt(j);
                if (row.charAt(j) == PLAYER_MARK) {
                    currCarPosition[0] = i;
                    currCarPosition[1] = j;
                }
            }
        }

        String cmd = "";

        matrix[currCarPosition[0]][currCarPosition[1]] = NORMAL_MARK;

        cmd = scan.nextLine();
        totalCommandsLeft--;

        while (totalCommandsLeft -1 > 0 && !isCarFinished) {


            newCarPosition = processCommand(cmd, currCarPosition);
            if (matrix[newCarPosition[0]][newCarPosition[1]] == FINISH_MARK) {
                isCarFinished = true;
                matrix[newCarPosition[0]][newCarPosition[1]] = PLAYER_MARK;
            }
            if (matrix[newCarPosition[0]][newCarPosition[1]] == NORMAL_MARK) {
                currCarPosition = newCarPosition;
            }
            if (matrix[newCarPosition[0]][newCarPosition[1]] == BONUS_MARK) {
                currCarPosition = newCarPosition;
                continue;
            }

            cmd = scan.nextLine();
            totalCommandsLeft--;
            /*System.out.printf("======= new poz: %d, %d==========\n", currCarPosition[0], currCarPosition[1]);
            printMatrix(matrix);*/
        }
        matrix[currCarPosition[0]][currCarPosition[1]] = PLAYER_MARK;

        if (isCarFinished){
            System.out.println();
        }

        printMatrix(matrix);

    }

    static void printMatrix(final char[][] matrix) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

    }


    private static int[] processCommand(final String cmd, final int[] currentCarPosition) {
        int row = currentCarPosition[0];
        int col = currentCarPosition[1];

        switch (cmd) {
            case "down":
                row++;
                break;
            case "up":
                row--;
                break;
            case "left":
                col--;
                break;
            case "right":
                col++;
                break;
        }

        if (row < 0) {
            row = matrixSize - 1;
        }
        if (row >= matrixSize - 1) {
            row = 0;
        }
        if (col < 0) {
            col = matrixSize - 1;

        }
        if (col >= matrixSize - 1) {

        }
        return new int[]{row, col};
    }


}
