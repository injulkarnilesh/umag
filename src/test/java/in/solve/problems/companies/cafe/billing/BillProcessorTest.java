package in.solve.problems.companies.cafe.billing;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BillProcessorTest {

    private Bill bill;
    private BillProcessor billProcessor;

    @Before
    public void setUp() throws Exception {
        final Item coffee = Item.createNew("CF", BigDecimal.valueOf(20.20));
        final ItemFactory factory = ItemFactory.getInstance().register("CF", coffee);
        bill = BillBuilder.withItemsFrom(factory)
                .addItems("CF", 2)
                .build();

        billProcessor = BillProcessor.forBill(bill);
    }

    @Test
    public void shouldApplyNoDiscountsWhenNoneAreThere() {
        final List<Discount<Bill>> billDiscounts = new ArrayList<>();

        final ProcessedBill processedBill = billProcessor.apply(billDiscounts);

        verifyBilledItems(processedBill);
        assertThat(processedBill.getAppliedDiscounts(), is(empty()));
    }

    @Test
    public void shouldApplyBillDiscount() {
        final BigDecimal discount = BigDecimal.TEN;
        final DiscountCriteria<Bill> matchingCriteria = matchingBillCriteria();
        final DiscountCalculator<Bill> fixed10Discount = discountOnBillOf(discount);
        final Discount<Bill> billDiscount = Discount.with(matchingCriteria, fixed10Discount);
        final List<Discount<Bill>> billDiscounts = new ArrayList<>();
        billDiscounts.add(billDiscount);

        final ProcessedBill processedBill = billProcessor.apply(billDiscounts);

        verifyBilledItems(processedBill);
        final List<AppliedDiscount> appliedDiscounts = processedBill.getAppliedDiscounts();
        assertThat(appliedDiscounts, hasSize(1));
        final AppliedDiscount appliedDiscount = appliedDiscounts.get(0);
        assertThat(appliedDiscount.getDiscount(), is(billDiscount));
        assertThat(appliedDiscount.getAmount(), is(discount.setScale(2)));
    }

    @Test
    public void shouldNotApplyBillDiscount() {
        final DiscountCriteria<Bill> nonMatchingCriteria = nonMatchingBillCriteria();
        final DiscountCalculator<Bill> fixed10Discount = discountOnBillOf(BigDecimal.TEN);
        final Discount<Bill> billDiscount = Discount.with(nonMatchingCriteria, fixed10Discount);
        final List<Discount<Bill>> billDiscounts = new ArrayList<>();
        billDiscounts.add(billDiscount);

        final ProcessedBill processedBill = billProcessor.apply(billDiscounts);

        verifyBilledItems(processedBill);
        final List<AppliedDiscount> appliedDiscounts = processedBill.getAppliedDiscounts();
        assertThat(appliedDiscounts, hasSize(0));
    }

    @Test
    public void shouldApplyApplicableDiscountAndNotApplyNotApplicable() {
        final BigDecimal discount = BigDecimal.TEN;
        final DiscountCriteria<Bill> nonMatchingCriteria = nonMatchingBillCriteria();
        final DiscountCalculator<Bill> fixed10Discount = discountOnBillOf(discount);
        final DiscountCriteria<Bill> matchingItemCriteria = matchingBillCriteria();

        final Discount<Bill> anotherDiscount = Discount.with(nonMatchingCriteria, fixed10Discount);
        final Discount<Bill> matchingDiscount = Discount.with(matchingItemCriteria, fixed10Discount);
        final List<Discount<Bill>> billDiscounts = new ArrayList<>();
        billDiscounts.add(anotherDiscount);
        billDiscounts.add(matchingDiscount);

        final ProcessedBill processedBill = billProcessor.apply(billDiscounts);

        verifyBilledItems(processedBill);
        final List<AppliedDiscount> appliedDiscounts = processedBill.getAppliedDiscounts();
        assertThat(appliedDiscounts, hasSize(1));
        final AppliedDiscount appliedDiscount = appliedDiscounts.get(0);
        assertThat(appliedDiscount.getDiscount(), is(matchingDiscount));
        assertThat(appliedDiscount.getAmount(), is(discount.setScale(2)));
    }

    private void verifyBilledItems(final ProcessedBill processedBill) {
        List<BilledItem> billedItems = processedBill.getBilledItems();
        bill.getItems()
                .forEach(b ->
                        assertThat(billedItems, Matchers.hasItem(b)));
    }

    private DiscountCalculator<Bill> discountOnBillOf(final BigDecimal amount) {
        return item -> amount;
    }

    private DiscountCriteria<Bill> matchingBillCriteria() {
        return item -> true;
    }

    private DiscountCriteria<Bill> nonMatchingBillCriteria() {
        return item -> false;
    }
}
