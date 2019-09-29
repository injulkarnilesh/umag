package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.util.Objects;

final class BilledItem {

    private final Item item;
    private final int count;

    private BilledItem(final Item item, final int count) {
        this.item = item;
        this.count = count;
    }

    public static BilledItem of(final Item item, final Integer count) {
        return new BilledItem(item, count);
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getTotal() {
        return item.getPrice().multiply(BigDecimal.valueOf(count));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BilledItem that = (BilledItem) o;
        return count == that.count &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, count);
    }

    @Override
    public String toString() {
        return "BilledItem{" +
                "item=" + item +
                ", count=" + count +
                '}';
    }
}
