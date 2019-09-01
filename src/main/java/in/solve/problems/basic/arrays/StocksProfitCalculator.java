package in.solve.problems.basic.arrays;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class StocksProfitCalculator {

    final int [] stockValues;

    private StocksProfitCalculator(final int[] stockValues) {
        this.stockValues = stockValues;
    }

    public static StocksProfitCalculator withValues(final int[] stockValues) {
        return new StocksProfitCalculator(stockValues);
    }

    public int maxProfitWithSingleSell() {
        if (stockValues.length == 0 || stockValues.length == 1) {
            return 0;
        }

        int min = stockValues[0];
        int diff = 0;

        for (int i = 1; i < stockValues.length; i++) {
            if (stockValues[i] - min > diff) {
                diff = stockValues[i] - min;
            }
            if (stockValues[i] < min) {
                min = stockValues[i];
            }
        }

        return diff;
    }

    public List<Pair<Integer, Integer>> maxProfitBuyAndSells() {
        final List<Pair<Integer, Integer>> buySells = new ArrayList<>();

        for (int i = 0; i < stockValues.length;) {
            int buy = i;
            int sell = i;
            while (sell < stockValues.length - 1 && stockValues[sell] < stockValues[sell+1]) {
                sell++;
            }

            if (sell != buy) {
                buySells.add(new Pair<>(buy, sell));
            }

            i = sell+1;
        }

        return buySells;
    }
}
