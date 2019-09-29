package in.solve.problems.companies.cafe.billing;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CafeBillingApplication {

    @Test
    public void appTest() {
        final ItemFactory factory = ItemFactory.getInstance();
        factory.register("NT", Item.createNew("Normal Tea", BigDecimal.valueOf( 10.00)))
                .register("ST", Item.createNew("Special Tea", BigDecimal.valueOf(20.00)))
                .register("CCF", Item.createNew("Cold Coffee", BigDecimal.valueOf(40.00)))
                .register("HCF", Item.createNew("Hot Coffee", BigDecimal.valueOf(20.00)))
                .register("TK", Item.createNew("Masala Tak", BigDecimal.valueOf(20.00)))
                .register("LS", Item.createNew("Plain Lassi", BigDecimal.valueOf(30.00)))
                .register("MLS", Item.createNew("Mango Plain Lassi", BigDecimal.valueOf(40.00)));

        final BillBuilder billBuilder = BillBuilder.withItemsFrom(factory);
        final Bill bill = billBuilder.addItems("HCF", 2)
                .addItems("LS", 2)
                .addItems("CCF", 4)
                .build();

        final Item coldCoffee = factory.get("CCF").get();
        final DiscountCriteria<Bill> moreThan3Coffees = BillMinimumItemCountCriteria.with(coldCoffee, 3);
        final DiscountCalculator<Bill> discountOf5 = PerItemFixedDiscountCalculator.forItem(coldCoffee, BigDecimal.valueOf(5.00));
        final Discount<Bill> off5ForMoreThan3Coffee = Discount.with(moreThan3Coffees, discountOf5);

        final DiscountCriteria<Bill> billMoreThan200 = BillMinimumTotalCriteria.with(BigDecimal.valueOf(200.00));
        final DiscountCalculator<Bill> billDiscount10Perc = PercentageOfTotalBillDiscountCalculator.withPercentage(BigDecimal.valueOf(10.00));
        final Discount<Bill> off10PercOnBillMoreThan200 = Discount.with(billMoreThan200, billDiscount10Perc);

        final List<Discount<Bill>> discounts = new ArrayList<>();
        discounts.add(off5ForMoreThan3Coffee);
        discounts.add(off10PercOnBillMoreThan200);

        final ProcessedBill processedBill = BillProcessor.forBill(bill)
                .apply(discounts);

        final BillPrintGenerator printGenerator = TextBillPrintGenerator.createNew();
        final String print = printGenerator.generate(processedBill);
        System.out.println(print);

    }
}
