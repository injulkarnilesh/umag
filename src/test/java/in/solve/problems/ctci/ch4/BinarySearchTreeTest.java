package in.solve.problems.ctci.ch4;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Test;

public class BinarySearchTreeTest {

  @Test
  public void testAddingOneNode() {
    final BinarySearchTree bst = BinarySearchTree.createNew();

    bst.add(2);
    final BinaryTreeNode<Integer> root = bst.getRoot();

    assertNotNull(root);
    final List<Integer> ordered = TreeTraversal.inOrderTraverse(root);
    assertThat(ordered, is(Lists.newArrayList(2)));
  }

  @Test
  public void testAddingThreeNode() {
    final BinarySearchTree bst = BinarySearchTree.createNew();

    bst.add(2);
    bst.add(1);
    bst.add(3);
    final BinaryTreeNode<Integer> root = bst.getRoot();

    assertThat(root.getValue(), is(2));
    final List<Integer> ordered = TreeTraversal.inOrderTraverse(root);
    assertThat(ordered, is(Lists.newArrayList(1, 2, 3)));
  }

  @Test
  public void testAdding5Node() {
    final BinarySearchTree bst = BinarySearchTree.createNew();

    bst.add(2, 1, 5, 3, 4);
    final BinaryTreeNode<Integer> root = bst.getRoot();

    assertThat(root.getValue(), is(2));
    final List<Integer> ordered = TreeTraversal.inOrderTraverse(root);
    assertThat(ordered, is(Lists.newArrayList(1, 2, 3, 4, 5)));
  }

  @Test
  public void testAddingDuplicateNode() {
    final BinarySearchTree bst = BinarySearchTree.createNew();

    bst.add(2, 1, 5, 3, 4, 2, 5, 1, 3);
    final BinaryTreeNode<Integer> root = bst.getRoot();

    assertThat(root.getValue(), is(2));
    final List<Integer> ordered = TreeTraversal.inOrderTraverse(root);
    assertThat(ordered, is(Lists.newArrayList(1, 1, 2, 2, 3, 3, 4, 5, 5)));
  }

  @Test
  public void testContains() {
    final BinarySearchTree bst = BinarySearchTree.createNew();

    assertFalse(bst.contains(1));

    bst.add(5, 2, 4, 8, 9, 1, 3, 6, 7, 4);

    assertTrue(bst.contains(5));
    assertTrue(bst.contains(4));
    assertTrue(bst.contains(3));
    assertTrue(bst.contains(9));

    assertFalse(bst.contains(0));
    assertFalse(bst.contains(10));
    assertFalse(bst.contains(-8));

    bst.add(0);
    bst.add(10);
    bst.add(-8);

    assertTrue(bst.contains(0));
    assertTrue(bst.contains(10));
    assertTrue(bst.contains(-8));
  }

  @Test
  public void testDeletingFromNullTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.remove(4);
    assertNull(bst.getRoot());
  }

  @Test
  public void testDeletingNonExistingItemFromSingleNodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(5);
    bst.remove(4);
    assertThat(bst.getRoot().getValue(), is(5));
  }

  @Test
  public void testDeletingExistingItemFromSingleNodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(5);
    bst.remove(5);
    assertNull(bst.getRoot());
  }

  /*
       2
      / \
     1   3
   */
  @Test
  public void testDeletingNonExistingItemFrom3NodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(2, 1, 3);
    bst.remove(4);

    final List<Integer> elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(1, 2, 3)));
  }

  /*
       2          2
      / \  =>    /
     1   3      1
   */
  @Test
  public void testDeletingRightItemFrom3NodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(2, 1, 3);
    bst.remove(3);

    final List<Integer> elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(1, 2)));
  }

  /*
       2          2
      / \  =>       \
     1   3           3
   */
  @Test
  public void testDeletingLeftItemFrom3NodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(2, 1, 3);
    bst.remove(1);

    final List<Integer> elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(2, 3)));
    assertThat(bst.getRoot().getValue(), is(2));
  }

  /*
       2          3
      / \  =>    /
     1   3      1
   */
  @Test
  public void testDeletingRootItemFrom3NodeTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(2, 1, 3);
    bst.remove(2);

    final List<Integer> elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(1, 3)));
    assertThat(bst.getRoot().getValue(), is(3));
    assertThat(bst.getRoot().getLeft().getValue(), is(1));
    assertNull(bst.getRoot().getRight());
  }

  /*
               10
           /       \
          5         20
        /    \      /  \
        3     8    12    30
       / \   / \
      1   4  7  9
   */
  @Test
  public void testDeletingNodesFromBiggerTree() {
    final BinarySearchTree bst = BinarySearchTree.createNew();
    bst.add(10, 5, 8, 3, 4, 20, 1, 9, 7, 12, 30);

    bst.remove(5);
    List<Integer> elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(1, 3, 4, 7, 8, 9, 10, 12, 20, 30)));
    assertThat(bst.getRoot().getLeft().getValue(), is(7));

    /*
               10
           /       \
          7         20
        /    \      /  \
        3     8    12    30
       / \     \
      1   4     9
   */
    bst.remove(8);
    elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(1, 3, 4, 7, 9, 10, 12, 20, 30)));
    assertThat(bst.getRoot().getLeft().getRight().getValue(), is(9));

    /*
               10
           /       \
          7         20
        /    \      /  \
        3     9    12    30
       / \
      1   4
   */
    bst.remove(1);
    elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(3, 4, 7, 9, 10, 12, 20, 30)));
    assertThat(bst.getRoot().getLeft().getLeft().getLeft(), is(nullValue()));

    /*
               10
           /       \
          7         20
        /    \      /  \
        3     9    12    30
         \
          4
   */
    bst.remove(7);
    elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(3, 4, 9, 10, 12, 20, 30)));
    assertThat(bst.getRoot().getLeft().getValue(), is(9));


    /*
               10
           /       \
          9         20
        /           /  \
        3          12    30
         \
          4
   */
    bst.remove(33);
    elements = TreeTraversal.inOrderTraverse(bst.getRoot());
    assertThat(elements, is(Lists.newArrayList(3, 4, 9, 10, 12, 20, 30)));
  }

  private boolean isSorted(List<Integer> preOrdered) {
    for (int i = 0; i < preOrdered.size() - 1; i++) {
      if (preOrdered.get(i) >= preOrdered.get(i+1)) {
        return false;
      }
    }
    return true;
  }
}
