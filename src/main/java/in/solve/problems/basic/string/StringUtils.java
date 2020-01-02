package in.solve.problems.basic.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
}
