package WorkingWithAbstractionLab.HotelReservation;

import java.math.BigDecimal;

public enum DiscountPerCustomerType {
    None(1.0d),
    SecondVisit(0.9d),
    VIP(.8d);
    private BigDecimal discount;
    private DiscountPerCustomerType(double discount){
        this.discount = BigDecimal.valueOf(discount);
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
