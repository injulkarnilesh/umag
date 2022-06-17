package in.solve.problems.ctci.ch4;

public class BiDirectionalBinaryTreeNode<T> extends BinaryTreeNode<T> {

    private BinaryTreeNode<T> parent;

    public BiDirectionalBinaryTreeNode(T value, BinaryTreeNode<T> parent) {
        super(value);
        this.parent = parent;
    }

    public BiDirectionalBinaryTreeNode<T> getParent() {
        return (BiDirectionalBinaryTreeNode<T>) parent;
    }

    public BiDirectionalBinaryTreeNode<T> getLeft() {
        return (BiDirectionalBinaryTreeNode<T>) super.getLeft();
    }

    public BiDirectionalBinaryTreeNode<T> getRight() {
        return (BiDirectionalBinaryTreeNode<T>) super.getRight();
    }

}
