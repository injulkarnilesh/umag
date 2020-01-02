package in.solve.problems.basic.string;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {

    @Test
    public void shouldReverseWordsInNullString() {
        final String reversed = StringUtils.reverseWords(null);
        assertNull(reversed);
    }

    @Test
    public void shouldReverseWordsInEmptyString() {
        final String reversed = StringUtils.reverseWords("");
        assertThat(reversed, is(""));
    }

    @Test
    public void shouldReverseSingleWordSentence() {
        final String reversed = StringUtils.reverseWords("a");
        assertThat(reversed, is("a"));
    }

    @Test
    public void shouldReverseTwoWordSentence() {
        final String reversed = StringUtils.reverseWords("a b");
        assertThat(reversed, is("b a"));
    }

    @Test
    public void shouldReverseMultipleWordSentence() {
        final String reversed = StringUtils.reverseWords("hi there come here");
        assertThat(reversed, is("here come there hi"));
    }

    @Test
    public void shouldReverseMultipleWordSentenceSeparatedByMultipleSpaces() {
        final String reversed = StringUtils.reverseWords("hi  there  come here");
        assertThat(reversed, is("here come there hi"));
    }

    @Test
    public void shouldReverseMultipleWordSentenceSeparatedByTabs() {
        final String reversed = StringUtils.reverseWords("hi    there  come here");
        assertThat(reversed, is("here come there hi"));
    }

    @Test
    public void shouldRemoveDuplicateCharactersFromNull() {
        final String result = StringUtils.removeDupCharacters(null);
        assertNull(result);
    }

    @Test
    public void shouldRemoveDuplicateCharactersFromEmptyString() {
        final String result = StringUtils.removeDupCharacters("");
        assertThat(result, is(""));
    }

    @Test
    public void shouldRemoveDuplicateCharactersFromTwoDuplicateCharacterString() {
        final String result = StringUtils.removeDupCharacters("aa");
        assertThat(result, is("a"));
    }

    @Test
    public void shouldRemoveDuplicateCharactersFromString() {
        final String result = StringUtils.removeDupCharacters("abacdbef");
        assertThat(result, is("abcdef"));
    }

    @Test
    public void shouldRemoveDuplicateCharactersFromStringWitoutDuplication() {
        final String result = StringUtils.removeDupCharacters("abcdefgh");
        assertThat(result, is("abcdefgh"));
    }

    @Test
    public void shouldGetDuplicateCharactersFromNullString() {
        final String result = StringUtils.orderedDuplicates(null);
        assertNull(result);
    }

    @Test
    public void shouldGetDuplicateCharactersFromEmptyString() {
        final String result = StringUtils.orderedDuplicates("");
        assertThat(result, is(""));
    }

    @Test
    public void shouldGetDuplicatesFromNonDuplicateString() {
        final String result = StringUtils.orderedDuplicates("abcd");
        assertThat(result, is(""));
    }

    @Test
    public void shouldGetDuplicatesFromDuplicateCharacterString() {
        final String result = StringUtils.orderedDuplicates("abcdbdpa");
        assertThat(result, is("abd"));
    }

    @Test
    public void shouldGetDuplicatesFromDuplicateCharacterStringForLongString() {
        final String result = StringUtils.orderedDuplicates("nileshlkptalpih");
        assertThat(result, is("ilhp"));
    }
}
