import java.util.*;

class ExternalTask {
    private static final String MY_CHALLENGE_TOKEN = "kish4af12";

    //todo TestData: new int[] {10,12,4,5,9};
    public static int arrayChallenge(int[] arr) {
        int profit = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            int maxPriceWas = calculateMaxPrice(arr, i + 1);
            if (profit < calculateProfit(arr[i], maxPriceWas)) {
                profit = calculateProfit(arr[i], maxPriceWas);
            }
        }
        // code goes here
        return profit;
    }

    public static void main(String[] args) {
        // keep this function call here
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        str = str.substring(str.indexOf('{') + 1, str.indexOf('}'));
        int[] arr = Arrays.stream(str.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int profit = arrayChallenge(arr);
        System.out.print(profit + MY_CHALLENGE_TOKEN);
    }

    static int calculateMaxPrice(int[] stockPricesData, int checkForDaysOnAndAfter) {
        int maxPrice = stockPricesData[checkForDaysOnAndAfter];
        for (int i = checkForDaysOnAndAfter; i < stockPricesData.length; i++) {
            if (stockPricesData[i] > maxPrice) {
                maxPrice = stockPricesData[i];
            }
        }
        return maxPrice;
    }

    static int calculateProfit(int buyPrice, int soldPrice) {
        return soldPrice - buyPrice;
    }
}