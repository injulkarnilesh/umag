package in.solve.problems.ctci.ch2;

public class Q2LastNthElement {

    public static <T> T findLastNthElement(ListNode<T> head, int n) {
        if (head == null) {
            throw new IllegalArgumentException("Null List");
        }
        if (n < 1) {
            throw new IllegalArgumentException("n less than 1");
        }
        ListNode<T> pointer = head;

        int count = 1;
        while(count < n) {
            if (pointer.getNext() == null) {
                throw new IllegalArgumentException("n more than length");
            }
            count++;
            pointer = pointer.getNext();
        }
        ListNode<T> follower = head;
        while (pointer.getNext() != null) {
            follower = follower.getNext();
            pointer = pointer.getNext();
        }
        return follower.getValue();
    }

}
