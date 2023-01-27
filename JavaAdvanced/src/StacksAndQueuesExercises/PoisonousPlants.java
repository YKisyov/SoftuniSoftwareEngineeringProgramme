package StacksAndQueuesExercises;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // omit/skip the fist input as it useless for us;
        scanner.nextLine();
        String[] input = scanner.nextLine().split("\\s+");

        ArrayDeque<Integer> userInputQueue = new ArrayDeque<>();
        ArrayDeque<Integer> dailyQueue = new ArrayDeque<>();


        for (String str : input) {
            userInputQueue.offer(Integer.parseInt(str));
        }

        int countCycles = 0;
        boolean doWeHaveAdeathPlant = true;

        while (userInputQueue.size() >= 2 && doWeHaveAdeathPlant) {
            doWeHaveAdeathPlant = false;
            while (!userInputQueue.isEmpty()) {
                Integer leftPlant;
                Integer rightPlant;
                if (dailyQueue.isEmpty()) {
                    leftPlant = userInputQueue.pollFirst();
                } else {
                    leftPlant = dailyQueue.pollLast();
                }
                rightPlant = userInputQueue.pollFirst();

                if (leftPlant.compareTo(rightPlant) >= 0) {  //case to check 2 ?> 2
                    dailyQueue.offer(leftPlant);
                    dailyQueue.offer(rightPlant);
                } else {
                    //plant must die;
                    doWeHaveAdeathPlant = true;
                    //omit the existence of the right flower by not including it in the dailyQueue;
                    dailyQueue.offer(leftPlant);
                }
            }

            //That was one full cycle of the userInputQueue = 1 day.
            countCycles++;
        }

        System.out.println(countCycles);

        //TEST to se the current stata of the DailyQueue

        while (!dailyQueue.isEmpty()){
            System.out.print(dailyQueue.poll() + " ");
        }
        System.out.println("Count is: " + countCycles);

    }
}
