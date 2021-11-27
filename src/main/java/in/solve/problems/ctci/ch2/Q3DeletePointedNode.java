package in.solve.problems.ctci.ch2;

public class Q3DeletePointedNode {

    public static <T> void delete(ListNode<T> nodeToDelete) {
        if (nodeToDelete == null) {
            throw new IllegalArgumentException("Null node to delete");
        }
        if (nodeToDelete.getNext() == null) {
            throw new IllegalArgumentException("Last Element can not be deleted");
        }
        nodeToDelete.setValue(nodeToDelete.getNext().getValue());
        nodeToDelete.setNext(nodeToDelete.getNext().getNext());
    }
}
