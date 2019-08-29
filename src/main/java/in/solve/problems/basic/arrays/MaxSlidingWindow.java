package in.solve.problems.basic.arrays;

public class MaxSlidingWindow {

    public static int[] of(final int[] array, int k) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        k = k > array.length? array.length: k;
        int window[] = new int[array.length - (k -1)];
        int start = 0;
        int end = start + (k - 1);

        for(int i = 0; end < array.length; start++, end++, i++) {
            int max = max(array, start, end);
            window[i] = max;
        }

        return window;
    }

    private static int max(final int[] array, final int start, final int end) {
        int max = array[start];
        for (int i = start + 1; i <= end; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
