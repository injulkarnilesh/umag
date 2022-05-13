package in.solve.problems.ctci.ch3;

public class QueueWithTwoStacks<T> {

    private LStack<T> primaryStack = new LStack<>();
    private LStack<T> secondaryStack = new LStack<>();

    public void add(T item) {
        primaryStack.push(item);
    }

    public T remove() {
        if (secondaryStack.isEmpty()) {
            if (primaryStack.isEmpty()) {
                throw new IllegalStateException("Empty Queue");
            }
            while (!primaryStack.isEmpty()) {
                secondaryStack.push(primaryStack.pop());
            }
        }
        return secondaryStack.pop();
    }
}
