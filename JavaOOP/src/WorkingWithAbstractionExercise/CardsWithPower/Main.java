package WorkingWithAbstractionExercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInputCardPower = scan.nextLine();
        String userInputTwoCardSuit = scan.nextLine();

        CardPowerRank powerRank = CardPowerRank.valueOf(userInputCardPower);
        CardPowerSuit suitPower = CardPowerSuit.valueOf(userInputTwoCardSuit);
        System.out.printf("Card name: %s of %s; Card power: %d\n",
                powerRank.name(),
                suitPower.name(),
                powerRank.getPowerRank() + suitPower.getSuitePower());
    }

}
