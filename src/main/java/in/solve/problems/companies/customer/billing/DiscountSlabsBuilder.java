package in.solve.problems.companies.customer.billing;


import java.util.ArrayList;
import java.util.List;

public final class DiscountSlabsBuilder {

    private List<SlabBuilder> slabBuilders = new ArrayList<SlabBuilder>();

    private DiscountSlabsBuilder() {

    }

    public static DiscountSlabsBuilder createNew() {
        return new DiscountSlabsBuilder();
    }

    public SlabBuilder addSlab() {
        final SlabBuilder slabBuilder = SlabBuilder.createNew();
        this.slabBuilders.add(slabBuilder);
        return slabBuilder;
    }

    public DiscountSlabs build() {
        return DiscountSlabs.createNew(slabBuilders);
    }
}
