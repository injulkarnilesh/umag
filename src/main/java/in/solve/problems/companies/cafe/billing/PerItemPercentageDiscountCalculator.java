package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

final class PerItemPercentageDiscountCalculator implements DiscountCalculator<Bill> {

    private final Item item;
    private final BigDecimal percentage;

    private PerItemPercentageDiscountCalculator(final Item item, final BigDecimal percentage) {
        this.item = item;
        this.percentage = percentage;
    }

    public static PerItemPercentageDiscountCalculator forItem(final Item item, final BigDecimal percentage) {
        return new PerItemPercentageDiscountCalculator(item, percentage);
    }

    @Override
    public BigDecimal calculate(final Bill bill) {
        final Optional<BilledItem> foundBilledItem = bill.getItems().stream()
                .filter(billedItem -> item.equals(billedItem.getItem()))
                .findFirst();
        if (foundBilledItem.isPresent()) {
            final BilledItem billedItem = foundBilledItem.get();
            final BigDecimal price = billedItem.getItem().getPrice();
            final BigDecimal perItemDiscount = price.multiply(percentage)
                    .divide(BigDecimal.valueOf(100.00));
            return perItemDiscount.multiply(BigDecimal.valueOf(billedItem.getCount())).setScale(2, RoundingMode.HALF_EVEN);
        }
        return BigDecimal.ZERO.setScale(2);
    }

    @Override
    public String toString() {
        return percentage + "% off on each of " + item.getName();
    }
}
