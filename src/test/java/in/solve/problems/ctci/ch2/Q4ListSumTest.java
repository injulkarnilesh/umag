package in.solve.problems.ctci.ch2;

import org.junit.Test;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class Q4ListSumTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSumNullFirstNumber() {
        Q4ListSum.sum(null, ListNode.createList(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumNullSecondNumber() {
        Q4ListSum.sum(ListNode.createList(1), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumNullBothNumbers() {
        Q4ListSum.sum(null, null);
    }

    @Test
    public void testSumSingleDigitLists() {
        ListNode<Integer> number1 = ListNode.createList(1);
        ListNode<Integer> number2 = ListNode.createList(3);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(1));
        assertThat(sum.toList(), hasItems(4));
    }

    @Test
    public void testSumTwoDigitLists() {
        ListNode<Integer> number1 = ListNode.createList(1, 5);
        ListNode<Integer> number2 = ListNode.createList(3, 1);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(2));
        assertThat(sum.toList(), hasItems(4, 6));
    }

    @Test
    public void testSumSingleDigitListsWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(8);
        ListNode<Integer> number2 = ListNode.createList(3);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(2));
        assertThat(sum.toList(), hasItems(1, 1));
    }

    @Test
    public void testSumTwoDigitListsWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(8, 9);
        ListNode<Integer> number2 = ListNode.createList(3, 1);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(3));
        assertThat(sum.toList(), hasItems(1, 2, 0));
    }

    @Test
    public void testSumThreeDigitListsWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(3, 8, 4);
        ListNode<Integer> number2 = ListNode.createList(2, 5, 1);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(3));
        assertThat(sum.toList(), hasItems(6, 3, 5));
    }


    @Test
    public void testSumThreeDigitListsWithExtraCarry() {
        ListNode<Integer> number1 = ListNode.createList(3, 8, 4);
        ListNode<Integer> number2 = ListNode.createList(9, 5, 1);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(4));
        assertThat(sum.toList(), hasItems(1, 3, 3, 5));
    }

    @Test
    public void testSumLongerDigitListsWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(3, 8, 4, 7, 8);
        ListNode<Integer> number2 = ListNode.createList(1, 5, 1, 6, 8);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(5));
        assertThat(sum.toList(), hasItems(5, 3, 6, 4, 6));
    }

    @Test
    public void testSumTwoDigitsAndSingleDigitWithoutCarry() {
        ListNode<Integer> number1 = ListNode.createList(7, 8);
        ListNode<Integer> number2 = ListNode.createList(1);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(2));
        assertThat(sum.toList(), hasItems(7, 9));
    }

    @Test
    public void testSumSingleDigitAndTwoDigitWithoutCarry() {
        ListNode<Integer> number1 = ListNode.createList(5);
        ListNode<Integer> number2 = ListNode.createList(1, 3);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(2));
        assertThat(sum.toList(), hasItems(1, 8));
    }

    @Test
    public void testSumSingleDigitAndTwoDigitWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(5);
        ListNode<Integer> number2 = ListNode.createList(1, 9);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(2));
        assertThat(sum.toList(), hasItems(2, 4));
    }

    @Test
    public void testSumDiffLengthNumbersWithCarry() {
        ListNode<Integer> number1 = ListNode.createList(9, 9, 4);
        ListNode<Integer> number2 = ListNode.createList(9, 9);

        ListNode<Integer> sum = Q4ListSum.sum(number1, number2);

        assertThat(sum.toList(), hasSize(4));
        assertThat(sum.toList(), hasItems(1, 0, 9, 3));
    }
}