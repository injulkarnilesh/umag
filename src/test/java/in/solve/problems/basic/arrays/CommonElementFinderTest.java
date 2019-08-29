package in.solve.problems.basic.arrays;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
Given arrays sorted in non-decreasing order, print all common elements in these arrays.
 */
public class CommonElementFinderTest {

    @Test
    public void shouldNotFindCommonElementsInEmptyArrays() {
        final int[][] arrays = new int[0][0];

        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{}));
    }

    @Test
    public void shouldFindCommonElementsInSingleElementArrays() {
        final int[][] arrays = new int[][]{{1}, {1}, {1}};
        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{1}));
    }

    @Test
    public void shouldNotFindCommonElementsInDisjointSingleElementArrays() {
        final int[][] arrays = new int[][]{{1}, {2}, {1}};

        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{}));
    }

    @Test
    public void shouldFindCommonElementsInArrayWithCommonElementsAtDifferentPosition() {
        final int[][] arrays = new int[][]{{1, 2, 3}, {2, 3}, {1, 2, 5}};

        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{2}));
    }

    @Test
    public void testSample() {
        final int[][] arrays = new int[][]{{1, 5, 10, 20, 40, 80},
                                            {6, 7, 20, 80, 100},
                                            {3, 4, 15, 20, 30, 70, 80, 120}
        };

        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{20, 80}));
    }

    @Test
    public void testSample2() {
        final int[][] arrays = new int[][]{ {1, 5, 5},
                            {3, 4, 5, 5, 10},
                            {5, 5, 10, 20}};

        final int [] common = CommonElementFinder.find(arrays);

        assertThat(common, is(new int[]{5, 5}));
    }
}
