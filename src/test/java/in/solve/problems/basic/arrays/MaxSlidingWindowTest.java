package in.solve.problems.basic.arrays;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
 */
public class MaxSlidingWindowTest {

    @Test
    public void shouldFindForEmptyArray() {
        final int array[] = new int [0];
        assertThat(MaxSlidingWindow.of(array, 1), is(new int[]{}));
    }

    @Test
    public void shouldBeNullSafeWithEmptySlidingWindow() {
        assertThat(MaxSlidingWindow.of(null, 1), is(new int[]{}));
    }

    @Test
    public void shouldFindSlidingWindowForArrayOfSize1WithWindowOf1() {
        final int array[] = {1};
        assertThat(MaxSlidingWindow.of(array, 1), is(new int[]{1}));
    }

    @Test
    public void shouldFindSlidingWindowForArrayWithWindowSizeOf1() {
        final int array[] = {1, 2};
        assertThat(MaxSlidingWindow.of(array, 1), is(new int[]{1, 2}));
    }

    @Test
    public void shouldFindSlidingWindowAsMaxElementWhenWindowSizeIsArraySize() {
        final int array[] = {1, 2};
        assertThat(MaxSlidingWindow.of(array, 2), is(new int[]{2}));
    }

    @Test
    public void shouldFindSlidingWindowForArrayOfSize3AndWindowOf2() {
        final int array[] = {3, 2, 6};
        assertThat(MaxSlidingWindow.of(array, 2), is(new int[]{3, 6}));
    }

    @Test
    public void shouldFindSlidingWindow() {
        final int array[] = {3, 2, 6, 5, 2, 19, 3, 5, 7};
        assertThat(MaxSlidingWindow.of(array, 3), is(new int[]{6, 6, 6, 19, 19, 19, 7}));
    }

    @Test
    public void shouldFindSlidingWindowForDescendingArray() {
        final int array[] = {30, 20, 17, 15, 12, 10, 6, 2};
        assertThat(MaxSlidingWindow.of(array, 3), is(new int[]{30, 20, 17, 15, 12, 10}));
    }

    @Test
    public void shouldFindSlidingWindowOfMaxElementIfWindowSizeExceedsArraySize() {
        final int array[] = {3, 2, 6};
        assertThat(MaxSlidingWindow.of(array, 4), is(new int[]{6}));
    }
}
