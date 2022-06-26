package in.solve.problems.ctci.ch8;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RecursionTest {

    @Test
    public void testFindNthFibonacciNumber() {
        assertThat(Recursion.findNth(0), is(0l));
        assertThat(Recursion.findNth(1), is(1l));
        assertThat(Recursion.findNth(2), is(1l));
        assertThat(Recursion.findNth(3), is(2l));
        assertThat(Recursion.findNth(4), is(3l));
        assertThat(Recursion.findNth(5), is(5l));
        assertThat(Recursion.findNth(6), is(8l));
        assertThat(Recursion.findNth(7), is(13l));
        assertThat(Recursion.findNth(8), is(21l));
        assertThat(Recursion.findNth(42), is(267914296l));
    }

    @Test
    public void testFindPathsInGridForRightAndDownMovementsCombinations() {
        assertThat(Recursion.findPathsForRightAndDownMovements(1), hasSize(0));
        assertThat(Recursion.findPathsForRightAndDownMovements(2), hasSize(2));
        assertThat(Recursion.findPathsForRightAndDownMovements(3), hasSize(6));
        assertThat(Recursion.findPathsForRightAndDownMovements(10), hasSize(48620));
    }

    @Test
    public void testFindAllSubsetsOfSet() {
        Set<Set<Integer>> subsets = Recursion.findSubsets(Sets.newHashSet(1));
        assertThat(subsets, containsInAnyOrder(Sets.newHashSet(1)));

        subsets = Recursion.findSubsets(Sets.newHashSet(1, 2));
        assertThat(subsets, containsInAnyOrder(Sets.newHashSet(1), Sets.newHashSet(2), Sets.newHashSet(1, 2)));

        subsets = Recursion.findSubsets(Sets.newHashSet(1, 2, 3));
        assertThat(subsets, containsInAnyOrder(Sets.newHashSet(1), Sets.newHashSet(2), Sets.newHashSet(3),
                Sets.newHashSet(1, 2), Sets.newHashSet(2, 3), Sets.newHashSet(1, 3), Sets.newHashSet(1, 2, 3)));

        subsets = Recursion.findSubsets(Sets.newHashSet(1, 2, 3, 4));
        assertThat(subsets, hasSize(15));
    }

    @Test
    public void testFindAllValidParenthesesCombinations() {
        assertThat(Recursion.validParentheses(1), containsInAnyOrder("()"));
        assertThat(Recursion.validParentheses(2), containsInAnyOrder("()()", "(())"));
        assertThat(Recursion.validParentheses(3), containsInAnyOrder(
                "()()()",  "(()())", "((()))", "(())()", "()(())"
        ));
        assertThat(Recursion.validParentheses(4), containsInAnyOrder(
                "()()()()", "(()()())",
                "(()())()", "()(()())", "((()()))",
                "((()))()", "()((()))", "(((())))",
                "(())()()", "()(())()", "((())())",
                "()()(())", "(()(()))"
        ));
    }

    @Test
    public void testMakingVariousCombinationsOfChangeForNCents() {
        assertThat(Recursion.makeChange(1), containsInAnyOrder(Lists.newArrayList(1)));
        assertThat(Recursion.makeChange(2), containsInAnyOrder(Lists.newArrayList(1, 1)));
        assertThat(Recursion.makeChange(5), containsInAnyOrder(
                Lists.newArrayList(5), Lists.newArrayList(1, 1 ,1, 1, 1)
        ));
        assertThat(Recursion.makeChange(10), containsInAnyOrder(
                Lists.newArrayList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                Lists.newArrayList(5, 5),
                Lists.newArrayList(5, 1, 1, 1, 1, 1),
                Lists.newArrayList(10)
        ));
        Set<List<Integer>> allChangeSets = Recursion.makeChange(100);
        for (List<Integer> changeSet : allChangeSets) {
            Integer sum = changeSet.stream().reduce(0, Integer::sum);
            assertThat(sum, is(100));
        }
    }

    @Test
    public void test8QueenProblem() {
        List<int[][]> boards = Recursion.nQueenProblem(8, 8);
        assertThat(boards, hasSize(105));
        boards.stream().limit(10).forEach(this::print);
    }

    private void print(int [][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] == 'Q'? "Q": " ");
                System.out.print(" |");
            }
            System.out.println("\n");
        }
        System.out.println("###########################");
    }

}