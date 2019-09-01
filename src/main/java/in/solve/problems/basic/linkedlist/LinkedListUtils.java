package in.solve.problems.basic.linkedlist;

import java.util.HashMap;

public class LinkedListUtils {

    public static <T> Node<T> reverse(final Node<T> list) {
        Node<T> pointer = list;
        Node lastNode = null;
        while (pointer != null) {
            final Node newNode = Node.of(pointer.value());
            newNode.setNext(lastNode);
            lastNode = newNode;
            pointer = pointer.next();
        }
        return lastNode;
    }

    public static <T> void deDupe(final Node<T> list) {
        final HashMap<T, Boolean> values = new HashMap<>();
        Node<T> pointer = list;
        Node<T> followerPointer = null;
        while (pointer != null) {
            if (values.containsKey(pointer.value())) {
                followerPointer.setNext(pointer.next());
            } else {
                followerPointer = pointer;
                values.put(pointer.value(), Boolean.TRUE);
            }

            pointer = pointer.next();
        }

    }
}
