package in.solve.problems.companies.customer.billing;


import java.math.BigDecimal;

public interface DiscountCalculator {

    BigDecimal calculate(BigDecimal amount, DiscountSlabs discountSlabs);
}
