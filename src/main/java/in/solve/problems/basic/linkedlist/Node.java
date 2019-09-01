package in.solve.problems.basic.linkedlist;

public class Node<T> {

    private T value;
    private Node<T> next;

    private Node(final T value) {
        this.value = value;
    }

    public static <N> Node<N> of(final N value) {
        return new Node<>(value);
    }

    public T value() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public Node<T> next() {
        return next;
    }

    public void setNext(final Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[" + value + "]" +
                (next == null ? "" : ("->" + next.toString()));
    }
}
