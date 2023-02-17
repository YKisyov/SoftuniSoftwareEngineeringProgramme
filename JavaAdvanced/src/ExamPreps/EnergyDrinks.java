package ExamPreps;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class EnergyDrinks {
    public static void main(String[] args) {
        int levelOfCaffineInTheBlood = 0;
        final int CAFFEINE_DEDUCTION_STEP = 30;
        final int CAFFEINE_MAX_UPTAKE_LIMIT = 300;
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> milCaffeineStack = new ArrayDeque<>();
        ArrayDeque<Integer> energyDrinkQueue = new ArrayDeque<>();


        int[] userInput = Arrays.stream(scan.nextLine().replaceAll("\\s+", "").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (Integer caffeine : userInput) {
            milCaffeineStack.push(caffeine);
        }
        userInput = Arrays.stream(scan.nextLine().replaceAll("\\s+", "").split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (Integer drink : userInput) {
            energyDrinkQueue.offer(drink);
        }

        while (!energyDrinkQueue.isEmpty() && !milCaffeineStack.isEmpty()) {
            int theCaffeine = milCaffeineStack.peek() * energyDrinkQueue.peek();

            if (theCaffeine + levelOfCaffineInTheBlood <= CAFFEINE_MAX_UPTAKE_LIMIT) {
                milCaffeineStack.pop();
                energyDrinkQueue.poll();
                levelOfCaffineInTheBlood += theCaffeine;
            } else {
                milCaffeineStack.pop();
                int drink = energyDrinkQueue.poll();
                energyDrinkQueue.offer(drink);
                levelOfCaffineInTheBlood = (levelOfCaffineInTheBlood - CAFFEINE_DEDUCTION_STEP >= 0)
                        ? levelOfCaffineInTheBlood - CAFFEINE_DEDUCTION_STEP : levelOfCaffineInTheBlood;

            }

        }

        if (energyDrinkQueue.isEmpty()) {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        } else {
            StringBuilder sb = new StringBuilder("Drinks left: ");
            while (!energyDrinkQueue.isEmpty()) {
                sb.append(energyDrinkQueue.poll());
                if (energyDrinkQueue.size() > 0) {
                    sb.append(", ");
                }
            }
            System.out.println(sb.toString());
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.", levelOfCaffineInTheBlood);
    }
}
