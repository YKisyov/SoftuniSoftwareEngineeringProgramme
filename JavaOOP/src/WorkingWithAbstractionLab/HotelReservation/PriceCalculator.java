package WorkingWithAbstractionLab.HotelReservation;

import java.math.BigDecimal;

public class PriceCalculator {
    public static BigDecimal calculatePrice(double basePricePerNight, int nightsBooked, Season season, DiscountPerCustomerType discount) {

        return new BigDecimal(basePricePerNight)
                .multiply(BigDecimal.valueOf(nightsBooked))
                .multiply(BigDecimal.valueOf(season.getSeasonMultiplier()))
                .multiply(discount.getDiscount());
    }
}
