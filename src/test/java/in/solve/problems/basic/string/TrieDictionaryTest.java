package in.solve.problems.basic.string;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieDictionaryTest {

    final private TrieDictionary dictionary = TrieDictionary.createNew();

    @Test
    public void shouldFindAddedSingleLetterWord() {
        dictionary.add("i");

        assertTrue(dictionary.has("i"));
    }

    @Test
    public void shouldFindAddedMultiLetterWord() {
        dictionary.add("am");

        assertTrue(dictionary.has("am"));
    }

    @Test
    public void shouldFindAddedMultipleWords() {
        dictionary.add("i", "am", "batman");

        assertTrue(dictionary.has("am"));
        assertTrue(dictionary.has("i"));
        assertTrue(dictionary.has("batman"));

        assertFalse(dictionary.has("superman"));
    }

    @Test
    public void shouldNotMatchForSubstring() {
        dictionary.add("i", "am", "batman");

        assertFalse(dictionary.has("a"));
        assertFalse(dictionary.has("bat"));
    }

    @Test
    public void shouldFindWordsConsideringCase() {
        dictionary.add("i", "am", "batman");

        assertTrue(dictionary.has("am"));
        assertTrue(dictionary.has("batman"));
        assertTrue(dictionary.has("i"));

        assertFalse(dictionary.has("Batman"));
        assertFalse(dictionary.has("I"));
    }

    @Test
    public void shouldNotAddEmptyWords() {
        dictionary.add("", null, " ", "batman");

        assertTrue(dictionary.has("batman"));

        assertFalse(dictionary.has(""));
        assertFalse(dictionary.has(" "));
        assertFalse(dictionary.has(null));
    }

    @Test
    public void canAddWordsMultipleTimes() {
        dictionary.add("he", "she", "he", "she");

        assertTrue(dictionary.has("he"));
        assertTrue(dictionary.has("she"));

        assertFalse(dictionary.has("it"));
    }

    @Test
    public void shouldFindWordsStartingWith() {
        dictionary.add("hear", "me", "roar", "winter", "is", "coming");

        assertTrue(dictionary.hasWordStartingWith("wint"));
        assertTrue(dictionary.hasWordStartingWith("hea"));
        assertTrue(dictionary.hasWordStartingWith("coming"));
        assertTrue(dictionary.hasWordStartingWith("is"));

        assertFalse(dictionary.hasWordStartingWith("meet"));
        assertFalse(dictionary.hasWordStartingWith("raring"));
        assertFalse(dictionary.hasWordStartingWith("you"));
    }

    @Test
    public void shouldDeleteWords() {
        dictionary.add("bat", "batman", "superman", "super");

        dictionary.delete("bat");
        assertTrue(dictionary.has("batman"));
        assertTrue(dictionary.has("superman"));
        assertTrue(dictionary.has("super"));
        assertFalse(dictionary.has("bat"));

        dictionary.delete("superman");
        assertTrue(dictionary.has("batman"));
        assertTrue(dictionary.has("super"));
        assertFalse(dictionary.has("superman"));
        assertFalse(dictionary.hasWordStartingWith("superm"));

        dictionary.delete("wonder-woman");

        dictionary.delete("b");
        assertTrue(dictionary.has("batman"));
    }
}
