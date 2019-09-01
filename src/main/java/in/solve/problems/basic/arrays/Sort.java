package in.solve.problems.basic.arrays;

public class Sort {

    public static void quickSort(final int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int l = 0;
        int r = array.length - 1;
        quickSort(array, l, r);
    }

    private static void quickSort(final int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int sortedPosition = partition(array, l, r);
        quickSort(array, l, sortedPosition - 1);
        quickSort(array, sortedPosition+1, r);
    }

    private static int partition(final int [] array, int l, int r) {
        int pivotIndex = l + ((r-l)/2);

        while(l < r) {
            while (l < r && array[l] < array[pivotIndex]) {
                l++;
            }

            while (l < r && array[r] >= array[pivotIndex]) {
                r--;
            }
            swap(array, l, r);

            if (l == pivotIndex) {
                pivotIndex = r;
            } else if (r == pivotIndex) {
                pivotIndex = l;
            }

        }

        swap(array, l, pivotIndex);
        return l;
    }

    private static void swap(final int[] array, final int l, final int r) {
        int temp = array[l];
        array[l] = array[r];
        array[r] = temp;
    }
}


