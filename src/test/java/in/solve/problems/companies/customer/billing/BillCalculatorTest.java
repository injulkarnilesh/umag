package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class BillCalculatorTest {

    @Mock DiscountCalculator discountCalculator;
    @Mock DiscountSlabsFactory discountSlabsFactory;
    @InjectMocks BillCalculatorImpl billCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCalculateBillByReducingDiscount() throws Exception {
        final BigDecimal discount = BigDecimal.valueOf(Math.random() * 1000);
        final BigDecimal amount = BigDecimal.valueOf(14000);
        final CustomerType customerType = CustomerType.REGULAR;
        final DiscountSlabs discountSlabs = DiscountSlabsBuilder.createNew().build();

        when(discountSlabsFactory.getSlabs(customerType)).thenReturn(discountSlabs);
        when(discountCalculator.calculate(amount, discountSlabs)).thenReturn(discount);

        final BigDecimal billAmount = billCalculator.calculate(customerType, amount);

        verify(discountSlabsFactory).getSlabs(customerType);
        verify(discountCalculator).calculate(amount, discountSlabs);

        assertEquals("Billing amount mismatch", amount.subtract(discount), billAmount);
    }
}
