package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;

final class TextBillPrintGenerator extends BillPrintGenerator {

    private TextBillPrintGenerator() {
    }

    public static TextBillPrintGenerator createNew() {
        return new TextBillPrintGenerator();
    }

    @Override
    protected String header() {
        final Integer itemNameMaxLength = 15;
        final StringBuilder header = new StringBuilder(128);
        header.append("|").append(spaceFilled("Item", itemNameMaxLength))
                .append("|").append(spaceFilled("Price", 8))
                .append("|").append(spaceFilled("Count", 8))
                .append("|").append(spaceFilled("Total Price", 12))
                .append("|\n");

        header.append("|").append(repeat("-", itemNameMaxLength))
                .append("|:").append(repeat("-", 6))
                .append(":|:").append(repeat("-", 6))
                .append(":|").append(repeat("-", 12))
                .append("|\n");

        return header.toString();
    }

    @Override
    protected String totalRow(final ProcessedBill bill) {
        final StringBuilder totalRow = new StringBuilder(32);
        totalRow.append("|").append(spaceFilled("Total", 15))
                .append("|").append(repeat(" ", 8))
                .append("|").append(repeat(" ", 8))
                .append("|").append(spaceFilled(bill.total().setScale(2).toString(), 12)).append("|\n");
        return totalRow.toString();
    }

    @Override
    protected String itemRow(final BilledItem billedItem) {
        final StringBuilder row = new StringBuilder(128);
        final Item item = billedItem.getItem();
        row.append("|").append(spaceFilled(item.getName(), 15))
                .append("|").append(spaceFilled(item.getPrice().setScale(2).toString(), 8))
                .append("|").append(spaceFilled(Integer.toString(billedItem.getCount()), 8))
                .append("|").append(spaceFilled(billedItem.getTotal().setScale(2).toString(), 12))
                .append("|\n");

        return row.toString();
    }

    @Override
    protected String discountRow(final AppliedDiscount appliedDiscount) {
        final StringBuilder totalRow = new StringBuilder(32);
        totalRow.append("|").append(spaceFilled(appliedDiscount.getDiscount().toString(), 15))
                .append("|").append(repeat(" ", 8))
                .append("|").append(repeat(" ", 8))
                .append("|").append(spaceFilled(BigDecimal.ZERO.subtract(appliedDiscount.getAmount()).setScale(2).toString(), 12)).append("|\n");
        return totalRow.toString();
    }

    @Override
    protected String effectiveRow(final ProcessedBill bill) {
        final BigDecimal totalDiscount = bill.getAppliedDiscounts().stream().map(d -> d.getAmount())
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        final StringBuilder totalRow = new StringBuilder(32);
        totalRow.append("|").append(spaceFilled("Effective Total", 15))
                .append("|").append(repeat(" ", 8))
                .append("|").append(repeat(" ", 8))
                .append("|").append(spaceFilled(bill.total().subtract(totalDiscount).setScale(2).toString(), 12)).append("|\n");
        return totalRow.toString();
    }

    @Override
    protected String footer() {
        return "";
    }

    private String spaceFilled(final String value, final int maxLength) {
        final StringBuilder data = new StringBuilder(32);
        data.append(value);
        for (int i = 0; i < maxLength - value.length(); i++) {
            data.append(" ");
        }
        return data.toString();
    }

    private String repeat(final String value, final int count) {
        final StringBuilder data = new StringBuilder(32);
        for (int i = 0; i < count; i++) {
            data.append(value);
        }
        return data.toString();
    }
}
