package in.solve.problems.ctci;

import org.junit.Assert;
import org.junit.Test;

import static in.solve.problems.ctci.Q4AnagramCheck.areAnagram;
import static org.junit.Assert.*;

public class Q4AnagramCheckTest {

    @Test
    public void testInValidInputs() {
        assertFalse(areAnagram(null, "input2"));
        assertFalse(areAnagram("in1", null));
        assertFalse(areAnagram(null, null));
        assertFalse(areAnagram("", "input2"));
        assertFalse(areAnagram("in10", ""));
        assertFalse(areAnagram("", ""));
    }

    @Test
    public void testAnagrams() {
        assertTrue(areAnagram("silent", "listen"));
        assertTrue(areAnagram("abcd", "bcad"));
        assertTrue(areAnagram("abcdaa", "abcada"));
        assertTrue(areAnagram("abcda90", "9abcad0"));
        assertTrue(areAnagram("ab&da90", "9ab&ad0"));
    }

    @Test
    public void testNoAnagrams() {
        assertFalse(areAnagram("silent", "lisgen"));
        assertFalse(areAnagram("abcddd", "bcad"));
        assertFalse(areAnagram("abcdap", "abcada"));
        assertFalse(areAnagram("abcdap", "abcadauiop"));
        assertFalse(areAnagram("abcda^0", "9abcad0"));
    }
}