package in.solve.problems.basic.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sets {

    public static <T>  List<Set<T>> subSetsOf(final Set<T> set) {
        final List<Set<T>> subSets = new ArrayList<>();
        final List<T> list = new ArrayList<>(set);
        if (set.size() == 0) {
            return subSets;
        }

        final int n = (int) Math.pow(2, set.size());
        for (int i = 0; i < n; i++) {
            final Set<T> subset = new HashSet<>();
            final String binaryString = getBinaryString(i, set.size());
            for (int j = binaryString.length() - 1; j >= 0; j--) {
                if (binaryString.charAt(j) == '1') {
                    final T element = list.get(j);
                    subset.add(element);
                }
            }
            subSets.add(subset);
        }
        return subSets;
    }

    static String getBinaryString(final int number, final int size) {
        return String.format("%" + size + "s", Integer.toBinaryString(number))
                .replace(" ", "0");
    }

}
