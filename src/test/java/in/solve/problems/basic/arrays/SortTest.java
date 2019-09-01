package in.solve.problems.basic.arrays;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SortTest {

    @Test
    public void shouldSortEmptyArray() {
        int[] array = new int [0];
        Sort.quickSort(array);
        assertThat(array, is(new int[0]));
    }

    @Test
    public void shouldSortSingleElementArray() {
        int[] array = new int[] {1};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {1}));
    }

    @Test
    public void shouldSortAlreadySortedArrayOfTwoElements() {
        int[] array = new int[] {1, 2};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {1, 2}));
    }

    @Test
    public void shouldSortDescendingSortedArrayOfTwoElements() {
        int[] array = new int[] {2, 1};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {1, 2}));
    }

    @Test
    public void shouldQuickSort() {
        int[] array = new int[] {12, 4, 2, 1, 5, 7, 2, 45, 14, 3, 9};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {1, 2, 2, 3, 4, 5, 7, 9, 12, 14, 45}));
    }

    @Test
    public void shouldQuickSortLargerArray() {
        int[] array = new int[] {12, 4, 12, 61, 25, 7, 21, 5, 42, 53, 19, 23, 67, 89, 23, 56, 11, 3, 23, 87, 89, 12, 90, 4, 56};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {3, 4, 4, 5, 7, 11, 12, 12, 12, 19, 21, 23, 23, 23, 25, 42, 53, 56, 56, 61, 67, 87, 89, 89, 90}));
    }

    @Test
    public void shouldQuickSortSortedArray() {
        int[] array = new int[] {1, 3, 4, 7, 9, 13, 16, 26, 35, 41, 44, 47, 50, 66, 75, 89, 90, 100};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {1, 3, 4, 7, 9, 13, 16, 26, 35, 41, 44, 47, 50, 66, 75, 89, 90, 100}));
    }

    @Test
    public void shouldQuickSortDescendingSortedArray() {
        int[] array = new int[] {100, 90, 88, 78, 71, 60, 56, 54, 52, 50, 43, 40, 36, 36, 33, 20, 11, 6};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {6, 11, 20, 33, 36, 36, 40, 43, 50, 52, 54, 56, 60, 71, 78, 88, 90, 100}));
    }

    @Test
    public void shouldQuickSortSameElementArray() {
        int[] array = new int[] {5, 5, 5, 5, 5};
        Sort.quickSort(array);
        assertThat(array, is(new int[] {5, 5, 5, 5, 5}));
    }
}
