package in.solve.problems.ctci.ch4;

public class BiDirectionalBinarySearchTree extends BinarySearchTree {

    public static BiDirectionalBinarySearchTree createNew() {
        return new BiDirectionalBinarySearchTree();
    }

    @Override
    protected <N extends BinaryTreeNode> N newNodeToAdd(N parent, int item) {
        return (N) new BiDirectionalBinaryTreeNode<Integer>(item, parent);
    }


    public BiDirectionalBinaryTreeNode<Integer> getRoot() {
        return (BiDirectionalBinaryTreeNode<Integer>) super.getRoot();
    }
}
