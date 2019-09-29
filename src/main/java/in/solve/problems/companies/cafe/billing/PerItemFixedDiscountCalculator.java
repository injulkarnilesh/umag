package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class PerItemFixedDiscountCalculator implements DiscountCalculator<Bill> {

    private final Item item;
    private final BigDecimal amountPerItem;

    private PerItemFixedDiscountCalculator(final Item item, final BigDecimal amountPerItem) {
        this.item = item;
        this.amountPerItem = amountPerItem;
    }

    public static PerItemFixedDiscountCalculator forItem(final Item item, final BigDecimal amountPerItem) {
        return new PerItemFixedDiscountCalculator(item, amountPerItem);
    }

    @Override
    public BigDecimal calculate(final Bill bill) {
        final Integer count = bill.getItems().stream()
                .filter(it -> item.equals(it.getItem()))
                .findFirst()
                .map(it -> it.getCount())
                .orElse(0);
        return amountPerItem.multiply(BigDecimal.valueOf(count)).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return amountPerItem + " off on each of " + item.getName();
    }
}
