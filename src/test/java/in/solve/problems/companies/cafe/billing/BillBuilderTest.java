package in.solve.problems.companies.cafe.billing;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BillBuilderTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    public static final String COFFEE_CODE = "CF";
    public static final String TEA_CODE = "TEA";
    private final ItemFactory itemFactory = ItemFactory.getInstance();
    private final Item coffee = Item.createNew("Coffee", BigDecimal.TEN);


    @Test
    public void shouldCreateBillWithOneItem() {
        itemFactory.register(COFFEE_CODE, coffee);

        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItem(COFFEE_CODE)
                .build();

        final List<BilledItem> items = bill.getItems();
        assertThat(items, hasSize(1));

        final BilledItem billedCoffee = items.get(0);
        assertThat(billedCoffee.getItem(), is(coffee));
        assertThat(billedCoffee.getCount(), is(1));
    }

    @Test
    public void shouldCreateBillWithItemAddedMultipleTimes() {
        itemFactory.register(COFFEE_CODE, coffee);

        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItem(COFFEE_CODE)
                .addItem(COFFEE_CODE)
                .build();

        final List<BilledItem> items = bill.getItems();
        assertThat(items, hasSize(1));

        final BilledItem billedCoffee = items.get(0);
        assertThat(billedCoffee.getItem(), is(coffee));
        assertThat(billedCoffee.getCount(), is(2));
    }

    @Test
    public void shouldCreateBillWithItemAddedWithCount() {
        itemFactory.register(COFFEE_CODE, coffee);

        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(COFFEE_CODE, 3)
                .build();

        final List<BilledItem> items = bill.getItems();
        assertThat(items, hasSize(1));

        final BilledItem billedCoffee = items.get(0);
        assertThat(billedCoffee.getItem(), is(coffee));
        assertThat(billedCoffee.getCount(), is(3));
    }

    @Test
    public void shouldCreateBillWithMultipleItems() {
        final Item tea = Item.createNew(TEA_CODE, BigDecimal.valueOf(20.00));
        itemFactory.register(COFFEE_CODE, coffee)
                   .register(TEA_CODE, tea);

        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(COFFEE_CODE, 3)
                .addItem(TEA_CODE)
                .build();

        final List<BilledItem> items = bill.getItems();
        assertThat(items, hasSize(2));

        assertThat(items, hasItems(
                BilledItem.of(coffee, 3),
                BilledItem.of(tea, 1)));
    }

    @Test
    public void shouldNotAddItemNotFoundByCode() {
        itemFactory.register(COFFEE_CODE, coffee);

        final BillBuilder billBuilder = BillBuilder.withItemsFrom(itemFactory);

        expectedException.expect(BillBuilder.ItemNotFoundException.class);

        billBuilder.addItem(TEA_CODE);
    }

    @Test
    public void shouldContinueToAddItemEvenAfterFailedAddition() {
        itemFactory.register(COFFEE_CODE, coffee);

        final BillBuilder billBuilder = BillBuilder.withItemsFrom(itemFactory);

        try {
            billBuilder.addItem(TEA_CODE);
            Assert.fail("Exception expected");
        } catch (BillBuilder.ItemNotFoundException e) {

        }
        final Bill bill = billBuilder.addItem(COFFEE_CODE).build();

        final List<BilledItem> billItems = bill.getItems();
        assertThat(billItems, hasSize(1));
        final BilledItem billedCoffee = billItems.get(0);
        assertThat(billedCoffee.getItem(), is(coffee));
        assertThat(billedCoffee.getCount(), is(1));
    }

    @Test
    public void shouldNotAddItemsNotFoundByCode() {
        itemFactory.register(COFFEE_CODE, coffee);

        final BillBuilder billBuilder = BillBuilder.withItemsFrom(itemFactory);

        expectedException.expect(BillBuilder.ItemNotFoundException.class);

        billBuilder.addItems(TEA_CODE, 2);
    }

    @Test
    public void shouldContinueToAddItemsEvenAfterFailedAddition() {
        itemFactory.register(COFFEE_CODE, coffee);

        final BillBuilder billBuilder = BillBuilder.withItemsFrom(itemFactory);

        try {
            billBuilder.addItems(TEA_CODE, 3);
            Assert.fail("Exception expected");
        } catch (BillBuilder.ItemNotFoundException e) {

        }
        final Bill bill = billBuilder.addItems(COFFEE_CODE,2).build();

        final List<BilledItem> billItems = bill.getItems();
        assertThat(billItems, hasSize(1));
        final BilledItem billedCoffee = billItems.get(0);
        assertThat(billedCoffee.getItem(), is(coffee));
        assertThat(billedCoffee.getCount(), is(2));
    }
}
