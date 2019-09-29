package in.solve.problems.companies.cafe.billing;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BillMinimumItemCountCriteriaTest {

    private static final String CF = "CF";
    private static final Item COFFEE = Item.createNew("Coffee", BigDecimal.valueOf(25.00));
    private static final String TE = "TE";
    private static final Item TEA = Item.createNew("Tea", BigDecimal.TEN);

    private ItemFactory itemFactory;

    @Before
    public void setUp() throws Exception {
        itemFactory = ItemFactory.getInstance()
                .register(CF, COFFEE)
                .register(TE, TEA);
    }

    @Test
    public void shouldNotBeApplicableForEmptyBill() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .build();

        final DiscountCriteria<Bill> criteria = BillMinimumItemCountCriteria.with(TEA, 1);

        assertFalse(criteria.isApplicable(bill));
    }

    @Test
    public void shouldNotBeApplicableForItemFoundWithEqualCount() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(TE, 2)
                .build();

        final DiscountCriteria<Bill> criteria = BillMinimumItemCountCriteria.with(TEA, 2);

        assertFalse(criteria.isApplicable(bill));
    }

    @Test
    public void shouldBeApplicableForItemFoundWithMoreCount() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(TE, 3)
                .addItems(CF, 2)
                .build();

        final DiscountCriteria<Bill> criteria = BillMinimumItemCountCriteria.with(TEA, 2);

        assertTrue(criteria.isApplicable(bill));
    }

    @Test
    public void shouldNotBeApplicableForItemFoundWithLesserCount() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(TE, 1)
                .addItems(CF, 2)
                .build();

        final DiscountCriteria<Bill> criteria = BillMinimumItemCountCriteria.with(TEA, 2);

        assertFalse(criteria.isApplicable(bill));
    }
}
