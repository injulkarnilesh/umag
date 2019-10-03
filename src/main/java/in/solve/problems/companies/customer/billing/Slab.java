package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;

public final class Slab {

    private final int from;
    private final int to;
    private final BigDecimal discount;

    private Slab(final SlabBuilder slabBuilder) {
        this.from = slabBuilder.getFrom();
        this.to = slabBuilder.getTo();
        this.discount = slabBuilder.getDiscount();
    }

    public static Slab createNew(final SlabBuilder slabBuilder) {
        validate(slabBuilder);
        return new Slab(slabBuilder);
    }

    private static void validate(final SlabBuilder slabBuilder) {
        if (slabBuilder.getTo() < slabBuilder.getFrom()) {
            throw new InvalidSlabException("Slab range not valid [ FROM " + slabBuilder.getFrom() + " TO " + slabBuilder.getTo() + " ]");
        }

        if (slabBuilder.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidSlabException("Slab discount can not be negative  : " + slabBuilder.getDiscount());
        }
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return new StringBuilder(256)
                .append("[ FROM : ").append(this.from).append(",")
                .append(" TO : ").append(this.to).append(",")
                .append(" WITH DISCOUNT : ").append(this.discount)
                .append("]").toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (null == obj || !(obj instanceof Slab)) {
            return false;
        }
        final Slab other = (Slab) obj;
        return this.from == other.from &&
                this.to == other.to &&
                this.discount.equals(other.discount);
    }
}
