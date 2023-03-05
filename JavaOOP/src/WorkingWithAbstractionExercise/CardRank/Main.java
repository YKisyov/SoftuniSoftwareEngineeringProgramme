package WorkingWithAbstractionExercise.CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (userInput.equals("Card Ranks")) {
            System.out.println(userInput + ":");
            CardRank[] cardsRanks = CardRank.values();
            for (CardRank enumElement : cardsRanks){
                System.out.printf("Ordinal value: %d; Name value: %s\n",
                        enumElement.ordinal(),
                        enumElement.name());
            }
        }
    }
}
