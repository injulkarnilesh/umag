package in.solve.problems.ctci.ch2;


import org.junit.Test;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class Q3DeletePointedNodeTest {

    @Test(expected = IllegalArgumentException.class)
    public void deleteNull() {
        Q3DeletePointedNode.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteLastNode() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3);
        ListNode<Integer> pointer = head;
        for (int i = 0; i < 3-1; i++) {
            pointer = pointer.getNext();
        }
        Q3DeletePointedNode.delete(pointer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNodeFromSingleItemList() {
        ListNode<Integer> head = ListNode.createList(1);
        ListNode<Integer> pointer = head;
        Q3DeletePointedNode.delete(pointer);
    }

    @Test
    public void deleteNodeFromTwoItemList() {
        ListNode<Integer> head = ListNode.createList(1, 2);
        ListNode<Integer> pointer = head;

        Q3DeletePointedNode.delete(pointer);

        assertThat(head.toList(), hasSize(1));
        assertThat(head.toList(), hasItems(2));
    }

    @Test
    public void deleteNodeFrom3ItemsList() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3);
        ListNode<Integer> pointer = head;
        for (int i = 0; i < 2-1; i++) {
            pointer = pointer.getNext();
        }

        Q3DeletePointedNode.delete(pointer);

        assertThat(head.toList(), hasSize(2));
        assertThat(head.toList(), hasItems(1, 3));
    }

    @Test
    public void deleteFirstNodeFrom3ItemsList() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3);
        ListNode<Integer> pointer = head;

        Q3DeletePointedNode.delete(pointer);

        assertThat(head.toList(), hasSize(2));
        assertThat(head.toList(), hasItems(2, 3));
    }

    @Test
    public void deleteNodeFrom5ItemList() {
        ListNode<Integer> head = ListNode.createList(1, 2, 3, 4, 5);
        ListNode<Integer> pointer = head;
        for (int i = 0; i < 3-1; i++) {
            pointer = pointer.getNext();
        }

        Q3DeletePointedNode.delete(pointer);

        assertThat(head.toList(), hasSize(4));
        assertThat(head.toList(), hasItems(1, 2, 4, 5));
    }
}