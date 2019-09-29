package in.solve.problems.companies.cafe.billing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;

public class TextBillPrintGeneratorTest {

    final ItemFactory factory = ItemFactory.getInstance();
    final Item coffee = Item.createNew("Hot Coffee", BigDecimal.valueOf(25.00));
    final Item colCoffee = Item.createNew("Cold Coffee", BigDecimal.valueOf(40.00));
    final Item tea = Item.createNew("Tea", BigDecimal.valueOf(10.00));
    final Item lassi = Item.createNew("Lassi", BigDecimal.valueOf(20.00));

    @Before
    public void setUp() throws Exception {
        factory.register("CF", coffee)
                .register("CCF", colCoffee)
                .register("TE", tea)
                .register("LS", lassi);
    }

    @Test
    public void shouldGenerateSingleItemBillWithoutDiscounts() {
        final Bill bill = BillBuilder.withItemsFrom(factory)
                .addItems("CF", 2)
                .build();
        final ProcessedBill processedBill = ProcessedBill.createNew(bill, new ArrayList<>());
        final BillPrintGenerator printGenerator = TextBillPrintGenerator.createNew();

        final String billPrint = printGenerator.generate(processedBill);

        Assert.assertThat(billPrint, is(
            "|Item           |Price   |Count   |Total Price |\n" +
                  "|---------------|:------:|:------:|------------|\n" +
                  "|Hot Coffee     |25.00   |2       |50.00       |\n" +
                  "|Total          |        |        |50.00       |\n"));
    }

    @Test
    public void shouldGenerateTwoItemBillWithoutDiscounts() {
        final Bill bill = BillBuilder.withItemsFrom(factory)
                .addItems("CF", 2)
                .addItems("TE", 4)
                .build();
        final ProcessedBill processedBill = ProcessedBill.createNew(bill, new ArrayList<>());
        final BillPrintGenerator printGenerator = TextBillPrintGenerator.createNew();

        final String billPrint = printGenerator.generate(processedBill);

        Assert.assertThat(billPrint, is(
                "|Item           |Price   |Count   |Total Price |\n" +
                    "|---------------|:------:|:------:|------------|\n" +
                    "|Hot Coffee     |25.00   |2       |50.00       |\n" +
                    "|Tea            |10.00   |4       |40.00       |\n" +
                    "|Total          |        |        |90.00       |\n"));
    }

    @Test
    public void shouldGenerateTwoItemBillWithDiscounts() {
        final Bill bill = BillBuilder.withItemsFrom(factory)
                .addItems("CF", 2)
                .addItems("TE", 4)
                .build();

        final ArrayList<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        final Discount<Bill> discount = Discount.with(
                (b) -> true,
                (b) -> BigDecimal.valueOf(12.00)
        );
        final AppliedDiscount appliedDiscount = AppliedDiscount.from(discount, BigDecimal.valueOf(12.00));
        appliedDiscounts.add(appliedDiscount);
        final ProcessedBill processedBill = ProcessedBill.createNew(bill, appliedDiscounts);
        final BillPrintGenerator printGenerator = TextBillPrintGenerator.createNew();

        final String billPrint = printGenerator.generate(processedBill);

        Assert.assertThat(billPrint, is(
                "|Item           |Price   |Count   |Total Price |\n" +
                        "|---------------|:------:|:------:|------------|\n" +
                        "|Hot Coffee     |25.00   |2       |50.00       |\n" +
                        "|Tea            |10.00   |4       |40.00       |\n" +
                        "|Total          |        |        |90.00       |\n" +
                        "|" + discount.toString() + "|        |        |-12.00      |\n" +
                        "|Effective Total|        |        |78.00       |\n"));
    }

}
