package in.solve.problems.basic.math;

import java.util.Arrays;

public class PermutationHelper {

    public static String findNthPermutation(final String str, final int n) {
        if (str == null) {
            return null;
        }
        final int factorial = factorial(str.length());
        if (n > factorial) {
            throw new IllegalArgumentException("Invalid 'n' can accept upto " + factorial + " for string " + str);
        }
        final char[] strCharacters = str.toCharArray();
        Arrays.sort(strCharacters);
        return findNthPermutation(strCharacters, n);
    }

    private static String findNthPermutation(final char[] characters, final int n) {
        int length = characters.length;
        if (length == 0 || length == 1 || n == 0) {
            return new String(characters);
        }
        int d = (n-1)/factorial(length - 1);
        int m = (n-1)%factorial(length - 1);

        final char[] remainingChars = new char[length - 1];
        for (int i = 0, j =0; j < length; j++) {
            if (j != d) {
                remainingChars[i] = characters[j];
                i++;
            }
        }

        return characters[d] + findNthPermutation(remainingChars, m + 1);
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }
}
