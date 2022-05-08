package in.solve.problems.ctci.ch3;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LMinStackTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMinStack() {
        LMinStack stack = new LMinStack();
        stack.push(100);
        assertThat(stack.min(), is(100));
        assertThat(stack.min(), is(100));

        stack.push(90);
        assertThat(stack.min(), is(90));

        stack.push(110);
        assertThat(stack.min(), is(90));

        assertThat(stack.peek(), is(110));
        assertThat(stack.pop(), is(110));
        assertThat(stack.pop(), is(90));

        assertThat(stack.min(), is(100));

        assertThat(stack.pop(), is(100));

        expectedException.expect(IllegalStateException.class);
        stack.min();

        stack.push(10);
        assertThat(stack.min(), is(10));

        stack.push(11);
        assertThat(stack.min(), is(10));

        stack.push(9);
        assertThat(stack.min(), is(9));

        stack.push(7);
        assertThat(stack.min(), is(7));

        stack.push(12);
        assertThat(stack.min(), is(7));

        stack.push(15);
        assertThat(stack.min(), is(7));

        assertThat(stack.pop(), is(15));
        assertThat(stack.min(), is(7));

        assertThat(stack.pop(), is(12));
        assertThat(stack.min(), is(7));

        assertThat(stack.pop(), is(7));
        assertThat(stack.min(), is(9));

        assertThat(stack.pop(), is(9));
        assertThat(stack.min(), is(10));

        assertThat(stack.pop(), is(11));
        assertThat(stack.min(), is(10));

        assertThat(stack.pop(), is(10));
    }

    @Test
    public void testMinStackWithDuplicates() {
        LMinStack stack = new LMinStack();
        stack.push(100);
        stack.push(100);
        assertThat(stack.min(), is(100));

        stack.push(110);
        assertThat(stack.min(), is(100));

        stack.push(90);
        assertThat(stack.min(), is(90));

        stack.push(90);
        assertThat(stack.min(), is(90));

        assertThat(stack.pop(), is(90));
        assertThat(stack.min(), is(90));

        assertThat(stack.pop(), is(90));
        assertThat(stack.min(), is(100));

        assertThat(stack.pop(), is(110));
        assertThat(stack.min(), is(100));

        assertThat(stack.pop(), is(100));
        assertThat(stack.min(), is(100));

        assertThat(stack.pop(), is(100));

        assertTrue(stack.isEmpty());

        expectedException.expect(IllegalStateException.class);
        stack.min();
    }
}