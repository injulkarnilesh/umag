package in.solve.problems.ctci.ch3;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class Q1ArrayFor3StacksTest {

    public static final String STACK_FULL = "Stack full";
    public static final String STACK_IS_EMPTY = "Stack is empty";
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInvalidArraySizeFor3StacksDoesNotPush() {
        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(0);

        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(-1);
    }

    @Test
    public void test1SizeArrayForStack() {
        Q1ArrayFor3Stacks<Integer> stacks = new Q1ArrayFor3Stacks<>(1);
        assertTrue(stacks.isEmpty(0));
        assertTrue(stacks.isEmpty(1));
        assertTrue(stacks.isEmpty(2));

        stacks.push(1, 1);

        assertTrue(stacks.isEmpty(0));
        assertFalse(stacks.isEmpty(1));
        assertThat(stacks.peek(1), is(1));
        assertTrue(stacks.isEmpty(2));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_FULL);
        stacks.push(1, 2);

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_FULL);
        stacks.push(2, 2);

        Integer popped = stacks.pop(1);
        assertThat(popped, is(1));
        assertTrue(stacks.isEmpty(1));

        stacks.push(2, 10);
        assertFalse(stacks.isEmpty(2));
        Integer popped2 = stacks.pop(2);
        assertThat(popped2, is(10));

        assertTrue(stacks.isEmpty(1));
        assertTrue(stacks.isEmpty(2));
        assertTrue(stacks.isEmpty(0));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_IS_EMPTY);
        stacks.pop(2);
    }

    @Test
    public void testStacksWithArrayOfSize10() {
        Q1ArrayFor3Stacks<Integer> stacks = new Q1ArrayFor3Stacks<>(10);
        assertTrue(stacks.isEmpty(0));
        assertTrue(stacks.isEmpty(1));
        assertTrue(stacks.isEmpty(2));

        stacks.push(0, 1);
        stacks.push(0, 2);
        stacks.push(0, 3);
        stacks.push(0, 4);
        stacks.push(0, 5);

        assertThat(stacks.peek(0), is(5));
        Integer popped = stacks.pop(0);
        assertThat(popped, is(5));
        assertThat(stacks.pop(0), is(4));
        assertThat(stacks.pop(0), is(3));
        assertThat(stacks.pop(0), is(2));
        assertThat(stacks.pop(0), is(1));

        expectedException.expectMessage(STACK_IS_EMPTY);
        expectedException.expect(IllegalStateException.class);
        stacks.pop(0);

        assertTrue(stacks.isEmpty(0));

        stacks.push(1, 10);
        stacks.push(1, 20);
        stacks.push(1, 30);
        stacks.push(1, 40);
        stacks.push(1, 50);
        stacks.push(1, 60);

        stacks.push(2, 100);
        stacks.push(2, 200);
        stacks.push(2, 300);
        stacks.push(2, 400);

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_FULL);
        stacks.push(2, 500);

        assertThat(stacks.pop(1), is(60));
        assertThat(stacks.pop(1), is(50));
        assertThat(stacks.pop(1), is(40));

        stacks.push(2, 500);
        stacks.push(2, 600);
        stacks.push(2, 700);

        assertThat(stacks.peek(2), is(700));
        assertThat(stacks.peek(1), is(30));
        assertTrue(stacks.isEmpty(0));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_FULL);
        stacks.push(2, 800);

        assertThat(stacks.pop(2), is(700));
        assertThat(stacks.pop(2), is(600));
        assertThat(stacks.pop(1), is(30));

        stacks.push(0, 1);
        stacks.push(0, 2);
        stacks.push(0, 3);

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(STACK_FULL);
        stacks.push(0, 4);

        assertThat(stacks.peek(0), is(3));
        assertThat(stacks.peek(1), is(20));
        assertThat(stacks.peek(2), is(500));

        assertThat(stacks.pop(2), is(500));
        assertThat(stacks.pop(2), is(400));
        assertThat(stacks.pop(2), is(300));
        assertThat(stacks.pop(2), is(200));

        stacks.push(0, 4);
        stacks.push(1, 30);
        stacks.push(0, 5);
        stacks.push(1, 40);

        assertThat(stacks.peek(0), is(5));
        assertThat(stacks.peek(1), is(40));
        assertThat(stacks.peek(2), is(100));

        assertThat(stacks.pop(0), is(5));
        assertThat(stacks.pop(0), is(4));
        assertThat(stacks.pop(0), is(3));
        assertThat(stacks.pop(0), is(2));
        assertThat(stacks.pop(0), is(1));

        assertThat(stacks.pop(1), is(40));
        assertThat(stacks.pop(1), is(30));
        assertThat(stacks.pop(1), is(20));
        assertThat(stacks.pop(1), is(10));

        assertThat(stacks.pop(0), is(100));

        assertTrue(stacks.isEmpty(0));
        assertTrue(stacks.isEmpty(1));
        assertTrue(stacks.isEmpty(2));
    }

    @Test
    public void testStackIndexInvalidStackPush() {
        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).push(-1, 4);

        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).push(3, 5);
    }


    @Test
    public void testStackIndexInvalidStackPop() {
        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).pop(-1);

        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).pop(3);
    }


    @Test
    public void testStackIndexInvalidStackPeek() {
        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).peek(-1);

        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).peek(3);
    }


    @Test
    public void testStackIndexInvalidStackIsEmpty() {
        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).isEmpty(-1);

        expectedException.expect(IllegalArgumentException.class);
        new Q1ArrayFor3Stacks<Integer>(5).isEmpty(3);
    }
}