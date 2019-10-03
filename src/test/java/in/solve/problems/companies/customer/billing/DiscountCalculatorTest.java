package in.solve.problems.companies.customer.billing;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @Before
    public void setUp() throws Exception {
        discountCalculator = DiscountCalculatorImpl.getInstance();
    }

    @Test
    public void shouldHaveZeroDiscountWithNoSlabs() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        final DiscountSlabs discountSlabs = slabsBuilder.build();

        final BigDecimal discount = discountCalculator.calculate(BigDecimal.TEN, discountSlabs);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    public void shouldCalculateDiscountForSingleSlab() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(0).to(1000).withDiscount(valueOf(5));
        final DiscountSlabs slabs = slabsBuilder.build();

        final BigDecimal discount = discountCalculator.calculate(valueOf(500), slabs);

        assertEquals(valueOf(25), discount);
    }

    @Test
    public void shouldCalculateDiscountForTwoSlabs() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(0).to(1000).withDiscount(valueOf(5));
        slabsBuilder.addSlab().from(1000).to(5000).withDiscount(valueOf(10));

        final DiscountSlabs slabs = slabsBuilder.build();

        final BigDecimal discount = discountCalculator.calculate(valueOf(1400), slabs);

        assertEquals(valueOf(90), discount);
    }

    @Test
    public void shouldCalculateDiscountForMultipleSlabs() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(0).to(5000).withDiscount(valueOf(5));
        slabsBuilder.addSlab().from(5000).to(7000).withDiscount(valueOf(10));
        slabsBuilder.addSlab().from(7000).to(10000).withDiscount(valueOf(15));
        slabsBuilder.addSlab().from(10000).to(15000).withDiscount(valueOf(20));
        slabsBuilder.addSlab().from(15000).toMax().withDiscount(valueOf(30));
        final DiscountSlabs slabs = slabsBuilder.build();

        assertEquals(valueOf(600), discountCalculator.calculate(valueOf(8000), slabs));
        assertEquals(valueOf(1700), discountCalculator.calculate(valueOf(14000), slabs));
        assertEquals(valueOf(6100), discountCalculator.calculate(valueOf(29000), slabs));
    }

    @Test
    public void shouldCalculateDiscountsForBoundaries() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(0).to(5000).withDiscount(valueOf(5));
        slabsBuilder.addSlab().from(5000).to(7000).withDiscount(valueOf(10));
        slabsBuilder.addSlab().from(7000).to(10000).withDiscount(valueOf(15));
        slabsBuilder.addSlab().from(10000).to(15000).withDiscount(valueOf(20));
        slabsBuilder.addSlab().from(15000).toMax().withDiscount(valueOf(30));
        final DiscountSlabs slabs = slabsBuilder.build();

        assertEquals(valueOf(0), discountCalculator.calculate(valueOf(0), slabs));
        assertEquals(valueOf(250), discountCalculator.calculate(valueOf(5000), slabs));

        assertEquals(valueOf(249.95), discountCalculator.calculate(valueOf(4999), slabs));
        assertEquals(valueOf(250.1), discountCalculator.calculate(valueOf(5001), slabs));

        assertEquals(valueOf(899.85), discountCalculator.calculate(valueOf(9999), slabs));
        assertEquals(valueOf(900.2), discountCalculator.calculate(valueOf(10001), slabs));

        assertEquals(valueOf(1900), discountCalculator.calculate(valueOf(15000), slabs));
        assertEquals(BigDecimal.ZERO, discountCalculator.calculate(BigDecimal.ZERO, slabs));
    }
}
