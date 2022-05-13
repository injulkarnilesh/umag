package in.solve.problems.ctci.ch3;

public class StackSorting {

    public static void sort(LStack<Integer> stack) {
        LStack<Integer> tempStack = new LStack<>();
        while (!stack.isEmpty()) {
            Integer item = stack.pop();
            while ((!tempStack.isEmpty()) && tempStack.peek() < item) {
                stack.push(tempStack.pop());
            }
            tempStack.push(item);
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

}
