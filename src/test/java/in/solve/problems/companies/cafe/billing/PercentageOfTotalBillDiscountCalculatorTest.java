package in.solve.problems.companies.cafe.billing;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PercentageOfTotalBillDiscountCalculatorTest {

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
    public void shouldCalculateForEmptyBill() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .build();
        final BigDecimal percentage = BigDecimal.valueOf(10.00);

        final DiscountCalculator<Bill> calculator = PercentageOfTotalBillDiscountCalculator.withPercentage(percentage);

        final BigDecimal discount = calculator.calculate(bill);
        assertThat(discount, is(BigDecimal.ZERO.setScale(2)));
    }

    @Test
    public void shouldCalculateSimplePercentageOnTotalBill() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(CF, 4)
                .addItems(TE, 2)
                .build();
        final BigDecimal percentage = BigDecimal.valueOf(10.00);

        final DiscountCalculator<Bill> calculator = PercentageOfTotalBillDiscountCalculator.withPercentage(percentage);

        final BigDecimal discount = calculator.calculate(bill);

        assertThat(discount, is(BigDecimal.valueOf(12.00).setScale(2)));
    }

    @Test
    public void shouldCalculateScaledPercentageOnTotalBill() {
        final Bill bill = BillBuilder.withItemsFrom(itemFactory)
                .addItems(CF, 4)
                .addItems(TE, 2)
                .build();
        final BigDecimal percentage = BigDecimal.valueOf(4.00).setScale(2);

        final DiscountCalculator<Bill> calculator = PercentageOfTotalBillDiscountCalculator.withPercentage(percentage);

        final BigDecimal discount = calculator.calculate(bill);

        assertThat(discount, is(BigDecimal.valueOf(4.80).setScale(2)));
    }
}
