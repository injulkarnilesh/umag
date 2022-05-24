package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class TreeTraversal {

  public static <T> List<T> preOrderTraverse(BinaryTreeNode<T> tree) {
    final List<T> items = Lists.newArrayList();
    preOrderTraverse(tree, items);
    return items;
  }

  private static  <T> void preOrderTraverse(BinaryTreeNode<T> tree, List<T> items) {
    if (tree == null) {
      return;
    }
    items.add(tree.getValue());
    preOrderTraverse(tree.getLeft(), items);
    preOrderTraverse(tree.getRight(), items);
  }

  public static <T> List<T> inOrderTraverse(BinaryTreeNode<T> tree) {
    final List<T> items = Lists.newArrayList();
    inOrderTraverse(tree, items);
    return items;
  }

  private static <T> void inOrderTraverse(BinaryTreeNode<T> tree, List<T> items) {
    if (tree == null) {
      return;
    }
    inOrderTraverse(tree.getLeft(), items);
    items.add(tree.getValue());
    inOrderTraverse(tree.getRight(), items);
  }

  public static <T> List<T> postOrderTraverse(BinaryTreeNode<T> tree) {
    final List<T> items = Lists.newArrayList();
    postOrderTraverse(tree, items);
    return items;
  }

  private static <T> void postOrderTraverse(BinaryTreeNode<T> tree, List<T> items) {
    if (tree == null) {
      return;
    }
    postOrderTraverse(tree.getLeft(), items);
    postOrderTraverse(tree.getRight(), items);
    items.add(tree.getValue());
  }

}
