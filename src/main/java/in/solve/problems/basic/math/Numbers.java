package in.solve.problems.basic.math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Numbers {

    public static int missing(final int ...numbers) {
        final int n = numbers.length + 1;
        final int expectedSum = (n * (n + 1))/2;
        final int actualSum = IntStream.of(numbers).sum();
        return expectedSum - actualSum;
    }

    public static boolean isValid(final String str) {
        final Pattern p = Pattern.compile("[-]?\\d+[\\\\.]?\\d*");
        final Matcher m = p.matcher(str);
        return m.matches();
    }
}
