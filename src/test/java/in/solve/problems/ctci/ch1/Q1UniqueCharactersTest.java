package in.solve.problems.ctci.ch1;


import in.solve.problems.ctci.ch1.Q1UniqueCharacters;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Q1UniqueCharactersTest {

    @Test
    public void testNullStringHasUniqueCharacters() {
        assertTrue(Q1UniqueCharacters.hasUniqueCharacters(null));
    }

    @Test
    public void testEmptyStringHasUniqueCharacters() {
        assertTrue(Q1UniqueCharacters.hasUniqueCharacters(""));
    }

    @Test
    public void testUniqueCharactersInString() {
        assertTrue(Q1UniqueCharacters.hasUniqueCharacters("abcdef2453&#()"));
    }

    @Test
    public void testNonUniqueCharactersInString() {
        assertFalse(Q1UniqueCharacters.hasUniqueCharacters("abcdef2453&#()d"));
    }

    @Test
    public void testNullSmallCaseAlphaStringHasUniqueCharacters() {
        assertTrue(Q1UniqueCharacters.smallCaseAlphabeticStringHasUniqueChars(null));
    }

    @Test
    public void testSmallCaseAlphaEmptyStringHasUniqueCharacters() {
        assertTrue(Q1UniqueCharacters.smallCaseAlphabeticStringHasUniqueChars(""));
    }

    @Test
    public void testSmallCaseAlphaUniqueCharactersInString() {
        assertTrue(Q1UniqueCharacters.smallCaseAlphabeticStringHasUniqueChars("abcdef"));
    }

    @Test
    public void testSmallCaseAlphaNonUniqueCharactersInString() {
        assertFalse(Q1UniqueCharacters.smallCaseAlphabeticStringHasUniqueChars("abcdedz"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSmallCaseAlphaStringDoesNotSupportOtherChars() {
        Q1UniqueCharacters.smallCaseAlphabeticStringHasUniqueChars("abcedZ");
    }
}