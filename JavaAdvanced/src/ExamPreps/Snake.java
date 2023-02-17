package ExamPreps;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Snake {
    static int matrixSize;
    static final int INIT_SNAKE_SIZE = 1;
    static final char ENEMY_MARK = 'e';
    static final char SNAKE_MARK = 's';
    static final char FOOD_MARK = 'f';
    static final char REGULAR_MARK = '*';


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        matrixSize = Integer.parseInt(scan.nextLine());
        int totalFoodInGame = 0;
        int[] currSnakePosition = new int[2];

        boolean isSnakeAlive = true;
        int snakeSize = INIT_SNAKE_SIZE;

        String[] rawDirectionsQueue = scan.nextLine().split(", ");
        ArrayDeque<String> directionsQueue = new ArrayDeque<>();
        for (String command : rawDirectionsQueue) {
            if (command.equals("up") || command.equals("down") || command.equals("left") || command.equals("right")) {
                directionsQueue.offer(command);
            }
        }
        char[][] matrix = new char[matrixSize][matrixSize];
        for (int i = 0; i < matrix.length; i++) {
            String row = scan.nextLine().replace(" ", "");
            for (int j = 0; j < matrix[i].length; j++) {
                char currChar = row.charAt(j);
                matrix[i][j] = currChar;

                switch (currChar) {
                    case SNAKE_MARK:
                        currSnakePosition[0] = i;
                        currSnakePosition[1] = j;
                        break;
                    case FOOD_MARK:
                        totalFoodInGame++;
                        break;
                }
            }
        }

        int[] newSnakePosition = new int[2];
        String command;
        if (directionsQueue.size() > 0) {
            updateMatrix(matrix, currSnakePosition, REGULAR_MARK); //Hide out the initial snake position;
        }
        while (isSnakeAlive && !directionsQueue.isEmpty()) {
            command = directionsQueue.poll();
            newSnakePosition = calculateNewSnakePosition(matrix, currSnakePosition, command);
            applyWallPassingIfNeeded(newSnakePosition);
            if (matrix[newSnakePosition[0]][newSnakePosition[1]] == ENEMY_MARK) {
                isSnakeAlive = false;
            }
            if (matrix[newSnakePosition[0]][newSnakePosition[1]] == FOOD_MARK) {
                snakeSize++;
                updateMatrix(matrix, newSnakePosition, REGULAR_MARK);
                if (totalFoodInGame == snakeSize - INIT_SNAKE_SIZE){
                    break;
                }
            }
            currSnakePosition = newSnakePosition;
        }

        matrix[newSnakePosition[0]][newSnakePosition[1]] = SNAKE_MARK;

        if (!isSnakeAlive) {
            System.out.println("You lose! Killed by an enemy!");
        } else if (totalFoodInGame == snakeSize - INIT_SNAKE_SIZE) {
            System.out.println("You win! Final snake length is " + snakeSize);
        } else {
            System.out.printf("You lose! There is still %s food to be eaten.\n",
                    (totalFoodInGame + INIT_SNAKE_SIZE - snakeSize));
        }

    }

    private static void displayMatrix(char[][] matrix) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void updateMatrix(char[][] matrix, int[] newSnakePosition, char changeTileTo) {
        matrix[newSnakePosition[0]][newSnakePosition[1]] = REGULAR_MARK;
    }

    private static void applyWallPassingIfNeeded(int[] newSnakePosition) {
        int row = newSnakePosition[0];
        int col = newSnakePosition[1];

        if (row < 0) {
            row = matrixSize - 1;
        }
        if (row >= matrixSize) {
            row = 0;
        }
        if (col < 0) {
            col = matrixSize - 1;
        }
        if (col >= matrixSize) {
            col = 0;
        }
        newSnakePosition[0] = row;
        newSnakePosition[1] = col;
    }

    private static int[] calculateNewSnakePosition(char[][] matrix, int[] currSnakePosition, String command) {
        int row = currSnakePosition[0];
        int col = currSnakePosition[1];

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
