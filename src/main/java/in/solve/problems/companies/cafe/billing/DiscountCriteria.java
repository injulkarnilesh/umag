package in.solve.problems.companies.cafe.billing;

interface DiscountCriteria<T> {
    boolean isApplicable(final T item);

    default DiscountCriteria<T> and(final DiscountCriteria<T> other) {
        return item -> this.isApplicable(item) && other.isApplicable(item);
    }

    default DiscountCriteria<T> or(final DiscountCriteria<T> other) {
        return item -> this.isApplicable(item) || other.isApplicable(item);
    }

    default DiscountCriteria<T> not() {
        return item -> !this.isApplicable(item);
    }

}
