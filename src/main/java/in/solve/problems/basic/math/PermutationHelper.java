package in.solve.problems.basic.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PermutationHelper {

    public static String nthPermutation(final String str, final int n) {
        if (str == null) {
            return null;
        }
        final int factorial = factorial(str.length());
        if (n > factorial) {
            throw new IllegalArgumentException("Invalid 'n' can accept upto " + factorial + " for string " + str);
        }
        final char[] strCharacters = str.toCharArray();
        Arrays.sort(strCharacters);
        return nthPermutation(strCharacters, n);
    }

    private static String nthPermutation(final char[] characters, final int n) {
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

        return characters[d] + nthPermutation(remainingChars, m + 1);
    }

    static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorial(n-1);
    }

    public static Set<String> permutations(final String str) {
        final HashSet<String> permutations = new HashSet<>();
        if (str == null || str.length() == 0) {
            return permutations;
        }

        if (str.length() == 1) {
            permutations.add(str);
            return permutations;
        }

        for (int i = 0; i < str.length(); i++) {
            final char currentChar = str.charAt(i);
            String remainingString = "";
            if (i > 0) {
                remainingString = remainingString + str.substring(0, i);
            }
            if (i != str.length() - 1) {
                remainingString = remainingString + str.substring(i+1);
            }

            final Set<String> otherPermutations = permutations(remainingString);
            otherPermutations.forEach(p -> permutations.add(currentChar + p));
        }

        return permutations;
    }
}
