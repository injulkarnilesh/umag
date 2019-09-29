package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class Discount<T> {

    private final DiscountCriteria<T> criteria;
    private final DiscountCalculator<T> calculator;

    public Discount(final DiscountCriteria<T> criteria, final DiscountCalculator<T> calculator) {
        this.criteria = criteria;
        this.calculator = calculator;
    }

    public static <C> Discount<C> with(final DiscountCriteria<C> criteria, final DiscountCalculator<C> calculator) {
        return new Discount<>(criteria, calculator);
    }

    public boolean isApplicable(final T item) {
        return this.criteria.isApplicable(item);
    }

    public BigDecimal getDiscount(final T item) {
        return this.calculator.calculate(item).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return calculator.toString() + " for " + criteria.toString();
    }
}
