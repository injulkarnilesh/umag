package in.solve.problems.basic.math;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void numbersShouldBeValid() {
        Arrays.asList("0", "11", "1000", "46.3" , "00.111", "-12", "-3.14")
                .forEach(ch -> assertTrue(ch + " is not valid", Numbers.isValid(ch)));
    }

    @Test
    public void alphabetShouldBeInValid() {
        Arrays.asList("A", "l", "pha", "be" , "t", "s")
                .forEach(ch -> assertFalse(ch + " is number", Numbers.isValid(ch)));
    }

    @Test
    public void specialCharShouldBeInValid() {
        Arrays.asList("!", "@", "#", "%" , "-", "`" , ".", " ", "1.1.1")
                .forEach(ch -> assertFalse(ch + " is number", Numbers.isValid(ch)));
    }
}
