package in.solve.problems.basic.math;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumbersTest {

    @Test
    public void shouldFindMissingNumberWithNAs2() {
        final int missing = Numbers.missing(2);
        assertThat(missing, is(1));
    }

    @Test
    public void shouldFindMissingNumberWIthNAs5() {
        final int missing = Numbers.missing(1, 3, 4, 5);
        assertThat(missing, is(2));
    }

    @Test
    public void shouldFindMissingNumberWIthNAs10() {
        final int missing = Numbers.missing(1, 3, 4, 5, 7, 2, 8, 9, 10);
        assertThat(missing, is(6));
    }
}
