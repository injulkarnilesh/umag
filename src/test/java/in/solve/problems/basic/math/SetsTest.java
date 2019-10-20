package in.solve.problems.basic.math;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SetsTest {

    @Test
    public void shouldFindAllSubSetsOfEmptySet() {
        final Set<Integer> set = new HashSet<>();

        final List<Set<Integer>>  subsets = Sets.subSetsOf(set);

        assertThat(subsets, is(empty()));
    }

    @Test
    public void shouldFindSubSetsOfSingleElementSet() {
        final Set<Integer> set = new HashSet<>();
        set.add(1);

        final List<Set<Integer>>  subsets = Sets.subSetsOf(set);

        assertThat(subsets, hasSize(2));
        assertThat(subsets, containsInAnyOrder(setOf(), setOf(1)));
    }

    @Test
    public void shouldFindSubSetsOfTwoElementSet() {
        final Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        final List<Set<Integer>>  subsets = Sets.subSetsOf(set);

        assertThat(subsets, hasSize(4));
        assertThat(subsets, containsInAnyOrder(setOf(), setOf(2), setOf(1), setOf(1, 2)));
    }

    @Test
    public void shouldFindSubSetsOfThreeElementSet() {
        final Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(2);
        set.add(1);

        final List<Set<Integer>>  subsets = Sets.subSetsOf(set);

        assertThat(subsets, hasSize(8));
        assertThat(subsets, containsInAnyOrder(setOf(),
                setOf(3), setOf(2), setOf(1),
                setOf(1, 2), setOf(1, 3), setOf(2, 3), setOf(1, 2, 3)));
    }

    @Test
    public void shouldTestBinaryConversion() {
        final String binaryString = Sets.getBinaryString(1, 2);
        assertThat(binaryString, is("01"));
    }

    private Set<Integer> setOf(final int ...numbers) {
        return IntStream.of(numbers)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toSet());
    }
}
