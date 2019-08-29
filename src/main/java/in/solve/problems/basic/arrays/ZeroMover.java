package in.solve.problems.basic.arrays;

public class ZeroMover {

    public static int[] toEnd(final int[] array) {

        int nonZeroElementCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[nonZeroElementCount] = array[i];
                nonZeroElementCount++;
            }
        }
        while (nonZeroElementCount < array.length) {
            array[nonZeroElementCount++] = 0;
        }
        return array;
    }
}
