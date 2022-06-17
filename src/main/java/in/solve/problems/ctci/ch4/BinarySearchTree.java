package in.solve.problems.ctci.ch4;

import java.util.List;

public class BinarySearchTree {

  private BinaryTreeNode<Integer> root = null;

  public static BinarySearchTree createNew() {
    return new BinarySearchTree();
  }

  public void add(int ...items) {
    for (int item : items) {
      root = add(root, null, item);
    }
  }

  public boolean contains(int item) {
    return contains(root, item);
  }

  public void remove(int item) {
    root = remove(root, item);
  }

  private BinaryTreeNode<Integer> remove(BinaryTreeNode<Integer> node, int item) {
    if (node == null) {
      return node;
    }
    if (node.getValue() == item) {
      if (node.getLeft() == null && node.getRight() == null) {
        return null;
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else {
        if (node.getRight().getLeft() == null) {
          node.setValue(node.getRight().getValue());
          node.setRight(node.getRight().getRight());
        } else {
          BinaryTreeNode<Integer> parent = node;
          BinaryTreeNode<Integer> min = node.getRight();
          while (min.getLeft() != null) {
            parent = min;
            min = min.getLeft();
          }
          node.setValue(min.getValue());
          parent.setLeft(null);
        }
        return node;
      }
    } else if (item < node.getValue()) {
      node.setLeft(remove(node.getLeft(), item));
    } else {
      node.setRight(remove(node.getRight(), item));
    }
    return node;
  }

  public boolean contains(BinaryTreeNode<Integer> node, int item) {
    if (node == null) {
      return false;
    }
    if (node.getValue() == item) {
      return true;
    }
    if (item < node.getValue()) {
      return contains(node.getLeft(), item);
    }
    return contains(node.getRight(), item);
  }


  private BinaryTreeNode<Integer> add(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> parent, int item) {
    if (node == null) {
      return newNodeToAdd(parent, item);
    }
    if (item < node.getValue()) {
      node.setLeft(add(node.getLeft(), node, item));
    } else if (item > node.getValue()){
      node.setRight(add(node.getRight(), node, item));
    }
    return node;
  }

  protected <N extends BinaryTreeNode> N newNodeToAdd(N parent, int item) {
    return (N) new BinaryTreeNode(Integer.valueOf(item));
  }

  public BinaryTreeNode<Integer> getRoot() {
    return root;
  }

  public void setRoot(BinaryTreeNode<Integer> root) {
    this.root = root;
  }
}
