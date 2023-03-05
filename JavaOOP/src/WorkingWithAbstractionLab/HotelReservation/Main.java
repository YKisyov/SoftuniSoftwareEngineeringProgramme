package WorkingWithAbstractionLab.HotelReservation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] bookingDetails = scan.nextLine().split("\\s+");
        double basePricePerNight = Double.parseDouble(bookingDetails[0]);
        int nightsBooked = Integer.parseInt(bookingDetails[1]);
        Season seasonEnum = Season.valueOf(bookingDetails[2]);
        DiscountPerCustomerType discountEnum = DiscountPerCustomerType.valueOf(bookingDetails[3]);
        BigDecimal calculatedPrice = PriceCalculator.calculatePrice(basePricePerNight, nightsBooked, seasonEnum, discountEnum);

        System.out.println(calculatedPrice.setScale(2, RoundingMode.HALF_DOWN));
    }
}
