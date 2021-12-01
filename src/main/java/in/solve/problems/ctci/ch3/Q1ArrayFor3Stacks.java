package in.solve.problems.ctci.ch3;

import java.lang.reflect.Array;

public class Q1ArrayFor3Stacks<T> {

    private final StackNode<T>[] array;
    private int index = 0;
    private int totalSize;
    private final int[] tops = {-1, -1, -1};

    public Q1ArrayFor3Stacks(int totalSize) {
        if (totalSize < 1) {
            throw new IllegalArgumentException("Invalid array size " + totalSize);
        }
        this.totalSize = totalSize;
        this.array = (StackNode<T>[]) Array.newInstance(StackNode.class, totalSize);
    }

    public void push(int stackIndex, T item) {
        validateStackIndex(stackIndex);
        if (index == totalSize) {
            for (int i = 0; i < tops.length; i++) {
                if (tops[i] != -1) {
                    int nextIndex = array[tops[i]].getNextIndex();
                    if (nextIndex != -1) {
                        array[nextIndex].setValue(item);
                        int currentTop = array[tops[stackIndex]].getPreviousIndex();
                        array[nextIndex].setPreviousIndex(currentTop);
                        array[currentTop].setNextIndex(nextIndex);
                        tops[stackIndex] = nextIndex;
                        array[tops[i]].setNextIndex(-1);
                        return;
                    }
                }
            }
            throw new IllegalStateException("Stack full, pay us more");
        }
        int top = tops[stackIndex];
        StackNode<T> newNode = new StackNode(item, top, -1);
        array[index] = newNode;
        tops[stackIndex] = index;
        index++;
    }

    public T pop(int stackIndex) {
        validateStackIndex(stackIndex);
        if (tops[stackIndex] == -1) {
            throw new IllegalStateException("Stack is empty, can not pop");
        }
        StackNode<T> topNode = array[tops[stackIndex]];
        T value = topNode.getValue();
        tops[stackIndex] = topNode.getPreviousIndex();
        return value;
    }

    public T peek(int stackIndex) {
        validateStackIndex(stackIndex);
        if (tops[stackIndex] == -1) {
            throw new IllegalStateException("Stack is empty, can not pop");
        }
        StackNode<T> topNode = array[tops[stackIndex]];
        T value = topNode.getValue();
        return value;
    }

    public boolean isEmpty(int stackIndex) {
        validateStackIndex(stackIndex);
        return tops[stackIndex] == -1;
    }

    private void validateStackIndex(int stackIndex) {
        if (stackIndex > 2 || stackIndex < 0) {
            throw new IllegalArgumentException("Illegal stack Index " + stackIndex + ". Should be between 0..2");
        }
    }

    private static class StackNode<T> {
        private T value;
        private int previousIndex;
        private int nextIndex;

        public StackNode(T value, int previousIndex, int nextIndex) {
            this.value = value;
            this.previousIndex = previousIndex;
            this.nextIndex = nextIndex;
        }

        public int getNextIndex() {
            return nextIndex;
        }

        public T getValue() {
            return value;
        }

        public int getPreviousIndex() {
            return previousIndex;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setPreviousIndex(int previousIndex) {
            this.previousIndex = previousIndex;
        }

        public void setNextIndex(int nextIndex) {
            this.nextIndex = nextIndex;
        }
    }



}
