package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.util.Objects;

final class Item {

    private final String name;
    private final BigDecimal price;

    private Item(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public static Item createNew(final String name, final BigDecimal price) {
        return new Item(name, price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
