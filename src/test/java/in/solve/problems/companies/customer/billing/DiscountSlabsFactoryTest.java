package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DiscountSlabsFactoryTest {

    @Test
    public void shouldHaveDiscountSlabsForRegularCustomer() throws Exception {
        final DiscountSlabs slabs = DiscountSlabsFactory.getInstance().getSlabs(CustomerType.REGULAR);

        final Iterator<Slab> slabIterator = slabs.getSlabs();
        final List<Slab> actualSlabs = new ArrayList<Slab>();
        while (slabIterator.hasNext()) {
            actualSlabs.add(slabIterator.next());
        }
        assertEquals("Unexpected number of slabs", 4, actualSlabs.size());
        final Slab firstExpectedSlab = SlabBuilder.createNew().from(0).to(5000).withDiscount(BigDecimal.ZERO).buildASlab();
        final Slab secondExpectedSlab = SlabBuilder.createNew().from(5000).to(7000).withDiscount(new BigDecimal(5)).buildASlab();
        final Slab thirdExpectedSlab = SlabBuilder.createNew().from(7000).to(10000).withDiscount(new BigDecimal(10)).buildASlab();
        final Slab fourthExpectedSlab = SlabBuilder.createNew().from(10000).toMax().withDiscount(new BigDecimal(15)).buildASlab();
        assertEquals("First slab mismatch", firstExpectedSlab, actualSlabs.get(0));
        assertEquals("Second slab mismatch", secondExpectedSlab, actualSlabs.get(1));
        assertEquals("Third slab mismatch", thirdExpectedSlab, actualSlabs.get(2));
        assertEquals("Fourth slab mismatch", fourthExpectedSlab, actualSlabs.get(3));
    }

    @Test
    public void shouldHaveDiscountSlabsForPremiumCustomer() throws Exception {
        final DiscountSlabs slabs = DiscountSlabsFactory.getInstance().getSlabs(CustomerType.PREMIUM);

        final Iterator<Slab> slabIterator = slabs.getSlabs();
        final List<Slab> actualSlabs = new ArrayList<Slab>();
        while (slabIterator.hasNext()) {
            actualSlabs.add(slabIterator.next());
        }
        assertEquals("Unexpected number of slabs", 5, actualSlabs.size());
        final Slab firstExpectedSlab = SlabBuilder.createNew().from(0).to(5000).withDiscount(BigDecimal.valueOf(5)).buildASlab();
        final Slab secondExpectedSlab = SlabBuilder.createNew().from(5000).to(7000).withDiscount(new BigDecimal(10)).buildASlab();
        final Slab thirdExpectedSlab = SlabBuilder.createNew().from(7000).to(10000).withDiscount(new BigDecimal(15)).buildASlab();
        final Slab fourthExpectedSlab = SlabBuilder.createNew().from(10000).to(15000).withDiscount(new BigDecimal(20)).buildASlab();
        final Slab fifthExpectedSlab = SlabBuilder.createNew().from(15000).toMax().withDiscount(new BigDecimal(25)).buildASlab();
        assertEquals("First slab mismatch", firstExpectedSlab, actualSlabs.get(0));
        assertEquals("Second slab mismatch", secondExpectedSlab, actualSlabs.get(1));
        assertEquals("Third slab mismatch", thirdExpectedSlab, actualSlabs.get(2));
        assertEquals("Fourth slab mismatch", fourthExpectedSlab, actualSlabs.get(3));
        assertEquals("Fifth slab mismatch", fifthExpectedSlab, actualSlabs.get(4));
    }
}
