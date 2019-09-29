package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

class PercentageOfTotalBillDiscountCalculator implements DiscountCalculator<Bill> {

    private final BigDecimal percentage;

    private PercentageOfTotalBillDiscountCalculator(final BigDecimal percentage) {
        this.percentage = percentage;
    }

    public static PercentageOfTotalBillDiscountCalculator withPercentage(final BigDecimal percentage) {
        return new PercentageOfTotalBillDiscountCalculator(percentage);
    }

    @Override
    public BigDecimal calculate(final Bill item) {
        return item.total().multiply(percentage)
                .divide(BigDecimal.valueOf(100.00))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return percentage + "% off on total bill";
    }
}
