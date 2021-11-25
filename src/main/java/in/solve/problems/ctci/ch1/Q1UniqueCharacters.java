package in.solve.problems.ctci.ch1;

import java.util.HashSet;
import java.util.Set;

public class Q1UniqueCharacters {

    public static boolean hasUniqueCharacters(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        return bruteForce(input);
        //return withExtraSpace(input);
        //return withSmallExtraSpace(input);
    }

    private static boolean bruteForce(String input) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean withExtraSpace(String input) {
        Set<Character> set = new HashSet<>();
        for (char ch : input.toCharArray()){
            set.add(Character.valueOf(ch));
        }
        return input.length() == set.size();
    }

    private static boolean withSmallExtraSpace(String input) {
        int asciiMax = 128;
        if (input.length() > asciiMax) {
            return false;
        }
        boolean[] flags = new boolean[asciiMax];
        for (char ch : input.toCharArray()) {
            int ascii = ch;
            if (ascii > asciiMax) {
                throw new IllegalArgumentException("ASCII characters supported only");
            }
            if (flags[ascii]) {
                return false;
            }
            flags[ascii] = true;
        }
        return true;
    }

    public static boolean smallCaseAlphabeticStringHasUniqueChars(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        int flag = 0;
        for (char ch : input.toCharArray()) {
            int ascii = ch - 'a';
            if (ascii < 0 || ascii > 25) {
                throw new IllegalArgumentException("Small case alphabets only supported");
            }
            int place = 1 << ascii;
            int check = place & flag;
            if (check > 0) {
                return false;
            }
            flag |= place;
        }

        return true;
    }

}
