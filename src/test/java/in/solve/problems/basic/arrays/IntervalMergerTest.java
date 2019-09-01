package in.solve.problems.basic.arrays;

import javafx.util.Pair;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IntervalMergerTest {

    @Test
    public void shouldMergeNoIntervals() {
        final Pair<Integer, Integer>[] intervals = new Pair[0];

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(emptyArray()));
    }

    @Test
    public void shouldMergeSingleInterval() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30)
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30)
        }));
    }

    @Test
    public void shouldMergeDisjointIntervals() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(31, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(31, 40)
        }));
    }

    @Test
    public void shouldJoinTwoOverlappingIntervalsWhenOneIsSubsetOfOther() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(21, 29),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30)
        }));
    }

    @Test
    public void shouldJoinTwoOverlappingIntervalsWhenOneOverlapsSomeOfOther() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(25, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 40)
        }));
    }

    @Test
    public void shouldMergeIntervals() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(1, 5),
                new Pair<>(5, 7),
                new Pair<>(25, 30),
                new Pair<>(10, 20),
                new Pair<>(5, 7),
                new Pair<>(26, 34),
                new Pair<>(10, 15),
                new Pair<>(30, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlapping(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(1, 7),
                new Pair<>(25, 40),
                new Pair<>(10, 20)
        }));
    }



    @Test
    public void shouldMergeNoIntervalsWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[0];

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(emptyArray()));
    }

    @Test
    public void shouldMergeSingleIntervalWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30)
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30)
        }));
    }

    @Test
    public void shouldMergeDisjointIntervalsWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(31, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(31, 40)
        }));
    }

    @Test
    public void shouldJoinTwoOverlappingIntervalsWhenOneIsSubsetOfOtherWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(21, 29),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 30)
        }));
    }

    @Test
    public void shouldJoinTwoOverlappingIntervalsWhenOneOverlapsSomeOfOtherWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(20, 30),
                new Pair<>(25, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(20, 40)
        }));
    }

    @Test
    public void shouldMergeIntervalsWithSorting() {
        final Pair<Integer, Integer>[] intervals = new Pair[] {
                new Pair<>(1, 5),
                new Pair<>(5, 7),
                new Pair<>(25, 30),
                new Pair<>(10, 20),
                new Pair<>(5, 7),
                new Pair<>(26, 34),
                new Pair<>(10, 15),
                new Pair<>(30, 40),
        };

        final Pair<Integer, Integer>[] merged = IntervalMerger.mergeOverlappingWithSort(intervals);

        assertThat(merged, is(new Pair[] {
                new Pair<>(1, 7),
                new Pair<>(10, 20),
                new Pair<>(25, 40),
        }));
    }
}
