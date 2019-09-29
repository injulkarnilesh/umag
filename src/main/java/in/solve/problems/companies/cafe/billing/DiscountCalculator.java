package in.solve.problems.companies.cafe.billing;

import java.math.BigDecimal;

public interface DiscountCalculator<T> {

    BigDecimal calculate(T item);

}
