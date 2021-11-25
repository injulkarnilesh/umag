package in.solve.problems.ctci.ch1;

import org.junit.Test;

import static in.solve.problems.ctci.ch1.Q5StringSpaceReplacement.replaceSpaceInString;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Q5StringSpaceReplacementTest {

    @Test
    public void testNullStringSpaceReplacement() {
        replaceSpaceInString(null, 0);
    }

    @Test
    public void testEmptyStringSpaceReplacement() {
        replaceSpaceInString(new char[]{}, 0);
    }

    @Test
    public void testStringSingleSpaceReplacement() {
        char[] chars = new char[]{ 'a', ' ', 'c', '\u0000', '\u0000'};
        replaceSpaceInString(chars, 3);
        assertThat(String.valueOf(chars), is("a%20c"));
    }

    @Test
    public void testStringReplacementWithSpaceAtEnd() {
        char[] chars = new char[]{ 'a', 'b', ' ', '\u0000', '\u0000'};
        replaceSpaceInString(chars, 3);
        assertThat(String.valueOf(chars), is("ab%20"));
    }

    @Test
    public void testStringReplacementWithSpaceAtStart() {
        char[] chars = new char[]{ ' ', 'b', 'c', '\u0000', '\u0000'};
        replaceSpaceInString(chars, 3);
        assertThat(String.valueOf(chars), is("%20bc"));
    }

    @Test
    public void testStringReplacementWithMultipleSpaces() {
        char[] chars = new char[]{ ' ', 'b', ' ', 'd', ' ', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000'};
        replaceSpaceInString(chars, 5);
        assertThat(String.valueOf(chars), is("%20b%20d%20"));
    }

    @Test
    public void testStringReplacementWithoutSpaces() {
        char[] chars = new char[]{ 'a', 'b', 'c', 'd'};
        replaceSpaceInString(chars, 4);
        assertThat(String.valueOf(chars), is("abcd"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringReplacementWithIncorrectLength() {
        char[] chars = new char[]{ 'a', 'b', 'c', ' '};
        replaceSpaceInString(chars, 4);
    }

}