package in.solve.problems.basic.arrays;

import java.util.ArrayList;
import java.util.List;

public class CommonElementFinder {

    public static int[] find(final int[][] arrays) {

        final List<Integer> common = new ArrayList<>();
        if (arrays.length == 0 ){
            return common.stream().mapToInt(Integer::intValue).toArray();
        }

        final int[] indices = new int[arrays.length];

        while (true) {
            boolean outOfElements = false;
            for (int i = 0; i < indices.length; i++) {
                if (!outOfElements) {
                    outOfElements = indices[i] >= arrays[i].length;
                }

            }
            if (outOfElements) {
                break;
            }

            int maxElementArrayIndex = identifyMaxElementArray(arrays, indices);
            int maxElement = arrays[maxElementArrayIndex][indices[maxElementArrayIndex]];

            for (int i = 0; i < indices.length; i++) {
                while (arrays[i][indices[i]] < maxElement) {
                    if (indices[i] == arrays[i].length - 1) {
                        break;
                    } else {
                        indices[i] = indices[i] + 1;
                    }
                }
            }

            boolean allAreSame = false;
            for (int i = 0; i < indices.length - 1; i++) {
                allAreSame = arrays[i][indices[i]] == arrays[i+1][indices[i+1]];
                if (!allAreSame) {
                    break;
                }
            }
            if (allAreSame) {
                common.add(arrays[0][indices[0]]);
            }

            for (int i = 0; i < indices.length; i++) {
                indices[i] = indices[i] + 1;
            }

        }

        return common.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int identifyMaxElementArray(final int[][] arrays, final int[] indices) {
        int maxArrayIndex = 0;
        int maxElement = arrays[0][indices[0]];

        for (int i = 1; i < indices.length; i++) {
            if (arrays[i][indices[i]] > maxElement) {
                maxElement = arrays[i][indices[i]];
                maxArrayIndex = i;
            }
        }

        return maxArrayIndex;

    }
}
