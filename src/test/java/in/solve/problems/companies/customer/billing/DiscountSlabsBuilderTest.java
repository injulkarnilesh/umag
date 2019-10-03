package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class DiscountSlabsBuilderTest {

    @Test
    public void shouldHaveEmptySlabs() throws Exception {
        final Iterator<Slab> slabs = DiscountSlabsBuilder.createNew().build().getSlabs();
        assertFalse(slabs.hasNext());
    }

    @Test
    public void shouldBuildWithSlabs() throws Exception {
        final DiscountSlabsBuilder builder = DiscountSlabsBuilder.createNew();
        builder.addSlab().from(0).to(5000).withDiscount(new BigDecimal(0));
        builder.addSlab().from(5000).to(10000).withDiscount(new BigDecimal(10));
        builder.addSlab().from(10000).toMax().withDiscount(new BigDecimal(20));
        final DiscountSlabs slabs = builder.build();

        final Iterator<Slab> actualSlabs = slabs.getSlabs();
        final List<Slab> slabList = new ArrayList<Slab>();
        while (actualSlabs.hasNext()) {
            slabList.add(actualSlabs.next());
        }
        assertEquals("Unexpected number of slabs", 3, slabList.size());
        final Slab firstExpectedSlab = SlabBuilder.createNew().from(0).to(5000).withDiscount(BigDecimal.ZERO).buildASlab();
        final Slab secondExpectedSlab = SlabBuilder.createNew().from(5000).to(10000).withDiscount(new BigDecimal(10)).buildASlab();
        final Slab thirdExpectedSlab = SlabBuilder.createNew().from(10000).toMax().withDiscount(new BigDecimal(20)).buildASlab();
        assertEquals("First slab mismatch", firstExpectedSlab, slabList.get(0));
        assertEquals("Second slab mismatch", secondExpectedSlab, slabList.get(1));
        assertEquals("Third slab mismatch", thirdExpectedSlab, slabList.get(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToModifyTheSlabs() throws Exception {
        final DiscountSlabsBuilder builder = DiscountSlabsBuilder.createNew();
        builder.addSlab().from(10).to(34).withDiscount(BigDecimal.ZERO);
        final DiscountSlabs discountSlabs = builder.build();

        final Iterator<Slab> slabs = discountSlabs.getSlabs();
        slabs.next();
        slabs.remove();
    }

    @Test(expected = InvalidSlabException.class)
    public void shouldNotCreateSlabWithToSmallerThanFrom() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(1000).to(999).withDiscount(BigDecimal.ZERO);
        slabsBuilder.build();
    }

    @Test(expected = InvalidSlabException.class)
    public void shouldNotCreateSlabWithNegativeDiscount() throws Exception {
        final DiscountSlabsBuilder slabsBuilder = DiscountSlabsBuilder.createNew();
        slabsBuilder.addSlab().from(1000).to(9999).withDiscount(BigDecimal.valueOf(-1));
        slabsBuilder.build();
    }
}
