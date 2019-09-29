package in.solve.problems.companies.cafe.billing;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ItemFactoryTest {

    private static final String CODE = "CODE";

    @Test
    public void shouldNotGetItemIfNoneIsRegistered() {
        final Optional<Item> item = ItemFactory.getInstance().get("Any");
        assertFalse(item.isPresent());
    }

    @Test
    public void shouldGetRegisteredItem() {
        final ItemFactory factory = ItemFactory.getInstance();
        final Item item = Item.createNew("ITEM", BigDecimal.valueOf(34.56));
        factory.register(CODE, item);

        final Optional<Item> itemFound = factory.get(CODE);

        assertTrue(itemFound.isPresent());
        assertThat(itemFound.get(), is(item));
    }

    @Test
    public void shouldNotGetItemIfMatchingNotFound() {
        final ItemFactory factory = ItemFactory.getInstance();
        factory.register(CODE, Item.createNew("Item", BigDecimal.valueOf(23.77)));

        final Optional<Item> foundItem = factory.get("SOMEOTHERCODE");
        assertFalse(foundItem.isPresent());
    }

    @Test
    public void factoryShouldBeSingleton() {
        final ItemFactory instance1 = ItemFactory.getInstance();
        final ItemFactory instance2 = ItemFactory.getInstance();

        final Item item = Item.createNew("Tea", BigDecimal.TEN);
        instance1.register(CODE, item);

        final Optional<Item> itemFound = instance2.get("CODE");

        assertTrue(itemFound.isPresent());
        assertThat(itemFound.get(), is(item));
    }
}
