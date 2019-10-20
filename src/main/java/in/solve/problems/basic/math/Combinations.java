package in.solve.problems.basic.math;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static List<List<Integer>> withSum(final int sum) {
        final List<List<Integer>> combinations = new ArrayList<>();
        if (sum == 0) {
            return combinations;
        }
        final List<Integer> sameElementCombination = new ArrayList<>();
        sameElementCombination.add(sum);
        combinations.add(sameElementCombination);
        forSum(combinations, new ArrayList<>(), sum);
        return combinations;
    }

    private static void forSum(final List<List<Integer>> combinations,
                               final List<Integer> currentCombination,
                               final int sum) {
        if (sum == 1) {
            return;
        }

        int start = currentCombination.size() == 0? 1: currentCombination.get(currentCombination.size() - 1);
        for (int i = start; i <= Math.floor(sum/2); i++) {
            int number1 = i;
            int number2 = sum - i;
            final List<Integer> newCombination = new ArrayList<>(currentCombination);
            newCombination.add(number1);
            newCombination.add(number2);
            combinations.add(newCombination);
            System.out.println(combinations);
            if (number2 != 1) {
                final List<Integer> addedCombination = new ArrayList<>(currentCombination);
                addedCombination.add(number1);
                forSum(combinations, addedCombination, number2);
            }
        }
    }
}
