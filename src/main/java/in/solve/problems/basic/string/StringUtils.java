package in.solve.problems.basic.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {

    public static String reverseWords(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final String[] words = str.split("\\s+");
        final int length = words.length;
        for (int i = 0; i < length /2; i++) {
            final String temp = words[i];
            words[i] = words[length - 1 - i];
            words[length - 1 - i] = temp;
        }
        return Arrays.stream(words)
                .collect(Collectors.joining(" "));
    }

    public static String removeDupCharacters(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final Map<Character, Boolean> characters = new HashMap<>();
        final StringBuilder output = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            if (!characters.containsKey(str.charAt(i))) {
                output.append(str.charAt(i));
            }
            characters.put(str.charAt(i), Boolean.TRUE);
        }
        return output.toString();
    }

    public static String orderedDuplicates(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final Map<Character, Integer> charCount = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            final Character character = str.charAt(i);
            charCount.compute(character, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return charCount.get(character) + 1;
                }
            });
        }
        return charCount.entrySet().stream()
                .filter((e) -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private static boolean isEmpty(final String str) {
        return str == null || str.length() == 0;
    }

    public static boolean canSegment(final String string, final List<String> words) {
        if (isEmpty(string)) {
            return false;
        }
        TrieDictionary dictionary = TrieDictionary.createNew();
        dictionary.add(words.toArray(new String[words.size()]));
        return canSegment(string, dictionary);
    }

    private static boolean canSegment(final String string, final TrieDictionary dictionary) {
        if (isEmpty(string)) {
            return true;
        }
        boolean canSegment = false;
        for (int i = 1; i <= string.length(); i++) {
            final String prefix = string.substring(0, i);
            final String remaining = string.substring(i);
            canSegment = dictionary.has(prefix) && canSegment(remaining, dictionary);
            if (canSegment) {
                return true;
            }
        }
        return canSegment;
    }

    public static Set<String> palindromeSubstrings(final String str) {
        final HashSet<String> palindromes = new HashSet<>();
        if (isEmpty(str)) {
            return palindromes;
        }

        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            palindromes.add(Character.toString(character));

            int left = i;
            int right = i + 1;
            final String evenPalindromeCurrent = "";
            collectPalindromesByMovingEitherSide(str, left, right, evenPalindromeCurrent, palindromes);

            left = i - 1;
            right = i + 1;
            final String oddPalindromeCurrent = Character.toString(str.charAt(i));
            collectPalindromesByMovingEitherSide(str, left, right, oddPalindromeCurrent, palindromes);
        }

        return palindromes;
    }

    private static void collectPalindromesByMovingEitherSide(final String str, int left, int right,
                                                             String currentPalindrome, final HashSet<String> palindromes) {
        while (left >= 0 && right < str.length()) {
            StringBuffer newPalindrome = new StringBuffer();

            if (str.charAt(left) == str.charAt(right)) {
                newPalindrome.append(str.charAt(left))
                        .append(currentPalindrome)
                        .append(str.charAt(right));
                currentPalindrome = newPalindrome.toString();
                palindromes.add(currentPalindrome);
            } else {
                break;
            }
            left--;
            right++;
        }
    }
}
