package in.solve.problems.ctci.ch1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q4AnagramCheck {

    public static boolean areAnagram(String input1, String input2) {
        if (input1 == null || input1.length() == 0 || input2 == null || input2.length() == 0) {
            return false;
        }
        //return checkWithSort(input1, input2);
        //return checkWithArray(input1, input2);
        return checkWithMap(input1, input2);
    }

    private static boolean checkWithArray(String input1, String input2) {
        int[] counters = new int[256];
        for (int i = 0; i < input1.length(); i++) {
            int c = input1.charAt(i);
            counters[c]++;
        }
        for (int i = 0; i < input2.length(); i++) {
            int c = input2.charAt(i);
            counters[c]--;
        }

        for (int i = 0; i < counters.length; i++) {
            if (counters[i] != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkWithMap(String input1, String input2) {
        Map<Character, Integer> counters = new HashMap<>();
        for (int i = 0; i < input1.length(); i++) {
            char c = input1.charAt(i);
            counters.computeIfPresent(c, (ch, count) -> ++count);
            counters.putIfAbsent(c, 1);
        }
        for (int i = 0; i < input2.length(); i++) {
            char c = input2.charAt(i);
            if (!counters.containsKey(c)) {
                return false;
            }
            counters.computeIfPresent(c, (ch, count) -> --count);
        }

        for (Integer value : counters.values()) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkWithSort(String input1, String input2) {
        char[] charArray1 = input1.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = input2.toCharArray();
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

}
