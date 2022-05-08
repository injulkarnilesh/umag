package in.solve.problems.ctci.ch3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SetOfStacksTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldPerformPushPopWithinFirstStack() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        assertNotNull(stacks);

        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);

        assertThat(stacks.pop(), is(5));
        assertThat(stacks.pop(), is(4));
        assertThat(stacks.pop(), is(3));
        assertThat(stacks.pop(), is(2));
        assertThat(stacks.pop(), is(1));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("empty");

        stacks.pop();

        stacks.push(10);
        assertThat(stacks.pop(), is(10));
    }

    @Test
    public void shouldPushIntoMultipleStackWhenSizeCrossesCase2Stacks() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        stacks.push(6);
        stacks.push(7);
        stacks.push(8);
        stacks.push(9);

        assertThat(stacks.geNumberOfStacks(), is(2));

        assertThat(stacks.pop(), is(9));
        assertThat(stacks.pop(), is(8));
        assertThat(stacks.pop(), is(7));
        assertThat(stacks.pop(), is(6));
        assertThat(stacks.pop(), is(5));

        assertThat(stacks.geNumberOfStacks(), is(1));

        assertThat(stacks.pop(), is(4));
        assertThat(stacks.pop(), is(3));
        assertThat(stacks.pop(), is(2));
        assertThat(stacks.pop(), is(1));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Stacks empty");
        stacks.pop();
    }

    @Test
    public void shouldContinueToPushIntoMultipleStacks() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        assertTrue(stacks.isEmpty());

        stacks.push(1);
        assertThat(stacks.geNumberOfStacks(), is(1));
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        assertThat(stacks.geNumberOfStacks(), is(1));

        stacks.push(6);
        assertThat(stacks.geNumberOfStacks(), is(2));
        stacks.push(7);
        stacks.push(8);
        stacks.push(9);
        stacks.push(10);
        assertThat(stacks.geNumberOfStacks(), is(2));

        stacks.push(11);
        assertThat(stacks.geNumberOfStacks(), is(3));
        stacks.push(12);
        stacks.push(13);
        stacks.push(14);
        stacks.push(15);
        assertThat(stacks.geNumberOfStacks(), is(3));

        stacks.push(16);
        assertThat(stacks.geNumberOfStacks(), is(4));

        assertThat(stacks.pop(), is(16));
        assertThat(stacks.geNumberOfStacks(), is(3));

        assertThat(stacks.pop(), is(15));
        assertThat(stacks.geNumberOfStacks(), is(3));
        assertThat(stacks.pop(), is(14));
        assertThat(stacks.pop(), is(13));
        assertThat(stacks.pop(), is(12));
        assertThat(stacks.pop(), is(11));
        assertThat(stacks.geNumberOfStacks(), is(2));

        assertThat(stacks.pop(), is(10));
        assertThat(stacks.geNumberOfStacks(), is(2));
        assertThat(stacks.pop(), is(9));
        assertThat(stacks.pop(), is(8));
        assertThat(stacks.pop(), is(7));
        assertThat(stacks.pop(), is(6));
        assertThat(stacks.geNumberOfStacks(), is(1));

        assertThat(stacks.pop(), is(5));
        assertThat(stacks.geNumberOfStacks(), is(1));
        assertThat(stacks.pop(), is(4));
        assertThat(stacks.pop(), is(3));
        assertThat(stacks.pop(), is(2));
        assertThat(stacks.pop(), is(1));
        assertThat(stacks.geNumberOfStacks(), is(0));

        assertTrue(stacks.isEmpty());
    }

    @Test
    public void shouldNotPopFromSpecificIndexStackIfThatIndexDoesNotExists() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("index does not exists");
        stacks.popAt(0);
    }

    @Test
    public void shouldNotPopFromSpecificIndexStackIfThatIndexDoesNotExistsAfterPush() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        stacks.push(1);
        stacks.push(2);
        stacks.popAt(0);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("index does not exists");
        stacks.popAt(1);
    }

    @Test
    public void shouldPopAt0With1Stack() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        assertThat(stacks.popAt(0), is(3));
        assertThat(stacks.popAt(0), is(2));
    }

    @Test
    public void shouldPopAtLastStack() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(3);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        stacks.push(6);
        stacks.push(7);
        stacks.push(8);

        assertThat(stacks.popAt(2), is(8));
        assertThat(stacks.popAt(2), is(7));
        assertThat(stacks.popAt(1), is(6));
        assertThat(stacks.popAt(1), is(5));
        assertThat(stacks.popAt(1), is(4));
        assertThat(stacks.popAt(0), is(3));
        assertThat(stacks.popAt(0), is(2));
        assertThat(stacks.popAt(0), is(1));
    }

    @Test
    public void shouldPopAtNonLastStackAndRollover() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(5);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);

        stacks.push(6);
        stacks.push(7);
        stacks.push(8);
        stacks.push(9);
        stacks.push(10);

        stacks.push(11);
        stacks.push(12);
        stacks.push(13);
        stacks.push(14);

        assertThat(stacks.popAt(1), is(10));
        assertThat(stacks.popAt(1), is(11));
        assertThat(stacks.popAt(2), is(14));
        assertThat(stacks.popAt(1), is(12));
        assertThat(stacks.popAt(1), is(13));
    }

    @Test
    public void shouldPopAtNonLastStackAndRolloverMoreStacks() {
        SetOfStacks<Integer> stacks = SetOfStacks.ofSize(3);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);

        stacks.push(4);
        stacks.push(5);
        stacks.push(6);

        stacks.push(7);
        stacks.push(8);
        stacks.push(9);

        stacks.push(10);
        stacks.push(11);
        stacks.push(12);

        stacks.push(13);
        stacks.push(14);
        stacks.push(15);

        assertThat(stacks.popAt(1), is(6));
        assertThat(stacks.popAt(1), is(7));
        assertThat(stacks.popAt(1), is(8));

        assertThat(stacks.popAt(1), is(9));
        assertThat(stacks.popAt(1), is(10));
        assertThat(stacks.popAt(1), is(11));


        assertThat(stacks.popAt(2), is(15));
        assertThat(stacks.popAt(2), is(14));
        assertThat(stacks.popAt(2), is(13));
    }

}