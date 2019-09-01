package in.solve.problems.basic.linkedlist;

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
}
