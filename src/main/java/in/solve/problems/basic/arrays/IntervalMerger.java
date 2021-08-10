package in.solve.problems.basic.arrays;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class IntervalMerger {

  public static final Pair<Integer, Integer> ALREADY_MERGED = null;

  public static Pair<Integer, Integer>[] mergeOverlapping(
      final Pair<Integer, Integer>[] intervals) {

    for (int i = 0; i < intervals.length; i++) {
      for (int j = i + 1; j < intervals.length; j++) {
        if (intervals[i] != ALREADY_MERGED && intervals[j] != ALREADY_MERGED && overlaps(
            intervals[i], intervals[j])) {
          final Pair<Integer, Integer> merged = merge(intervals[i], intervals[j]);
          intervals[i] = merged;
          intervals[j] = ALREADY_MERGED;
        }
      }
    }

    return Arrays.stream(intervals).filter(Objects::nonNull)
        .toArray(Pair[]::new);
  }

  public static Pair<Integer, Integer>[] mergeOverlappingWithSort(
      final Pair<Integer, Integer>[] intervals) {
    if (intervals == null || intervals.length < 2) {
      return intervals;
    }
    Arrays.sort(intervals, Comparator.comparing(Pair::getKey));
    for (int i = 1; i < intervals.length; i++) {
      if (overlaps(intervals[i], intervals[i - 1])) {
        final Pair<Integer, Integer> merged = merge(intervals[i], intervals[i - 1]);
        intervals[i] = merged;
        intervals[i - 1] = ALREADY_MERGED;
      }
    }
    return Arrays.stream(intervals).filter(Objects::nonNull)
        .toArray(Pair[]::new);
  }

  private static Pair<Integer, Integer> merge(final Pair<Integer, Integer> interval,
      final Pair<Integer, Integer> anotherInterval) {
    return new Pair<>(
        Math.min(interval.getKey(), anotherInterval.getKey()),
        Math.max(interval.getValue(), anotherInterval.getValue())
    );
  }

  private static boolean overlaps(final Pair<Integer, Integer> interval,
      final Pair<Integer, Integer> anotherInterval) {
    return (anotherInterval.getKey() <= interval.getValue() && anotherInterval.getKey() >= interval
        .getKey())
        || (interval.getKey() <= anotherInterval.getValue() && interval.getKey() >= anotherInterval
        .getKey());
  }
}
