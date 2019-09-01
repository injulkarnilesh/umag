package in.solve.problems.basic.arrays;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StocksProfitCalculatorTest {

    @Test
    public void shouldHave0ProfitForNoData() {
        assertThat(StocksProfitCalculator.withValues(new int[0])
                    .maxProfitWithSingleSell(),
                is(0));
    }

    @Test
    public void shouldHave0ProfitForSingleDayData() {
        assertThat(StocksProfitCalculator.withValues(new int[] {10})
                    .maxProfitWithSingleSell(),
                is(0));
    }

    @Test
    public void shouldHave0ProfitForStockValuesGoingDown() {
        assertThat(StocksProfitCalculator.withValues(new int[] {10, 5})
                    .maxProfitWithSingleSell(),
                is(0));
    }

    @Test
    public void shouldHaveProfitForStockValueGoingUpForTwoDays() {
        assertThat(StocksProfitCalculator.withValues(new int[] {10, 51})
                .maxProfitWithSingleSell(),
                is(41));
    }

    @Test
    public void shouldHaveProfitForStockValuesGoingUpForMultipleDays() {
        assertThat(StocksProfitCalculator.withValues(new int[] {10, 13, 15})
                        .maxProfitWithSingleSell(),
                is(5));
    }

    @Test
    public void testSample() {
        assertThat(StocksProfitCalculator.withValues(new int[] {2, 3, 10, 6, 4, 8, 1})
                        .maxProfitWithSingleSell(),
                is(8));
    }

    @Test
    public void testSample2() {
        assertThat(StocksProfitCalculator.withValues(new int[] {2, 3, 10, 6, 4, 8, 1, 12})
                        .maxProfitWithSingleSell(),
                is(11));
    }

    @Test
    public void testSample3() {
        assertThat(StocksProfitCalculator.withValues(new int[] {100, 180, 260, 310, 40, 535, 695})
                        .maxProfitWithSingleSell(),
                is(655));
    }

    @Test
    public void shouldHaveNoProfitWithMultipleSellsWhenNoDataProvided() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[0])
                .maxProfitBuyAndSells();
        assertThat(buyAndSells, is(empty()));
    }

    @Test
    public void shouldHaveNoProfitWithMultipleSellsWhenValuesAreDecreasing() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {4, 2, 1})
                .maxProfitBuyAndSells();
        assertThat(buyAndSells, is(empty()));
    }

    @Test
    public void shouldHaveProfitWithSingleSellWhenValuesAreIncreasing() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {4, 12, 14})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(0, 2));
        assertThat(buyAndSells, is(expectedButSells));
    }

    @Test
    public void shouldHaveProfitWithMultipleSellsWhenValuesAreIncreasingAndDecreasing() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {4, 5, 7, 6, 7, 9, 12, 1, 12})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(0, 2));
        expectedButSells.add(new Pair<>(3, 6));
        expectedButSells.add(new Pair<>(7, 8));
        assertThat(buyAndSells, is(expectedButSells));
    }

    @Test
    public void shouldHaveProfitWithMultipleSellsWhenValuesNotChangingForFewDays() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {4, 5, 7, 7, 7, 9, 12})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(0, 2));
        expectedButSells.add(new Pair<>(4, 6));
        assertThat(buyAndSells, is(expectedButSells));
    }

    @Test
    public void shouldHaveProfitWithMultipleSellsWhenValuesDecreasingTowardsEnd() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {1, 3, 2, 7, 9, 12, 10, 5, 2})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(0, 1));
        expectedButSells.add(new Pair<>(2, 5));
        assertThat(buyAndSells, is(expectedButSells));
    }

    @Test
    public void shouldHaveProfitWithMultipleSellsWhenValuesDecreasingAtTheBegining() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {10, 7, 2, 7, 9, 12, 10, 15})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(2, 5));
        expectedButSells.add(new Pair<>(6, 7));
        assertThat(buyAndSells, is(expectedButSells));
    }

    @Test
    public void shouldHaveProfitWithMultipleSellsWithTestData() {
        final List<Pair<Integer, Integer>> buyAndSells = StocksProfitCalculator.withValues(new int[] {100, 180, 260, 310, 40, 535, 695})
                .maxProfitBuyAndSells();
        final List<Pair<Integer, Integer>> expectedButSells = new ArrayList<>();
        expectedButSells.add(new Pair<>(0, 3));
        expectedButSells.add(new Pair<>(4, 6));
        assertThat(buyAndSells, is(expectedButSells));
    }
}
