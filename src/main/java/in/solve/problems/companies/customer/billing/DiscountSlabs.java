package in.solve.problems.companies.customer.billing;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DiscountSlabs {

    private List<Slab> slabs = new ArrayList<Slab>();

    private DiscountSlabs(final List<SlabBuilder> slabBuilders) {
        for (SlabBuilder slabBuilder : slabBuilders) {
            this.slabs.add(Slab.createNew(slabBuilder));
        }
    }

    static DiscountSlabs createNew(final List<SlabBuilder> slabBuilders) {
        return new DiscountSlabs(slabBuilders);
    }

    public Iterator<Slab> getSlabs() {
        return Collections.unmodifiableCollection(this.slabs).iterator();
    }
}
