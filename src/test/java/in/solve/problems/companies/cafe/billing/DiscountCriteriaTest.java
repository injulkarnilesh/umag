package in.solve.problems.companies.cafe.billing;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiscountCriteriaTest {

    @Test
    public void shouldAndTwoTrueCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> true;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> true;

        final DiscountCriteria<BilledItem> andCriteria = criteria1.and(criteria2);

        assertTrue(andCriteria.isApplicable(null));
    }

    @Test
    public void shouldAndTwoFalseCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> false;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> false;

        final DiscountCriteria<BilledItem> andCriteria = criteria1.and(criteria2);

        assertFalse(andCriteria.isApplicable(null));
    }

    @Test
    public void shouldAndOneTrueOneFalseCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> true;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> false;

        final DiscountCriteria<BilledItem> andCriteria = criteria1.and(criteria2);

        assertFalse(andCriteria.isApplicable(null));
    }

    @Test
    public void shouldOrTwoTrueCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> true;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> true;

        final DiscountCriteria<BilledItem> orCriteria = criteria1.or(criteria2);

        assertTrue(orCriteria.isApplicable(null));
    }

    @Test
    public void shouldOrTwoFalseCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> false;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> false;

        final DiscountCriteria<BilledItem> orCriteria = criteria1.or(criteria2);

        assertFalse(orCriteria.isApplicable(null));
    }

    @Test
    public void shouldOrOneTrueOneFalseCriteria() {
        final DiscountCriteria<BilledItem> criteria1 = (item) -> true;
        final DiscountCriteria<BilledItem> criteria2 = (item) -> false;

        final DiscountCriteria<BilledItem> orCriteria = criteria1.or(criteria2);

        assertTrue(orCriteria.isApplicable(null));
    }

    @Test
    public void shouldNegateTrueCriteria() {
        final DiscountCriteria<BilledItem> trueOne = item -> true;

        final DiscountCriteria<BilledItem> negated = trueOne.not();

        assertFalse(negated.isApplicable(null));
    }

    @Test
    public void shouldNegateFalseCriteria() {
        final DiscountCriteria<BilledItem> trueOne = item -> false;

        final DiscountCriteria<BilledItem> negated = trueOne.not();

        assertTrue(negated.isApplicable(null));
    }
}
