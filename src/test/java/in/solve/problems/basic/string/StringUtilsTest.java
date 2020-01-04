package in.solve.problems.basic.string;

import org.junit.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void shouldCheckSegmentationOfNullString() {
        assertFalse(StringUtils.canSegment(null, asList("i" , "am")));
    }

    @Test
    public void shouldCheckSegmentationOnNoWords() {
        assertFalse(StringUtils.canSegment("", asList()));
    }

    @Test
    public void shouldCheckSegmentationOfEmptyString() {
        assertFalse(StringUtils.canSegment("", asList("who")));
    }

    @Test
    public void shouldSegmentSingleLetterString() {
        assertTrue(StringUtils.canSegment("a", asList("a")));
    }

    @Test
    public void shouldNotSegmentSingleLetterString() {
        assertFalse(StringUtils.canSegment("a", asList("b")));
    }

    @Test
    public void shouldSegmentTwoLetterString() {
        assertTrue(StringUtils.canSegment("am", asList("a", "m")));
    }

    @Test
    public void shouldSegmentMultiWordString() {
        assertTrue(StringUtils.canSegment("iamironman", asList("iron", "i", "am", "man")));
    }

    @Test
    public void shouldSegmentMultiWordStringWhenMoreWordsInInput() {
        assertTrue(StringUtils.canSegment("canthis", asList("this", "be", "can", "me")));
    }

    @Test
    public void shouldSegmentMultiWordStringForLongerString() {
        assertTrue(StringUtils.canSegment("iamtheonewhoknocks", asList("am", "i", "the", "who", "one", "knocks", "who")));
    }

    @Test
    public void shouldSegmentMultiWordStringWithDuplicates() {
        assertTrue(StringUtils.canSegment("tobeornottobe", asList("to", "be", "not", "or", "who")));
    }

    @Test
    public void shouldNotSegmentMultiWordString() {
        assertFalse(StringUtils.canSegment("winteriscoming", asList("winter", "coming", "was")));
    }

    @Test
    public void shouldFindPalindromeSubstringForEmptyString() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("");
        assertThat(palindromes, is(empty()));
    }

    @Test
    public void shouldFindPalindromeSubstringForNullString() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings(null);
        assertThat(palindromes, is(empty()));
    }

    @Test
    public void shouldFindPalindromeSubstringForSingleCharacter() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("a");
        assertThat(palindromes, hasSize(1));
        assertThat(palindromes, hasItems("a"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForTwoDiffCharacters() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("ab");
        assertThat(palindromes, hasSize(2));
        assertThat(palindromes, hasItems("a", "b"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForTwoSameCharacters() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("aa");
        assertThat(palindromes, hasSize(2));
        assertThat(palindromes, hasItems("a", "aa"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForDistinctCharacters() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("abcd");
        assertThat(palindromes, hasSize(4));
        assertThat(palindromes, hasItems("a", "b", "c", "d"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForDuplicateCharacters() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("aabb");
        assertThat(palindromes, hasSize(4));
        assertThat(palindromes, hasItems("a", "b", "aa", "bb"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForLongerString() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("abbadttd");
        assertThat(palindromes, hasSize(8));
        assertThat(palindromes, hasItems("a", "b", "d", "t", "bb", "abba", "tt", "dttd"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForLongerStringWithOddNumberPalindromes() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("abadtvtd");
        assertThat(palindromes, hasSize(8));
        assertThat(palindromes, hasItems("a", "b", "d", "t", "v", "aba", "tvt", "dtvtd"));
    }

    @Test
    public void shouldFindPalindromeSubstringsForLongerStringWithEvenAndOddPalindromes() {
        final Set<String> palindromes = StringUtils.palindromeSubstrings("nilinyuuy");
        assertThat(palindromes, hasSize(9));
        assertThat(palindromes, hasItems("n", "i", "l", "u", "y", "ili", "nilin", "uu", "yuuy"));
    }
}
