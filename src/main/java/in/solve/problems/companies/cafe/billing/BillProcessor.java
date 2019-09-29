package in.solve.problems.companies.cafe.billing;

import java.util.ArrayList;
import java.util.List;

final class BillProcessor {

    private final Bill bill;

    private BillProcessor(final Bill bill) {
        this.bill = bill;
    }

    public static BillProcessor forBill(final Bill bill) {
        return new BillProcessor(bill);
    }

    public ProcessedBill apply(final List<Discount<Bill>> billDiscounts) {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();

        for (int i = 0; i < billDiscounts.size(); i++) {
            final Discount<Bill> discount = billDiscounts.get(i);
            if (discount.isApplicable(bill)) {
                final AppliedDiscount appliedDiscount = AppliedDiscount.from(discount, discount.getDiscount(bill));
                appliedDiscounts.add(appliedDiscount);
            }
        }

        return ProcessedBill.createNew(bill, appliedDiscounts);
    }
}
