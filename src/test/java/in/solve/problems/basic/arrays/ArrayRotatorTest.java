package in.solve.problems.basic.arrays;

import org.junit.Test;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
Write a function rotate(ar[], d, n) that rotates arr[] of size n by d elements.
 */
public class ArrayRotatorTest {

    @Test
    public void shouldNotRotateEmptyArray() {
        final String[] array = new String[0];
        final String[] rotatedArray = ArrayRotator.rotate(array, 1);
        assertThat(rotatedArray, is(emptyArray()));
    }

    @Test
    public void shouldRotateSingleElementArray() {
        final String[] array = new String[] {"A"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 1);
        assertThat(rotatedArray, is(new String[] {"A"}));
    }

    @Test
    public void shouldNotRotateArrayWhenDIsZero() {
        final String[] array = new String[] {"A", "B"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 0);
        assertThat(rotatedArray, is(new String[] {"A", "B"}));
    }

    @Test
    public void shouldBeGeneric() {
        final Integer[] array = new Integer[] {1};
        final Integer[] rotatedArray = ArrayRotator.rotate(array, 1);
        assertThat(rotatedArray, is(new Integer[] {1}));
    }

    @Test
    public void shouldRotateTwoElementArrayBy1() {
        final String[] array = new String[] {"A", "B"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 1);
        assertThat(rotatedArray, is(new String[] {"B", "A"}));
    }

    @Test
    public void shouldRotateThreeElementArrayBy1() {
        final String[] array = new String[] {"A", "B", "C"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 1);
        assertThat(rotatedArray, is(new String[] {"B", "C", "A"}));
    }

    @Test
    public void shouldRotateFourElementArrayBy2() {
        final String[] array = new String[] {"A", "B", "C", "D"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 2);
        assertThat(rotatedArray, is(new String[] {"C", "D", "A", "B"}));
    }

    @Test
    public void shouldRotateFiveElementsBy3() {
        final String[] array = new String[] {"A", "B", "C", "D", "E"};
        final String[] rotatedArray = ArrayRotator.rotate(array, 3);
        assertThat(rotatedArray, is(new String[] {"D", "E", "A", "B", "C"}));
    }
}
