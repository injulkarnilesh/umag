package in.solve.problems.ctci.ch2;


import org.junit.Test;

import static in.solve.problems.ctci.ch2.Q1DeDupeLinkedList.deDupe;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class Q1DeDupeLinkedListTest {

    @Test
    public void testNullDeDupe() {
        deDupe(null);
    }

    @Test
    public void deDupeSingleNodeList() {
        ListNode<Integer> head = new ListNode<>(1, null);
        deDupe(head);
        assertThat(head.toList(), hasSize(1));
    }

    @Test
    public void deDupeMultiNodeListWhenDuplicatesFound() {
        ListNode<Integer> head = ListNode.createList(1, 2, 4, 2, 3, 4, 1);
        deDupe(head);
        assertThat(head.toList(), hasSize(4));
        assertThat(head.toList(), hasItems(1, 2, 4, 3));
    }

    @Test
    public void deDupeMultiNodeListWhenAllDuplicatesFound() {
        ListNode<Integer> head = ListNode.createList(1, 1, 1, 1, 1, 1, 1);
        deDupe(head);
        assertThat(head.toList(), hasSize(1));
        assertThat(head.toList(), hasItems(1));
    }

    @Test
    public void deDupeMultiNodeListWhenDuplicatesFoundAtCorners() {
        ListNode<Integer> head = ListNode.createList(1, 1, 2, 2, 3, 4, 4);
        deDupe(head);
        assertThat(head.toList(), hasSize(4));
        assertThat(head.toList(), hasItems(1, 2, 3, 4));
    }

    @Test
    public void deDupeMultiNodeListWhenNoDuplicatesFound() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3, 4);
        deDupe(head);
        assertThat(head.toList(), hasSize(4));
        assertThat(head.toList(), hasItems(1, 2, 3, 4));
    }

}