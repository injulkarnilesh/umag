package in.solve.problems.companies.cafe.billing;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;

public class FixedDiscountCalculatorTest {

    @Test
    public void shouldHaveFixedDiscount() {
        final BigDecimal amount = BigDecimal.valueOf(12.12);

        final DiscountCalculator<BilledItem> calculator = FixedDiscountCalculator.of(amount);

        assertThat(calculator.calculate(null), Matchers.is(amount));
    }
}
