package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;

public class FixedDiscountCalculator<T> implements DiscountCalculator<T> {

    private final BigDecimal amount;

    public FixedDiscountCalculator(final BigDecimal amount) {
        this.amount = amount;
    }

    public static DiscountCalculator<BilledItem> of(final BigDecimal amount) {
        return new FixedDiscountCalculator(amount);
    }

    @Override
    public BigDecimal calculate(final T item) {
        return this.amount;
    }

    @Override
    public String toString() {
        return amount + " off " ;
    }
}
