package in.solve.problems.basic.math;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CombinationsTest {

    @Test
    public void shouldNotFindNumbersWithSum0() {
        List<List<Integer>> combinations = Combinations.withSum(0);

        assertThat(combinations, is(empty()));
    }

    @Test
    public void shouldFindNumbersWithSum1() {
        final List<List<Integer>> combinations = Combinations.withSum(1);

        assertThat(combinations, hasSize(1));
        assertThat(combinations.get(0), hasSize(1));
        assertThat(combinations.get(0).get(0), is(1));
    }

    @Test
    public void shouldFindNumbersWithSum2() {
        final List<List<Integer>> combinations = Combinations.withSum(2);

        assertThat(combinations, hasSize(2));
        assertThat(combinations.get(0), contains(2));
        assertThat(combinations.get(1), contains(1, 1));
    }

    @Test
    public void shouldFindNumbersWithSum3() {
        final List<List<Integer>> combinations = Combinations.withSum(3);

        assertThat(combinations, hasSize(3));
        assertThat(combinations.get(0), contains(3));
        assertThat(combinations.get(1), contains(1, 2));
        assertThat(combinations.get(2), contains(1, 1, 1));
    }

    @Test
    public void shouldFindNumbersWithSum4() {
        final List<List<Integer>> combinations = Combinations.withSum(4);

        assertThat(combinations, hasSize(5));
        assertThat(combinations.get(0), contains(4));
        assertThat(combinations.get(1), contains(1, 3));
        assertThat(combinations.get(2), contains(1, 1, 2));
        assertThat(combinations.get(3), contains(1, 1, 1, 1));
        assertThat(combinations.get(4), contains(2, 2));
    }

    @Test
    public void shouldFindNumbersWithSum5() {
        final List<List<Integer>> combinations = Combinations.withSum(5);

        assertThat(combinations, hasSize(7));
        assertThat(combinations.get(0), contains(5));
        assertThat(combinations.get(1), contains(1, 4));
        assertThat(combinations.get(2), contains(1, 1, 3));
        assertThat(combinations.get(3), contains(1, 1, 1, 2));
        assertThat(combinations.get(4), contains(1, 1, 1, 1, 1));
        assertThat(combinations.get(5), contains(1, 2, 2));
        assertThat(combinations.get(6), contains(2, 3));
    }

    @Test
    public void shouldFindNumbersWithSum6() {
        final List<List<Integer>> combinations = Combinations.withSum(6);

        assertThat(combinations, hasSize(11));
        assertThat(combinations.get(0), contains(6));
        assertThat(combinations.get(1), contains(1, 5));
        assertThat(combinations.get(2), contains(1, 1, 4));
        assertThat(combinations.get(3), contains(1, 1, 1, 3));
        assertThat(combinations.get(4), contains(1, 1, 1, 1, 2));
        assertThat(combinations.get(5), contains(1, 1, 1, 1, 1, 1));
        assertThat(combinations.get(6), contains(1, 1, 2, 2));
        assertThat(combinations.get(7), contains(1, 2, 3));
        assertThat(combinations.get(8), contains(2, 4));
        assertThat(combinations.get(9), contains(2, 2, 2));
        assertThat(combinations.get(10), contains(3, 3));
    }

}