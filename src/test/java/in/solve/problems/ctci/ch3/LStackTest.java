package in.solve.problems.ctci.ch3;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LStackTest {

    @Test
    public void testStack() {
        LStack<Integer> stack = new LStack<>();
        assertTrue(stack.isEmpty());

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertThat(stack.pop(), is(30));
        assertThat(stack.pop(), is(20));

        stack.push(100);
        stack.push(200);

        assertThat(stack.peek(), is(200));
        assertThat(stack.pop(), is(200));
        assertThat(stack.pop(), is(100));

        assertThat(stack.peek(), is(10));
        assertThat(stack.pop(), is(10));

        assertTrue(stack.isEmpty());

        stack.push(1000);
        assertThat(stack.pop(), is(1000));

        assertTrue(stack.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyStackPop() {
        LStack<Integer> stack = new LStack<>();
        stack.pop();
    }
}