package in.solve.problems.ctci.ch3;

public class LMinStack extends LStack<Integer> {

    private LStack<Integer> minStack = new LStack<>();

    @Override
    public void push(Integer item) {
        if (minStack.isEmpty()) {
            minStack.push(item);
        } else if (minStack.peek() >= item){
            minStack.push(item);
        }
        super.push(item);
    }

    @Override
    public Integer pop() {
        Integer popped = super.pop();
        if (!minStack.isEmpty() && minStack.peek() == popped) {
            minStack.pop();
        }
        return popped;
    }

    @Override
    public Integer peek() {
        return super.peek();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    public Integer min() {
        return minStack.peek();
    }

}
