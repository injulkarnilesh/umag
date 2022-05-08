package in.solve.problems.ctci.ch3;

import in.solve.problems.ctci.ch2.ListNode;

import java.util.Optional;

public class LStack<T> {

    private ListNode<T> head;
    private int size;

    public void push(T item) {
        head = new ListNode<>(item, head);
        size++;
    }

    public T pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = head.getValue();
        head = head.getNext();
        size--;
        return value;
    }

    public T peek() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.getValue();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
