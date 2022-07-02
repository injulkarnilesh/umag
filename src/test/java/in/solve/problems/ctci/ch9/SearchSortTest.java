package in.solve.problems.ctci.ch9;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchSortTest {

    @Test
    public void testMergingSortedArrayBIntoSortedArrayAWhenAHasPlaceForB() {
        int[] a = new int[] {1, 10, 30, 50, 60, 66, -1, -1, -1, -1};
        int[] b = new int[] {12, 40, 45, 90};
        int aSize = 6;
        int bSize = 4;

        SearchSort.mergeInPlaceSortedArrays(a, b, aSize, bSize);

        assertTrue(isSorted(a));
    }

    @Test
    public void testAnagramSort() {
        List<String> strings = Lists.newArrayList("abcd", "xyz", "pqr", "adcb", "ab", "yzx", "cdba", "xyz", "rpq");
        List<String> sorted = SearchSort.sort(strings);
        assertThat(sorted, is(Lists.newArrayList("ab", "abcd", "adcb", "cdba", "pqr", "rpq", "xyz", "yzx", "xyz")));
    }

    @Test
    public void findElementInSortedRotatedArray() {
        int[] array = new int[] {40, 50, 60, 70, 80, 2, 12, 32, 35};
        for (int i = 0; i < array.length; i++) {
            int index = SearchSort.findInRotatedSortedArray(array, array[i]);
            assertThat("Failed to find element :" + array[i], index, is(i));
            index = SearchSort.findInRotatedSortedArray(array, array[i]+1);
            assertThat("Failed to find element :" + (array[i] + 1), index, is(-1));
            index = SearchSort.findInRotatedSortedArray(array, array[i]-1);
            assertThat("Failed to find element :" + (array[i] -1), index, is(-1));
        }
    }

    @Test
    public void testFindingElementInMatrix() {
        int matrix[][] = new int[][] {
                {1, 15, 40},
                {4, 27, 60},
                {6, 32, 66},
                {8, 47, 70},
                {10, 50, 75}
        };
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 10));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 4));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 32));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 66));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 40));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 27));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 15));
        assertTrue(SearchSort.findElementInSortedMatrix(matrix, 75));

        assertFalse(SearchSort.findElementInSortedMatrix(matrix, 12));
        assertFalse(SearchSort.findElementInSortedMatrix(matrix, 35));
        assertFalse(SearchSort.findElementInSortedMatrix(matrix, 71));
    }

    private boolean isSorted(int[]  array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }
}