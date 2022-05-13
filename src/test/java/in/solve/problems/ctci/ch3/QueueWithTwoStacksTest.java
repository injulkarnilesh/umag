package in.solve.problems.ctci.ch3;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QueueWithTwoStacksTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void queue1Size() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.add(1);
        assertThat(q.remove(), is(1));
    }

    @Test
    public void queueEmpty() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Empty Queue");
        q.remove();
    }

    @Test
    public void queueEmptied() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.add(1);
        assertThat(q.remove(), is(1));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Empty Queue");
        q.remove();
    }

    @Test
    public void queue2Size() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.add(1);
        q.add(2);
        assertThat(q.remove(), is(1));
        assertThat(q.remove(), is(2));
    }

    @Test
    public void queue3Size() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.add(1);
        q.add(2);
        q.add(3);
        assertThat(q.remove(), is(1));
        assertThat(q.remove(), is(2));
        assertThat(q.remove(), is(3));
    }

    @Test
    public void queue6WithRemoveBetweenAdds() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.add(1);
        assertThat(q.remove(), is(1));
        q.add(2);
        q.add(3);
        assertThat(q.remove(), is(2));
        assertThat(q.remove(), is(3));
        q.add(4);
        q.add(5);
        assertThat(q.remove(), is(4));
        q.add(6);
        assertThat(q.remove(), is(5));
        assertThat(q.remove(), is(6));
    }

    @Test
    public void queue100Size() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assertThat(q.remove(), is(i));
        }
    }

}
