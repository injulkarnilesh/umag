package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;
import java.util.Iterator;

final class DiscountCalculatorImpl implements DiscountCalculator {

    private static final BigDecimal BY_100 = BigDecimal.valueOf(100);
    private static DiscountCalculatorImpl INSTANCE = new DiscountCalculatorImpl();

    private DiscountCalculatorImpl() {
    }

    public static DiscountCalculator getInstance() {
        return INSTANCE;
    }

    public BigDecimal calculate(final BigDecimal amount, final DiscountSlabs discountSlabs) {
        final Iterator<Slab> slabs = discountSlabs.getSlabs();
        BigDecimal discount = BigDecimal.ZERO;

        while (slabs.hasNext()) {
            final Slab slab = slabs.next();
            if (isSlabApplicable(amount, slab)) {
                final BigDecimal calculatedDiscount = discountableAmount(amount, slab)
                        .multiply(slab.getDiscount())
                        .divide(BY_100);

                discount = discount.add(calculatedDiscount);
            }

        }

        return discount;
    }

    private boolean isSlabApplicable(final BigDecimal amount, final Slab slab) {
        final BigDecimal from = BigDecimal.valueOf(slab.getFrom());
        return amount.subtract(from).compareTo(BigDecimal.ZERO) > 0;
    }

    private BigDecimal discountableAmount(final BigDecimal amount, final Slab slab) {
        final BigDecimal from = BigDecimal.valueOf(slab.getFrom());
        final BigDecimal to = BigDecimal.valueOf(slab.getTo());

        if (amount.subtract(to).compareTo(BigDecimal.ZERO) < 0) {
            return amount.subtract(from);
        } else {
            return to.subtract(from);
        }

    }
}
