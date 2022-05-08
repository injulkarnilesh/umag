package in.solve.problems.ctci.ch3;

import com.google.common.collect.Lists;

import java.util.List;

public class SetOfStacks<T> {

    private final int stackSize;
    private final List<Stack<T>> stacks = Lists.newArrayList();

    private SetOfStacks(int stackSize) {
        this.stackSize = stackSize;
    }

    public static SetOfStacks<Integer> ofSize(int stackSize) {
        return new SetOfStacks<>(stackSize);
    }

    public void push(T element) {
        if (stacks.isEmpty() || stacks.get(stacks.size() - 1).isFull()) {
            stacks.add(new Stack<>(stackSize));
        }
        stacks.get(stacks.size() - 1).push(element);
    }

    public T pop() {
        if (stacks.isEmpty()) {
            throw new IllegalStateException("Set of Stacks empty");
        }
        Stack<T> topStack = stacks.get(stacks.size() - 1);
        T value = topStack.pop();
        if (topStack.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }
        return value;
    }

    int geNumberOfStacks() {
        return stacks.size();
    }

    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    public T popAt(int stackIndex) {
        if (stackIndex < 0 || stackIndex >= stacks.size()) {
            throw new IllegalArgumentException(stackIndex + " index does not exists");
        }
        T value = stacks.get(stackIndex).pop();
        if (stacks.get(stackIndex).isEmpty()) {
            stacks.remove(stackIndex);
        }
        for (int i = stackIndex; i < stacks.size() - 1; i++) {
            T rolledOverValue = stacks.get(i+1).popFromBottom();
            if (stacks.get(i+1).isEmpty()) {
                stacks.remove(i+1);
            }
            stacks.get(i).push(rolledOverValue);
        }
        return value;
    }

    static class Stack<T> {
        private Node<T> top;
        private Node<T> bottom;
        private final int maxSize;
        private int size = 0;

        public Stack(int maxSize) {
            this.maxSize = maxSize;
        }

        public boolean isFull() {
            return size == maxSize;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(T element) {
            if (isFull()) {
                throw new IllegalStateException("Stack Full");
            }
            size++;
            Node<T> newNode = new Node<>(element);
            if (top == null) {
                top = newNode;
                bottom = newNode;
                return;
            }
            newNode.setDown(top);
            top.setUp(newNode);
            top = newNode;
        }

        public T pop() {
            if (top == null) {
                throw new IllegalStateException("Stack empty");
            }
            size--;
            T value = top.getValue();
            Node<T> newTop = top.getDown();
            if (newTop != null) {
                newTop.setUp(null);
            }
            top = newTop;
            return value;
        }

        public T popFromBottom() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack empty can not pop from bottom");
            }
            T value = bottom.getValue();
            size--;
            bottom = bottom.getUp();
            return value;
        }
    }

    static class Node<T> {
        private final T value;
        private Node<T> up;
        private Node<T> down;

        public Node(T value) {
            this.value = value;
        }

        public void setUp(Node<T> up) {
            this.up = up;
        }

        public void setDown(Node<T> down) {
            this.down = down;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getUp() {
            return up;
        }

        public Node<T> getDown() {
            return down;
        }
    }

}
