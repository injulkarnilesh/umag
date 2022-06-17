package in.solve.problems.ctci.ch4;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeUtil {

    public static boolean isBalanced(BinarySearchTree bst) {
        if (bst == null) {
            throw new IllegalArgumentException("Null BST");
        }
        BinaryTreeNode<Integer> root = bst.getRoot();
        if (root == null) {
            return true;
        }
        try {
            checkHeightDifferences(root);
        } catch (IllegalStateException stateException) {
            stateException.printStackTrace();
            return false;
        }
        return true;
    }


    public static BinarySearchTree minHeightBSTFrom(Integer[] sortedArray) {
        BinarySearchTree bst = BinarySearchTree.createNew();
        if (sortedArray == null || sortedArray.length == 0) {
            return bst;
        }
        verifySorted(sortedArray);
        bst.setRoot(addArrayToBST(sortedArray, 0, sortedArray.length));
        return bst;
    }

    private static void verifySorted(Integer[] sortedArray) {
        for (int i = 0; i < sortedArray.length - 1; i++) {
            if (sortedArray[i] >= sortedArray[i+1]) {
                throw new IllegalArgumentException("Not sorted array:" + sortedArray);
            }
        }
    }

    private static BinaryTreeNode<Integer> addArrayToBST(Integer[] sortedArray, int from, int to) {
        if (from >= to) {
            return null;
        }
        int midIndex = (from + to)/2;
        BinaryTreeNode parent = BinaryTreeNode.of(sortedArray[midIndex]);
        parent.setLeft(addArrayToBST(sortedArray, from, midIndex));
        parent.setRight(addArrayToBST(sortedArray, midIndex + 1, to));
        return parent;
    }


    private static int checkHeightDifferences(BinaryTreeNode<Integer> node) {
        if(node == null) {
            return 0;
        }
        int leftHeight = checkHeightDifferences(node.getLeft());
        int rightHeight = checkHeightDifferences(node.getRight());
        if (Math.abs(leftHeight - rightHeight) > 1) {
            throw new IllegalStateException("BST is not balanced at " + node);
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int getHeight(BinarySearchTree bst) {
        return getHeight(bst.getRoot());
    }

    private static int getHeight(BinaryTreeNode<Integer> node) {
        if(node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static List<List<Integer>> levelNodes(BinarySearchTree bst) {
        List<List<Integer>> result = Lists.newArrayList();
        if (bst == null || bst.getRoot() == null) {
            return result;
        }
        collectLevelNodes(bst.getRoot(), result, 0);
        return result;
    }

    private static void collectLevelNodes(BinaryTreeNode<Integer> node, List<List<Integer>> result, int resultIndex) {
        if (node == null) {
            return;
        }
        if (result.size() <= resultIndex) {
            result.add(Lists.newArrayList());
        }
        result.get(resultIndex).add(node.getValue());
        int nextIndex = resultIndex + 1;
        collectLevelNodes(node.getLeft(), result, nextIndex);
        collectLevelNodes(node.getRight(), result, nextIndex);
    }

    public static BiDirectionalBinaryTreeNode<Integer> findInOrderSuccessor(BiDirectionalBinaryTreeNode<Integer> node) {
        if (node.getRight() != null) {
            BiDirectionalBinaryTreeNode<Integer> successor = node.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }
            return successor;
        } else {
            if (node.getParent() != null) {
                return findNextSmallerParent(node, node.getParent());
            }
        }
        return null;
    }

    private static BiDirectionalBinaryTreeNode<Integer> findNextSmallerParent(BiDirectionalBinaryTreeNode<Integer> node, BiDirectionalBinaryTreeNode<Integer> parent) {
        if (parent == null) {
            return null;
        }
        if (parent.getLeft() == node) {
            return parent;
        }
        return findNextSmallerParent(parent, parent.getParent());
    }

    public static BinaryTreeNode<Integer> findCommonAncestor(BinarySearchTree bst, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        return findCommonAncestor(bst.getRoot(), null, p, q);
    }

    private static BinaryTreeNode<Integer> findCommonAncestor(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> parent, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode<Integer> commonAncestor = findCommonAncestor(node.getLeft(), node, p, q);
        if (commonAncestor == null) {
            commonAncestor = findCommonAncestor(node.getRight(), node, p, q);
        } if (commonAncestor == null) {
            if (node == p) {
                if (containsChild(node.getLeft(), q) || containsChild(node.getRight(), q)) {
                    return parent;
                }
            }
            if (node == q) {
                if (containsChild(node.getLeft(), p) || containsChild(node.getRight(), p)) {
                    return parent;
                }
            }
            if (containsChild(node.getLeft(), p) && containsChild(node.getRight(), q)) {
                return node;
            } else if (containsChild(node.getRight(), p) && containsChild(node.getLeft(), q)) {
                return node;
            }
        }
        return commonAncestor;
    }

    private static boolean containsChild(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> child) {
        if (node == null) {
            return false;
        }
        if (node == child) {
            return true;
        }
        else {
            return containsChild(node.getLeft(), child) || containsChild(node.getRight(), child);
        }
    }

    public static boolean containsSubTree(BinarySearchTree tree, BinarySearchTree subTree) {
        return checkAllNodes(tree.getRoot(), subTree.getRoot());
    }

    public static boolean checkAllNodes(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> subNode) {
        if (node == null) {
            return false;
        }
        if (hasSameSubTreeAt(node, subNode)) {
            return true;
        }
        return checkAllNodes(node.getLeft(), subNode) || checkAllNodes(node.getRight(), subNode);
    }

    public static boolean hasSameSubTreeAt(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> subNode) {
        if (subNode != null && node == null) {
            return false;
        }
        if (subNode == null) {
            return true;
        }
        if (Objects.equals(node.getValue(), subNode.getValue())) {
            return hasSameSubTreeAt(node.getLeft(), subNode.getLeft()) && hasSameSubTreeAt(node.getRight(), subNode.getRight());
        }
        return false;
    }

    public static List<List<BinaryTreeNode<Integer>>> findPathsWithSum(BinarySearchTree tree, int sum) {
        List<List<BinaryTreeNode<Integer>>> result = Lists.newArrayList();
        starSumCheckFrom(result, tree.getRoot(), sum);
        return result;
    }

    private static void starSumCheckFrom(List<List<BinaryTreeNode<Integer>>> result,
                                         BinaryTreeNode<Integer> startingNode, int sum) {
        if (startingNode == null) {
            return;
        }
        checkSumOnPaths(result, Lists.newArrayList(), startingNode, sum);
        starSumCheckFrom(result, startingNode.getLeft(), sum);
        starSumCheckFrom(result, startingNode.getRight(), sum);
    }

    private static void checkSumOnPaths(List<List<BinaryTreeNode<Integer>>> result,
                                        List<BinaryTreeNode<Integer>> currentPath, BinaryTreeNode<Integer> currentNode,
                                        int remainingSum) {
        if (currentNode == null || remainingSum < currentNode.getValue()) {
            return;
        }
        currentPath.add(currentNode);
        if (remainingSum == currentNode.getValue()) {
            result.add(currentPath);
            return;
        }
        int newRemainingSum = remainingSum - currentNode.getValue();
        checkSumOnPaths(result, Lists.newArrayList(currentPath), currentNode.getLeft(), newRemainingSum);
        checkSumOnPaths(result, Lists.newArrayList(currentPath), currentNode.getRight(), newRemainingSum);
    }

}
