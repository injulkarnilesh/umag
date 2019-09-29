package in.solve.problems.companies.cafe.billing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class ItemFactory {

    private Map<String, Item> items = new HashMap<>();
    private static final ItemFactory INSTANCE = new ItemFactory();

    private ItemFactory() {
    }

    static ItemFactory getInstance() {
        return INSTANCE;
    }

    ItemFactory register(final String code, final Item item) {
        items.put(code, item);
        return this;
    }

    Optional<Item> get(final String code) {
        return Optional.ofNullable(items.get(code));
    }
}
