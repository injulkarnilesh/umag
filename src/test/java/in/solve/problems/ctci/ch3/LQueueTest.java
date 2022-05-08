package in.solve.problems.ctci.ch3;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LQueueTest {

    @Test
    public void testQueue() {
        LQueue<Integer> queue = new LQueue<>();
        assertTrue(queue.isEmpty());

        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertThat(queue.remove(), is(1));
        assertThat(queue.remove(), is(2));

        queue.add(10);
        queue.add(20);

        assertThat(queue.remove(), is(3));
        assertThat(queue.remove(), is(10));
        assertThat(queue.remove(), is(20));

        assertTrue(queue.isEmpty());

        queue.add(100);
        queue.add(200);

        assertThat(queue.remove(), is(100));
        assertThat(queue.remove(), is(200));
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyQueueRemove() {
        LQueue<Integer> queue = new LQueue<>();
        queue.remove();
    }
}