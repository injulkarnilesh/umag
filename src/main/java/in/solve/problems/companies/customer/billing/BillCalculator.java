package in.solve.problems.companies.customer.billing;

import java.math.BigDecimal;

public interface BillCalculator {

    BigDecimal calculate(CustomerType customerType, BigDecimal amount);

}
