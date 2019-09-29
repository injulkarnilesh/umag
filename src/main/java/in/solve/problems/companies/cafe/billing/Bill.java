package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Bill {

    private final Map<Item, Integer> items = new HashMap<>();

    private Bill(final Set<Map.Entry<Item, Integer>> items) {
        items.forEach(e -> this.items.put(e.getKey(), e.getValue()));
    }

    public static Bill fromBuilder(final BillBuilder billBuilder) {
        return new Bill(billBuilder.getItems());
    }

    public List<BilledItem> getItems() {
        return this.items.entrySet()
                .stream()
                .map(e -> BilledItem.of(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public BigDecimal total() {
        return getItems().stream()
                .map(BilledItem::getTotal)
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }
}
