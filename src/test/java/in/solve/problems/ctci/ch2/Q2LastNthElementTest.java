package in.solve.problems.ctci.ch2;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Q2LastNthElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void findLastNthElementInNullList() {
        Q2LastNthElement.findLastNthElement(null, 1);
    }

    @Test
    public void findLastElementInSingleNodeList() {
        ListNode<Integer> head = ListNode.createList(1);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 1);
        assertThat(lastNthElement, is(1));
    }

    @Test
    public void findLastElementInTwoNodeList() {
        ListNode<Integer> head = ListNode.createList(1, 2);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 1);
        assertThat(lastNthElement, is(2));
    }

    @Test
    public void find2ndLastNodeInTwoNodeList() {
        ListNode<Integer> head = ListNode.createList(1, 2);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 2);
        assertThat(lastNthElement, is(1));
    }

    @Test
    public void find2ndLastNodeInThreeNodeList() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 2);
        assertThat(lastNthElement, is(2));
    }

    @Test
    public void find4thLastNodeIn6NodeList() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3, 4, 5, 6);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 4);
        assertThat(lastNthElement, is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void find0thLastElement() {
        ListNode<Integer> head = ListNode.createList(1);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMinus1stLastElement() {
        ListNode<Integer> head = ListNode.createList(1);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNthLastElementWhenNMoreThanLength() {
        ListNode<Integer> head = ListNode.createList(1, 2);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNthLastElementWhenNMoreThanLengthCaseSingleNodeList() {
        ListNode<Integer> head = ListNode.createList(1);
        Integer lastNthElement = Q2LastNthElement.findLastNthElement(head, 3);
    }
}