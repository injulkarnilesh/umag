package in.solve.problems.ctci.ch2;


import java.util.ArrayList;
import java.util.List;

public class ListNode<T> {
    private T value;
    private ListNode next;

    public ListNode(T value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        ListNode<T> pointer = this;
        while (pointer != null) {
            list.add(pointer.getValue());
            pointer = pointer.getNext();
        }
        return list;
    }

    public static <T> ListNode<T> createList(T... items) {
        if (items.length == 0) {
            return null;
        }
        ListNode<T> head = null;
        for (int i = items.length - 1; i >= 0; i--) {
            head = new ListNode<>(items[i], head);
        }
        return head;
    }

}
