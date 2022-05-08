package in.solve.problems.ctci.ch3;

import in.solve.problems.ctci.ch2.ListNode;

public class LQueue<T> {

    private ListNode<T> head;
    private ListNode<T> tail;

    public void add(T item) {
        ListNode<T> newNode = new ListNode<>(item, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        tail.setNext(newNode);
        tail = newNode;
    }

    public T remove() {
        if (head == null) {
            throw new IllegalStateException("Queue empty");
        }
        T value = head.getValue();
        head = head.getNext();
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }


}
