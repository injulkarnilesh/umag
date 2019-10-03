package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;

final class SlabBuilder {

    private int from;
    private int to;
    private BigDecimal discount = BigDecimal.ZERO;

    private SlabBuilder() {
    }

    public static SlabBuilder createNew() {
        return new SlabBuilder();
    }

    public SlabBuilder from(final int from) {
        this.from = from;
        return this;
    }

    public SlabBuilder to(final int to) {
        this.to = to;
        return this;
    }

    public SlabBuilder withDiscount(final BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public SlabBuilder toMax() {
        return to(Integer.MAX_VALUE);
    }

    public Slab buildASlab() {
        return Slab.createNew(this);
    }

    int getFrom() {
        return from;
    }

    int getTo() {
        return to;
    }

    BigDecimal getDiscount() {
        return discount;
    }
}
