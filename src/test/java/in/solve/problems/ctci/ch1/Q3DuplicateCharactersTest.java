package in.solve.problems.ctci.ch1;

import org.junit.Test;

import static in.solve.problems.ctci.ch1.Q3DuplicateCharacters.removeDuplicateCharacters;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class Q3DuplicateCharactersTest {

    @Test
    public void testNullSafeRemoveDuplicateCharacters() {
        String nonDup = removeDuplicateCharacters(null);
        assertNull(nonDup);
    }

    @Test
    public void testEmptyStringRemoveDuplicateCharacters() {
        String nonDup = removeDuplicateCharacters(new StringBuilder(""));
        assertThat(nonDup, isEmptyString());
    }

    @Test
    public void testRemoveDuplicates() {
        assertThat(removeDuplicateCharacters(new StringBuilder("aa")), is("a"));
        assertThat(removeDuplicateCharacters(new StringBuilder("a")), is("a"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abb")), is("ab"));
        assertThat(removeDuplicateCharacters(new StringBuilder("aba")), is("ab"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abaaaabbaaaa")), is("ab"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abcdef")), is("abcdef"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abcdefafec")), is("abcdef"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abcdefd")), is("abcdef"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abcdeff")), is("abcdef"));
        assertThat(removeDuplicateCharacters(new StringBuilder("abddcdeff")), is("abdcef"));
    }



}