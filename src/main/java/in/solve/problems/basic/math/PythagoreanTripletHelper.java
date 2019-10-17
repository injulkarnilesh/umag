package in.solve.problems.basic.math;

import java.util.Arrays;

public class PythagoreanTripletHelper {

    public static Integer[] isPresent(final Integer[] array) {
        if (array == null || array.length < 3) {
            return new Integer[0];
        }
        Arrays.sort(array);

        for (int i = array.length - 1; i > 1 ; i--) {
            int j = 0;
            int k = i - 1;

            while (j < k) {
                final int sumOfSquares = square(array[j]) + square(array[k]);
                final Integer targetSquare = square(array[i]);
                if (sumOfSquares == targetSquare) {
                    return new Integer[]{
                            array[j], array[k], array[i]
                    };
                } else if (sumOfSquares > targetSquare) {
                    k--;
                } else {
                    j++;
                }
            }

        }


        return new Integer[0];
    }

    private static Integer square(final Integer number) {
        return number*number;
    }

}
