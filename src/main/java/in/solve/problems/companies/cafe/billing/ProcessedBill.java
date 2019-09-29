package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.util.List;

final class ProcessedBill {

    private final Bill bill;
    private final List<AppliedDiscount> appliedDiscounts;

    private ProcessedBill(final Bill bill, final List<AppliedDiscount> appliedDiscounts) {
        this.bill = bill;
        this.appliedDiscounts = appliedDiscounts;
    }

    public static ProcessedBill createNew(final Bill bill, final List<AppliedDiscount> appliedDiscounts) {
        return new ProcessedBill(bill, appliedDiscounts);
    }

    public List<BilledItem> getBilledItems() {
        return this.bill.getItems();
    }

    public List<AppliedDiscount> getAppliedDiscounts() {
        return this.appliedDiscounts;
    }

    public BigDecimal total() {
        return this.bill.total();
    }
}
