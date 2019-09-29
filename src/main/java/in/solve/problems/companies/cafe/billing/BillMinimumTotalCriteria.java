package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

final class BillMinimumTotalCriteria implements DiscountCriteria<Bill> {

    private final BigDecimal minTotal;

    private BillMinimumTotalCriteria(final BigDecimal minTotal) {
        this.minTotal = minTotal;
    }

    public static BillMinimumTotalCriteria with(final BigDecimal minTotal) {
        return new BillMinimumTotalCriteria(minTotal);
    }

    @Override
    public boolean isApplicable(final Bill bill) {
        return bill.total().setScale(2, RoundingMode.HALF_EVEN).compareTo(minTotal) > 0;
    }

    @Override
    public String toString() {
        return "total bill more than " + this.minTotal;
    }
}
