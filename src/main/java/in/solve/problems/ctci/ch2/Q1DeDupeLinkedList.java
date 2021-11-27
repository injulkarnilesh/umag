package in.solve.problems.ctci.ch2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Q1DeDupeLinkedList {

    public static <T> void deDupe(ListNode<T> head) {
        if (head == null) {
            return;
        }
        //deDupeWithMap(head);
        deDupeWithoutBuffer(head);
    }

    private static <T> void deDupeWithoutBuffer(ListNode<T> head) {
        ListNode<T> pointer = head.getNext();
        ListNode<T> previous = head;
        while (pointer != null) {
            ListNode<T> follower = head;
            boolean dupeDeleted = false;
            while (follower != pointer) {
                if (Objects.equals(follower.getValue(), pointer.getValue())) {
                   previous.setNext(pointer.getNext());
                   pointer = pointer.getNext();
                   dupeDeleted = true;
                    break;
                }
                follower = follower.getNext();
            }
            if (!dupeDeleted) {
                previous = pointer;
                pointer = pointer.getNext();
            }
        }

    }

    private static <T> void deDupeWithMap(ListNode<T> head) {
        Map<T, Integer> map = new HashMap<>();
        ListNode<T> pointer = head;
        ListNode<T> previous = null;
        while (pointer != null) {
            if (map.containsKey(pointer.getValue())) {
                previous.setNext(pointer.getNext());
                pointer = pointer.getNext();
            } else {
                map.put(pointer.getValue(), 1);
                previous = pointer;
                pointer = pointer.getNext();
            }
        }
    }

}
