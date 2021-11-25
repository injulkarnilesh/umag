package in.solve.problems.ctci.ch1;


import org.junit.Test;

import static in.solve.problems.ctci.ch1.Q8StringRotation.areRotation;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Q8StringRotationTest {

    @Test
    public void testNullAreNotRotations() {
        assertFalse(areRotation(null, null));
        assertFalse(areRotation("abc", null));
        assertFalse(areRotation(null, "pqrs"));
    }

    @Test
    public void testStringsAreRotations() {
        assertTrue(areRotation("waterbottle", "rbottlewate"));
        assertTrue(areRotation("injulkarnilesh", "nileshinjulkar"));
    }

    @Test
    public void testStringAreNotRotations() {
        assertFalse(areRotation("waterbottle", "bottlewaper"));
        assertFalse(areRotation("waterbottle", "bottle"));
        assertFalse(areRotation("waterbottle", "ttlewater"));
    }
}