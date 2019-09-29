package in.solve.problems.companies.cafe.billing;

public abstract class BillPrintGenerator {

    String generate(ProcessedBill bill) {
        final StringBuilder print = new StringBuilder(1024);
        print.append(header());
        for (BilledItem billedItem: bill.getBilledItems()) {
            print.append(itemRow(billedItem));
        }
        print.append(totalRow(bill));

        if (bill.getAppliedDiscounts().size() > 0) {
            for (AppliedDiscount appliedDiscount : bill.getAppliedDiscounts()) {
                print.append(discountRow(appliedDiscount));
            }
            print.append((effectiveRow(bill)));
        }

        print.append(footer());
        return print.toString();
    }

    protected abstract String header();
    protected abstract String itemRow(final BilledItem billedItem);
    protected abstract String totalRow(final ProcessedBill bill);
    protected abstract String discountRow(final AppliedDiscount appliedDiscount);
    protected abstract String effectiveRow(final ProcessedBill bill);
    protected abstract String footer();

}
