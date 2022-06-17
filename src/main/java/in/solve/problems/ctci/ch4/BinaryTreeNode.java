package in.solve.problems.ctci.ch4;

public class BinaryTreeNode<T> {
  private T value;
  private BinaryTreeNode<T> left;
  private BinaryTreeNode<T> right;

  protected BinaryTreeNode(T value) {
    this(value, null, null);
  }

  private BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static <T> BinaryTreeNode of(T value) {
    return new BinaryTreeNode(value);
  }

  public static <T> BinaryTreeNode of(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
    return new BinaryTreeNode(value, left, right);
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public BinaryTreeNode<T> getLeft() {
    return left;
  }

  public void setLeft(BinaryTreeNode<T> left) {
    this.left = left;
  }

  public BinaryTreeNode<T> getRight() {
    return right;
  }

  public void setRight(BinaryTreeNode<T> right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "BinaryTreeNode{" +
            "value=" + value +
            '}';
  }
}
