package in.solve.problems.ctci.ch8;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import jdk.nashorn.internal.ir.LiteralNode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Recursion {

    public static long findNth(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return findNth(n-1) + findNth(n-2);
    }

    public static List<List<Character>> findPathsForRightAndDownMovements(int n) {
        List<List<Character>> paths = Lists.newArrayList();
        if (n < 2) {
            return paths;
        }
        findPaths(n, paths, Lists.newArrayList(), 0, 0);
        return paths;
    }

    private static void findPaths(int n, List<List<Character>> paths, List<Character> pathSoFar, int currentX, int currentY) {
        if (currentX == n-1 || currentY == n-1) {
            paths.add(pathSoFar);
            return;
        }
        List<Character> pathToMoveDown = Lists.newArrayList(pathSoFar);
        pathToMoveDown.add('D');
        findPaths(n, paths, pathToMoveDown, currentX, currentY+1);
        List<Character> pathToMoveRight = Lists.newArrayList(pathSoFar);
        pathToMoveRight.add('R');
        findPaths(n, paths, pathToMoveRight, currentX + 1, currentY);
    }


    public static <T> Set<Set<T>> findSubsets(Set<T> set) {
        List<T> list = Lists.newArrayList(set);
        Set<Set<T>> subsets = Sets.newHashSet();
        addSubSets(subsets, list, 0, Sets.newHashSet());
        return subsets;
    }

    private static <T> void addSubSets(Set<Set<T>> subsets, List<T> list, int currentIndex, Set<T> currentSubset) {
        if (currentIndex >= list.size()) {
            return;
        }
        Set<T> newSubset = Sets.newHashSet(currentSubset);
        newSubset.add(list.get(currentIndex));
        subsets.add(newSubset);
        for (int i = currentIndex+1; i < list.size(); i++) {
            addSubSets(subsets, list, i, Sets.newHashSet());
            addSubSets(subsets, list, i, newSubset);
        }
    }

    public static Set<String> validParentheses(int n) {
        Set<String> validParentheses = Sets.newHashSet();
        if (n == 0) {
            return validParentheses;
        }
        if (n == 1) {
            return Sets.newHashSet("()");
        }
        Set<String> previousValidParentheses = validParentheses(n - 1);
        for (String previousValidParenthesis : previousValidParentheses) {
            validParentheses.add("(" + previousValidParenthesis + ")");
            validParentheses.add("()" + previousValidParenthesis);
            validParentheses.add(previousValidParenthesis + "()");
        }
        return validParentheses;
    }

    public static Set<List<Integer>> makeChange(int cents) {
        Set<List<Integer>> changeCombinations = Sets.newHashSet();
        makeChange(cents, changeCombinations, Lists.newArrayList());
        return changeCombinations;
    }

    private static void makeChange(int remainingCents, Set<List<Integer>> combinations, List<Integer> currentCombination) {
        if (remainingCents == 0) {
            combinations.add(currentCombination);
        }
        if (remainingCents >= 25) {
            List<Integer> newCombination = Lists.newArrayList(currentCombination);
            newCombination.add(25);
            makeChange(remainingCents - 25, combinations, newCombination);
        }
        if (remainingCents >= 10) {
            List<Integer> newCombination = Lists.newArrayList(currentCombination);
            newCombination.add(10);
            makeChange(remainingCents - 10, combinations, newCombination);
        }
        if (remainingCents >= 5) {
            List<Integer> newCombination = Lists.newArrayList(currentCombination);
            newCombination.add(5);
            makeChange(remainingCents - 5, combinations, newCombination);
        }

        List<Integer> allCents = Stream.generate(() -> 1).limit(remainingCents).collect(Collectors.toList());
        List<Integer> newCombination = Lists.newArrayList(currentCombination);
        newCombination.addAll(allCents);
        combinations.add(newCombination);
    }

    public static List<int[][]> nQueenProblem(int boardSize, int n) {
        List<int[][]> successBoards = Lists.newArrayList();
        int[][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            placeQueenAndCheck(successBoards, board, i);
        }
        return successBoards;
    }

    private static void placeQueenAndCheck(List<int[][]> results, int[][] board, int row) {
        if (row == board.length) {
            results.add(board.clone());
            return;
        }
        for (int column = 0; column < board.length; column++) {
            board[row] = new int[board.length];
            board[row][column] = 'Q';
            if (!checkQueensAttack(board, row, column)) {
                placeQueenAndCheck(results, board, row+1);
            }
        }
    }

    private static boolean checkQueensAttack(int[][] board, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (board[i][column] != 0) {
                return true;
            }
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (row - i == Math.abs(column - j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
