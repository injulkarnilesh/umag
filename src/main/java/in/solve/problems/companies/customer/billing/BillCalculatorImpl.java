package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;

public final class BillCalculatorImpl implements BillCalculator {

    private final DiscountSlabsFactory discountSlabsFactory;
    private final DiscountCalculator discountCalculator;

    public BillCalculatorImpl(final DiscountSlabsFactory discountSlabsFactory, final DiscountCalculator discountCalculator) {
        this.discountSlabsFactory = discountSlabsFactory;
        this.discountCalculator = discountCalculator;
    }

    public static BillCalculator createNew(final DiscountSlabsFactory discountSlabsFactory, final DiscountCalculator discountCalculator) {
        return new BillCalculatorImpl(discountSlabsFactory, discountCalculator);
    }

    public BigDecimal calculate(final CustomerType customerType, final BigDecimal amount) {
        final DiscountSlabs slabs = discountSlabsFactory.getSlabs(customerType);
        final BigDecimal discount = discountCalculator.calculate(amount, slabs);
        return amount.subtract(discount);
    }
}
