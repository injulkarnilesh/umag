package in.solve.problems.basic.arrays;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ZerosMoverTest {

    @Test
    public void shouldMoveZerosToEndForEmptyArray() {
        assertThat(ZeroMover.toEnd(new int [0]), is(new int[0]));
    }

    @Test
    public void shouldMoveSingleNonZeroElementArray() {
        assertThat(ZeroMover.toEnd(new int [] {1}), is(new int[] {1}));
    }

    @Test
    public void shouldMoveSingleZeroElementArray() {
        assertThat(ZeroMover.toEnd(new int [] {0}), is(new int[] {0}));
    }

    @Test
    public void shouldMoveTwoElementArray() {
        assertThat(ZeroMover.toEnd(new int [] {0, 1}), is(new int[] {1, 0}));
    }

    @Test
    public void shouldMoveAlreadyMovedElementArray() {
        assertThat(ZeroMover.toEnd(new int [] {1, 0}), is(new int[] {1, 0}));
    }

    @Test
    public void shouldMoveSampleArray() {
        assertThat(ZeroMover.toEnd(new int [] {1, 0, 3, 4, 0, 0, 0, 4, 5, 0, 0, 1}),
                                 is(new int[] {1, 3, 4, 4, 5, 1, 0, 0, 0, 0, 0, 0}));
    }
}
