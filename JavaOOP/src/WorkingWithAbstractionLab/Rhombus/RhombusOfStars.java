package WorkingWithAbstractionLab.Rhombus;

import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        if (n == 1) {
            System.out.println("*");
        } else {
            printRow(n);
        }

    }

    static void printRow(int n) {
        int nextWhiteSpaces = n - 1;
        final char EMPTY_SPACE = ' ';
        final char STAR = '*';
        final int MIN_CHARS_PER_LINE = n;
        int maxAllowedCharNumbersForCurrentRow;
        int requaredStartPerRow = 1;
        boolean shouldIstartPrintingCurrentRowWithStarChar;
        for (int currentRowNumber = 0; currentRowNumber < n * 2; currentRowNumber++) {
            maxAllowedCharNumbersForCurrentRow = MIN_CHARS_PER_LINE + currentRowNumber;
            requaredStartPerRow = 1 + currentRowNumber;
            shouldIstartPrintingCurrentRowWithStarChar = currentRowNumber == n;
            if (!shouldIstartPrintingCurrentRowWithStarChar) {
                for (int i = 0; i < maxAllowedCharNumbersForCurrentRow; i++) {
                    if (i != maxAllowedCharNumbersForCurrentRow - 1) {
                        System.out.print(EMPTY_SPACE);
                    } else {
                        System.out.println(STAR);
                    }
                }
            } else {
                for (int i = 0; i < maxAllowedCharNumbersForCurrentRow; i++) {
                    if (i != maxAllowedCharNumbersForCurrentRow - 1) {
                        System.out.print(EMPTY_SPACE);
                    } else {
                        System.out.println(STAR);
                    }
                }
            }
        }
    }
}
