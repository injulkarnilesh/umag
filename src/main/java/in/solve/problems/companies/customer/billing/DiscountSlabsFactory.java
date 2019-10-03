package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class DiscountSlabsFactory {

    private static DiscountSlabsFactory INSTANCE = new DiscountSlabsFactory();
    private final Map<CustomerType, DiscountSlabs> customerToSlabs = new EnumMap(CustomerType.class);

    private DiscountSlabsFactory() {
        final DiscountSlabsBuilder regularSlabsBuilder = DiscountSlabsBuilder.createNew();
        regularSlabsBuilder.addSlab().from(0).to(5000).withDiscount(BigDecimal.ZERO);
        regularSlabsBuilder.addSlab().from(5000).to(7000).withDiscount(BigDecimal.valueOf(5));
        regularSlabsBuilder.addSlab().from(7000).to(10000).withDiscount(BigDecimal.valueOf(10));
        regularSlabsBuilder.addSlab().from(10000).toMax().withDiscount(BigDecimal.valueOf(15));
        final DiscountSlabs regularDiscountSlabs = regularSlabsBuilder.build();

        final DiscountSlabsBuilder premiumSlabsBuilds = DiscountSlabsBuilder.createNew();
        premiumSlabsBuilds.addSlab().from(0).to(5000).withDiscount(BigDecimal.valueOf(5));
        premiumSlabsBuilds.addSlab().from(5000).to(7000).withDiscount(BigDecimal.valueOf(10));
        premiumSlabsBuilds.addSlab().from(7000).to(10000).withDiscount(BigDecimal.valueOf(15));
        premiumSlabsBuilds.addSlab().from(10000).to(15000).withDiscount(BigDecimal.valueOf(20));
        premiumSlabsBuilds.addSlab().from(15000).toMax().withDiscount(BigDecimal.valueOf(25));
        final DiscountSlabs premiumDiscountSlabs = premiumSlabsBuilds.build();

        customerToSlabs.put(CustomerType.REGULAR, regularDiscountSlabs);
        customerToSlabs.put(CustomerType.PREMIUM, premiumDiscountSlabs);
    }

    public static DiscountSlabsFactory getInstance() {
        return INSTANCE;
    }


    public DiscountSlabs getSlabs(final CustomerType customerType) {
        return customerToSlabs.get(customerType);
    }
}
