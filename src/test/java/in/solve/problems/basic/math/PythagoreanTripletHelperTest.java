package in.solve.problems.basic.math;

import org.junit.Test;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PythagoreanTripletHelperTest {

    @Test
    public void shouldNotFindPythagoreanTripletInEmptyArray() {
        final Integer[] array = new Integer[0];

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(emptyArray()));
    }

    @Test
    public void shouldNotFindTripletInArrayOfSizeLessThan3() {
        final Integer[] array = new Integer[] {2, 1};

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(emptyArray()));
    }

    @Test
    public void shouldFindTripletInThreeElementArray() {
        final Integer[] array = new Integer[] {3, 5, 4};

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(new Integer[] {3, 4, 5}));
    }

    @Test
    public void shouldNotFindTripletInThreeElementArrayIfNotFound() {
        final Integer[] array = new Integer[] {3, 5, 1};

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(emptyArray()));
    }

    @Test
    public void shouldFindTripletIfPresentInLargerArray() {
        final Integer[] array = new Integer[] {6, 8, 3, 5, 4, 1};

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(new Integer[] {3, 4, 5}));
    }

    @Test
    public void shouldNotFindTripletIfNotPresentInLargerArray() {
        final Integer[] array = new Integer[] {6, 8, 3, 9, 4, 1};

        final Integer[] triplet = PythagoreanTripletHelper.isPresent(array);

        assertThat(triplet, is(emptyArray()));
    }
}
