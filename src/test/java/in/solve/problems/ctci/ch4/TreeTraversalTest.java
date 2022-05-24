package in.solve.problems.ctci.ch4;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Test;

public class TreeTraversalTest {

  @Test
  public void testEmptyTreePreOrderTraversal() {
    BinaryTreeNode<Integer> tree = null;
    List<Integer> items = TreeTraversal.preOrderTraverse(tree);
    assertThat(items, is(empty()));
  }


  /*
       1
   */
  @Test
  public void testSingleNodeTreePreOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1);
    List<Integer> items = TreeTraversal.preOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1)));
  }

  /*
       1
      / \
     2   3
   */
  @Test
  public void test3NodeTreePreOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, BinaryTreeNode.of(2), BinaryTreeNode.of(3));

    List<Integer> items = TreeTraversal.preOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1, 2, 3)));
  }

  /*
            1
          /  \
         2    3
        / \   / \
       4   5  6  7
   */
  @Test
  public void test3LevelPreOrderTraversal() {
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, BinaryTreeNode.of(4), BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, BinaryTreeNode.of(6), BinaryTreeNode.of(7));
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.preOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1, 2, 4, 5, 3, 6, 7)));
  }

  /*
            1
          /  \
         2     3
        /     /   \
       4      6     7
         \   / \   /
         5   8  9  10
   */
  @Test
  public void testTreePreOrderTraversalWithMissingNodes() {
    final BinaryTreeNode nodeOf4 = BinaryTreeNode.of(4, null, BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, nodeOf4, null);
    final BinaryTreeNode nodeOf6 = BinaryTreeNode.of(6, BinaryTreeNode.of(8), BinaryTreeNode.of(9));
    final BinaryTreeNode nodeOf7 = BinaryTreeNode.of(7, BinaryTreeNode.of(10), null);
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, nodeOf6, nodeOf7);
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.preOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1, 2, 4, 5, 3, 6, 8, 9, 7, 10)));
  }


  @Test
  public void testEmptyTreeInOrderTraversal() {
    BinaryTreeNode<Integer> tree = null;
    List<Integer> items = TreeTraversal.inOrderTraverse(tree);
    assertThat(items, is(empty()));
  }


  /*
       1
   */
  @Test
  public void testSingleNodeTreeInOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1);
    List<Integer> items = TreeTraversal.inOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1)));
  }

  /*
       1
      / \
     2   3
   */
  @Test
  public void test3NodeTreeInOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, BinaryTreeNode.of(2), BinaryTreeNode.of(3));

    List<Integer> items = TreeTraversal.inOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(2, 1, 3)));
  }

  /*
            1
          /  \
         2    3
        / \   / \
       4   5  6  7
   */
  @Test
  public void test3LevelInOrderTraverseTraversal() {
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, BinaryTreeNode.of(4), BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, BinaryTreeNode.of(6), BinaryTreeNode.of(7));
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.inOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(4, 2, 5, 1, 6, 3, 7)));
  }

  /*
            1
          /  \
         2     3
        /     /   \
       4      6     7
         \   / \   /
         5   8  9  10
   */
  @Test
  public void testTreeInOrderTraversalWithMissingNodes() {
    final BinaryTreeNode nodeOf4 = BinaryTreeNode.of(4, null, BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, nodeOf4, null);
    final BinaryTreeNode nodeOf6 = BinaryTreeNode.of(6, BinaryTreeNode.of(8), BinaryTreeNode.of(9));
    final BinaryTreeNode nodeOf7 = BinaryTreeNode.of(7, BinaryTreeNode.of(10), null);
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, nodeOf6, nodeOf7);
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.inOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(4, 5, 2, 1, 8, 6, 9, 3, 10, 7)));
  }

  @Test
  public void testEmptyTreePostOrderTraversal() {
    BinaryTreeNode<Integer> tree = null;
    List<Integer> items = TreeTraversal.postOrderTraverse(tree);
    assertThat(items, is(empty()));
  }


  /*
       1
   */
  @Test
  public void testSingleNodeTreePostOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1);
    List<Integer> items = TreeTraversal.postOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(1)));
  }

  /*
       1
      / \
     2   3
   */
  @Test
  public void test3NodeTreePostOrderTraversal() {
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, BinaryTreeNode.of(2), BinaryTreeNode.of(3));

    List<Integer> items = TreeTraversal.postOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(2, 3, 1)));
  }

  /*
            1
          /  \
         2    3
        / \   / \
       4   5  6  7
   */
  @Test
  public void test3LevelPostOrderTraverseTraversal() {
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, BinaryTreeNode.of(4), BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, BinaryTreeNode.of(6), BinaryTreeNode.of(7));
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.postOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(4, 5, 2, 6, 7, 3, 1)));
  }

  /*
            1
          /  \
         2     3
        /     /   \
       4      6     7
         \   / \   /
         5   8  9  10
   */
  @Test
  public void testTreePostOrderTraversalWithMissingNodes() {
    final BinaryTreeNode nodeOf4 = BinaryTreeNode.of(4, null, BinaryTreeNode.of(5));
    final BinaryTreeNode nodeOf2 = BinaryTreeNode.of(2, nodeOf4, null);
    final BinaryTreeNode nodeOf6 = BinaryTreeNode.of(6, BinaryTreeNode.of(8), BinaryTreeNode.of(9));
    final BinaryTreeNode nodeOf7 = BinaryTreeNode.of(7, BinaryTreeNode.of(10), null);
    final BinaryTreeNode nodeOf3 = BinaryTreeNode.of(3, nodeOf6, nodeOf7);
    BinaryTreeNode<Integer> tree = BinaryTreeNode.of(1, nodeOf2, nodeOf3);

    List<Integer> items = TreeTraversal.postOrderTraverse(tree);
    assertThat(items, is(Lists.newArrayList(5, 4, 2, 8, 9, 6, 10, 7, 3, 1)));
  }


}
