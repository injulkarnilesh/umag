package in.solve.problems.companies.cafe.billing;

final class BillMinimumItemCountCriteria implements DiscountCriteria<Bill> {

    private final Item item;
    private final int minCount;

    private BillMinimumItemCountCriteria(final Item item, final int minCount) {
        this.item = item;
        this.minCount = minCount;
    }

    public static BillMinimumItemCountCriteria with(final Item item, final int minCount) {
        return new BillMinimumItemCountCriteria(item, minCount);
    }

    @Override
    public boolean isApplicable(final Bill bill) {
        return bill.getItems()
                .stream()
                .anyMatch(billedItem ->
                        billedItem.getItem().equals(item) &&
                        billedItem.getCount() > minCount
                );
    }

    @Override
    public String toString() {
        return item.getName() + " more than " + minCount;
    }
}
