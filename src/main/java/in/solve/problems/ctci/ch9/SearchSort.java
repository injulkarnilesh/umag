package in.solve.problems.ctci.ch9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchSort {

    public static void mergeInPlaceSortedArrays(int[] a, int[] b, int aSize, int bSize) {
        int aFromEnd = a.length - 1;
        int bFromEnd = bSize - 1;
        int aFromLastElement = aSize - 1;
        assert  a.length == aSize + bSize;

        while (aFromEnd > aSize - 1) {
            if (a[aFromLastElement] >= b[bFromEnd]) {
                a[aFromEnd] = a[aFromLastElement];
                aFromLastElement--;
            } else {
                a[aFromEnd] = b[bFromEnd];
                bFromEnd--;
            }
            aFromEnd--;
        }

        while (bFromEnd >= 0) {
            if (a[aFromLastElement] >= b[bFromEnd]) {
                a[aFromEnd] = a[aFromLastElement];
                aFromLastElement--;
            } else {
                a[aFromEnd] = b[bFromEnd];
                bFromEnd--;
            }
            aFromEnd--;
        }
    }

    public static List<String> sort(List<String> strings) {
        return strings.stream().sorted(new AnagramComparator()).collect(Collectors.toList());
    }

    public static int findInRotatedSortedArray(int[] array, int element) {
        return findInRotatedSortedArray(array, element, 0, array.length-1);
    }

    private static int findInRotatedSortedArray(int[] array, int element, int from, int to) {
        if (to < from) {
            return -1;
        }
        int m = (from + to)/2;
        if (array[m] == element) {
            return m;
        }
        if (array[from] < array[m]) {
            if (element >= array[from] && element <= array[m]) {
                return findInRotatedSortedArray(array, element, from, m-1);
            } else {
                return findInRotatedSortedArray(array, element, m+1, to);
            }
        } else {
            if (element >= array[m] && element <= array[to]) {
                return findInRotatedSortedArray(array, element, m+1, to);
            } else {
                return findInRotatedSortedArray(array, element, from, m-1);
            }
        }
    }

    public static boolean findElementInSortedMatrix(int[][] matrix, int element) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        int row = 0;
        int col = columnSize-1;

        while (row < rowSize && col >=0 ){
            if (matrix[row][col] == element) {
                return true;
            } else if (element < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    static class AnagramComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            char[] s1Chars = s1.toCharArray();
            char[] s2Chars = s2.toCharArray();
            Arrays.sort(s1Chars);
            Arrays.sort(s2Chars);

            return new String(s1Chars).compareTo(new String(s2Chars));
        }
    }
}
