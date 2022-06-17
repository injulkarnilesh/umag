package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TreeUtilTest {

    @Test
    public void testEmptyTreeIsBalanced() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        assertTrue(TreeUtil.isBalanced(bst));
    }

    @Test
    public void testSingleElementTreeIsBalanced() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(4);
        assertTrue(TreeUtil.isBalanced(bst));
    }

    @Test
    public void test2ElementTreeIsBalanced() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(4);
        bst.add(3);
        assertTrue(TreeUtil.isBalanced(bst));
    }

    @Test
    public void test3ElementLeftAndRightNodeTreeIsBalanced() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(4);
        bst.add(3);
        bst.add(5);
        assertTrue(TreeUtil.isBalanced(bst));
    }

    @Test
    public void test3ElementsWithBothChildrenOnLeftIsNotBalanced() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(4);
        bst.add(3);
        bst.add(2);
        assertFalse(TreeUtil.isBalanced(bst));
    }

    /*
                        10
                     /      \
                   5          20
                /           /     \
              3            15       35
            /             /  \     /   \
           1             12   18  30
     */
    @Test
    public void testTreeNotBalancedAtSomeChildLevel() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(10, 5, 20);
        assertTrue(TreeUtil.isBalanced(bst));

        bst.add(15, 35);
        assertTrue(TreeUtil.isBalanced(bst));

        bst.add(12);
        assertFalse(TreeUtil.isBalanced(bst));

        bst.add(3);
        assertTrue(TreeUtil.isBalanced(bst));

        bst.add(18, 30);
        assertTrue(TreeUtil.isBalanced(bst));

        bst.add(1);
        assertFalse(TreeUtil.isBalanced(bst));
    }
    /************************************************************************************************************/

    @Test
    public void testHeightOfBSTFromSortedArrayOf0Size() {
        Integer[] array = {};
        BinarySearchTree bst = TreeUtil.minHeightBSTFrom(array);
        assertNotNull(bst);
        assertNull(bst.getRoot());
    }

    @Test
    public void testHeightOfBstFromSortedArrayOf1Size() {
        Integer[] array = {1};
        BinarySearchTree bst = TreeUtil.minHeightBSTFrom(array);
        assertNotNull(bst);
        assertThat(bst.getRoot().getValue(), is(1));
        assertThat(TreeUtil.getHeight(bst), is(1));
    }

    @Test
    public void testHeightOfBstFromSortedArrayOf2Size() {
        Integer[] array = {1, 2};
        BinarySearchTree bst = TreeUtil.minHeightBSTFrom(array);
        assertNotNull(bst);
        assertThat(bst.getRoot().getValue(), is(2));
        assertThat(TreeUtil.getHeight(bst), is(2));
    }

    @Test
    public void testHeightOfBstFromSortedArrayOf3Size() {
        Integer[] array = {1, 2, 3};
        BinarySearchTree bst = TreeUtil.minHeightBSTFrom(array);
        assertNotNull(bst);
        assertThat(bst.getRoot().getValue(), is(2));
        assertThat(TreeUtil.getHeight(bst), is(2));
        assertThat(bst.getRoot().getLeft().getValue(), is(1));
        assertThat(bst.getRoot().getRight().getValue(), is(3));
    }

    @Test
    public void testHeightOfBstFromSortedArrayOf10Size() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinarySearchTree bst = TreeUtil.minHeightBSTFrom(array);
        assertNotNull(bst);
        assertThat(bst.getRoot().getValue(), is(6));
        assertThat(TreeUtil.getHeight(bst), is(4));
        assertThat(bst.getRoot().getLeft().getValue(), is(3));
        assertThat(bst.getRoot().getRight().getValue(), is(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotCreatingBSTFromUnSortedArray() {
        Integer[] array = {4, 1, 2};
        TreeUtil.minHeightBSTFrom(array);
    }
    /************************************************************************************************************/
      /*
                        10
                     /      \
                   5          20
                /           /     \
              3            15       35
            /             /  \     /   \
           1             12   18  30
     */

    @Test
    public void testCreatingListsOfSameLevelNodesFromBST() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(10, 5, 3, 1, 20, 15, 35, 12, 18, 30);
        List<List<Integer>> nodeLists = TreeUtil.levelNodes(bst);
        assertThat(nodeLists, hasSize(4));
        assertThat(nodeLists.get(0), is(Lists.newArrayList(10)));
        assertThat(nodeLists.get(1), is(Lists.newArrayList(5, 20)));
        assertThat(nodeLists.get(2), is(Lists.newArrayList(3, 15, 35)));
        assertThat(nodeLists.get(3), is(Lists.newArrayList(1, 12, 18, 30)));
    }

    /************************************************************************************************************/
    /*
                        10
                     /      \
                   5          20
                /           /     \
              3            15       35
            /  \          /  \     /
           1    4        12   18  30
                        /  \
                      11    14
     */

    @Test
    public void testFindingInOrderSuccessorOfANodeInBiDirectionalBST() {
        BiDirectionalBinarySearchTree bst = BiDirectionalBinarySearchTree.createNew();
        bst.add(10, 5, 3, 1, 4, 20, 15, 12, 11, 14, 18, 35, 30);

        BiDirectionalBinaryTreeNode<Integer> successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getLeft());
        assertThat(successor.getValue(), is(10));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getLeft().getLeft());
        assertThat(successor.getValue(), is(4));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getLeft().getLeft().getLeft());
        assertThat(successor.getValue(), is(3));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot());
        assertThat(successor.getValue(), is(11));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getRight().getLeft());
        assertThat(successor.getValue(), is(18));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getRight().getLeft().getRight());
        assertThat(successor.getValue(), is(20));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getRight().getRight());
        assertThat(successor, is(nullValue()));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getRight().getLeft().getLeft().getRight());
        assertThat(successor.getValue(), is(15));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getRight().getRight().getLeft());
        assertThat(successor.getValue(), is(35));
    }

    @Test
    public void testFindingInOrderSuccessorOfANodeInBiDirectionalBSTCaseRootNode() {
        BiDirectionalBinarySearchTree bst = BiDirectionalBinarySearchTree.createNew();
        bst.add(10, 5, 3, 1, 4);

        BiDirectionalBinaryTreeNode<Integer> successor = TreeUtil.findInOrderSuccessor(bst.getRoot());
        assertThat(successor, is(nullValue()));

        successor = TreeUtil.findInOrderSuccessor(bst.getRoot().getLeft());
        assertThat(successor.getValue(), is(10));
    }

    /************************************************************************************************************/
    /*
                        10
                     /      \
                   5          20
                /           /     \
              3            15       35
            /  \          /  \     /
           1    4        12   18  30
                        /  \
                      11    14
     */

    @Test
    public void testFindingCommonAncestor() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(10, 5, 3, 1, 4, 20, 15, 12, 11, 14, 18, 35, 30);

        BinaryTreeNode<Integer> ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getLeft().getLeft().getLeft(), bst.getRoot().getLeft().getLeft().getRight());
        assertThat(ancestor.getValue(), is(3));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getLeft(), bst.getRoot().getRight());
        assertThat(ancestor.getValue(), is(10));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getRight().getLeft().getLeft().getRight(), bst.getRoot().getRight().getRight());
        assertThat(ancestor.getValue(), is(20));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getRight().getLeft().getLeft().getRight(), null);
        assertThat(ancestor, is(nullValue()));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getRight().getLeft().getLeft().getRight(), bst.getRoot().getRight().getLeft().getLeft().getLeft());
        assertThat(ancestor.getValue(), is(12));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getRight().getLeft().getLeft().getRight(), bst.getRoot().getRight().getLeft().getRight());
        assertThat(ancestor.getValue(), is(15));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getRight().getLeft().getRight(), bst.getRoot().getRight().getLeft());
        assertThat(ancestor.getValue(), is(20));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getLeft().getLeft().getRight(), bst.getRoot().getLeft());
        assertThat(ancestor.getValue(), is(10));

        ancestor = TreeUtil.findCommonAncestor(bst, bst.getRoot().getLeft().getLeft().getRight(), bst.getRoot());
        assertThat(ancestor, is(nullValue()));
    }

    /************************************************************************************************************/
    /*
                        10
                     /      \
                   5          20
                /           /     \
              3            15       35
            /  \          /  \     /
           1    4        12   18  30
                        /  \
                      11    14
     */

    @Test
    public void testIfTreeContainsSubTree() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(10, 5, 3, 1, 4, 20, 15, 12, 11, 14, 18, 35, 30);

        BinarySearchTree subBst = BinarySearchTree.createNew();
        subBst.add(15, 12, 11, 14, 18);
        boolean contains = TreeUtil.containsSubTree(bst, subBst);
        assertTrue(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(15, 12, 11, 14, 18, 20);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertFalse(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(35, 30);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertTrue(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(3, 1, 4);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertTrue(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(10, 5, 20, 15, 12, 18);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertTrue(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(10, 20, 35);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertTrue(contains);

        subBst = BinarySearchTree.createNew();
        subBst.add(10, 15, 20);
        contains = TreeUtil.containsSubTree(bst, subBst);
        assertFalse(contains);
    }


    /************************************************************************************************************/
    /*
                            40
                     /              \
                   20                 50
                /     \              /     \
              10       30           45      56
            /  \      /   \        /          \
           5    18   26    36      42           58
          / \       /  \     \                    \
         1   3     22   28   38                    164
     */

    @Test
    public void testFindingPathsInTreeSummingToANumber() {
        BinarySearchTree bst = BinarySearchTree.createNew();
        bst.add(40, 20, 10, 30, 5, 18, 1, 26, 36, 22, 28, 38, 50, 45, 56, 42, 58, 164);

        List<List<BinaryTreeNode<Integer>>> paths = TreeUtil.findPathsWithSum(bst, 90);
        assertThat(paths, hasSize(2));
        List<List<Integer>> numberPaths = getNumberPaths(paths);
        assertThat(numberPaths, hasItem(Lists.newArrayList(40, 50)));
        assertThat(numberPaths, hasItem(Lists.newArrayList(40, 20, 30)));


        paths = TreeUtil.findPathsWithSum(bst, 28);
        assertThat(paths, hasSize(2));
        numberPaths = getNumberPaths(paths);
        assertThat(numberPaths, hasItem(Lists.newArrayList(10, 18)));
        assertThat(numberPaths, hasItem(Lists.newArrayList(28)));

        paths = TreeUtil.findPathsWithSum(bst, 164);
        assertThat(paths, hasSize(3));
        numberPaths = getNumberPaths(paths);
        assertThat(numberPaths, hasItem(Lists.newArrayList(50, 56, 58)));
        assertThat(numberPaths, hasItem(Lists.newArrayList(40, 20, 30, 36, 38)));
        assertThat(numberPaths, hasItem(Lists.newArrayList(164)));

    }

    private List<List<Integer>> getNumberPaths(List<List<BinaryTreeNode<Integer>>> paths) {
        List<List<Integer>> numberPaths = paths.stream().map(nodeList -> nodeList.stream().map(BinaryTreeNode::getValue).collect(Collectors.toList())).collect(Collectors.toList());
        return numberPaths;
    }
}