package in.solve.problems.companies.cafe.billing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BillBuilder {

    private ItemFactory itemFactory;
    private Map<Item, Integer> items = new HashMap<>();

    public BillBuilder(final ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public static BillBuilder withItemsFrom(final ItemFactory itemFactory) {
        return new BillBuilder(itemFactory);
    }

    public BillBuilder addItem(final String code) {
        return this.addItems(code, 1);
    }

    public BillBuilder addItems(final String code, final int count) {
        final Optional<Item> itemFound = itemFactory.get(code);
        if (!itemFound.isPresent()) {
            throw new ItemNotFoundException(code);
        }
        final Item item = itemFound.get();
        return addItems(item, count);
    }

    private BillBuilder addItems(final Item item, final int count) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, count);
        }

        return this;
    }

    public Bill build() {
        return Bill.fromBuilder(this);
    }

    Set<Map.Entry<Item, Integer>> getItems() {
        return items.entrySet();
    }

    static class ItemNotFoundException extends RuntimeException {

        public ItemNotFoundException(final String code) {
            super("Item not found for code" + code);
        }
    }
}
