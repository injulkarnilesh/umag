package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;

final class AppliedDiscount {
    private final Discount<Bill> discount;
    private final BigDecimal amount;

    private AppliedDiscount(final Discount<Bill> discount, final BigDecimal amount) {
        this.discount = discount;
        this.amount = amount;
    }

    public static AppliedDiscount from(final Discount<Bill> discount, final BigDecimal amount) {
        return new AppliedDiscount(discount, amount);
    }

    public Discount<?> getDiscount() {
        return discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
