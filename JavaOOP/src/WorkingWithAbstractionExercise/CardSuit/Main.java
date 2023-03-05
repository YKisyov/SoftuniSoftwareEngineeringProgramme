package WorkingWithAbstractionExercise.CardSuit;

import java.util.Scanner;

public class Main {
    enum CardSuit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        System.out.println(userInput+":");

        CardSuit[] enumArr = CardSuit.values();
        for (int i = 0; i < enumArr.length; i++) {
            System.out.printf("Ordinal value: %d; Name value: %s\n",
                    enumArr[i].ordinal(),
                    enumArr[i].name());
        }
    }


}
