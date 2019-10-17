package in.solve.problems.basic.string;

import org.junit.Test;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StringCompressionTest {

    @Test
    public void shouldCompressNull() {
        final String compressed = StringCompression.compress(null);
        assertThat(compressed, is(nullValue()));
    }

    @Test
    public void shouldCompressEmptyString() {
        final String compressed = StringCompression.compress("");
        assertThat(compressed, is(""));
    }

    @Test
    public void shouldCompressSingleCharacter() {
        final String compressed = StringCompression.compress("a");
        assertThat(compressed, is("a1"));
    }

    @Test
    public void shouldCompressTwoSameCharacter() {
        final String compressed = StringCompression.compress("aa");
        assertThat(compressed, is("a2"));
    }

    @Test
    public void shouldCompressThreeSameCharacter() {
        final String compressed = StringCompression.compress("aaa");
        assertThat(compressed, is("a3"));
    }

    @Test
    public void shouldCompressMultipleTwoCharacters() {
        final String compressed = StringCompression.compress("aaabb");
        assertThat(compressed, is("a3b2"));
    }

    @Test
    public void shouldCompressMultipleTwoCharactersRepeated() {
        final String compressed = StringCompression.compress("aaabba");
        assertThat(compressed, is("a3b2a1"));
    }

    @Test
    public void shouldCompressSingleOccurances() {
        final String compressed = StringCompression.compress("abcd");
        assertThat(compressed, is("a1b1c1d1"));
    }

    @Test
    public void shouldCompressMoreThan10Characters() {
        final String compressed = StringCompression.compress("aaaaaaaaaabbbbbbbbbbbb");
        assertThat(compressed, is("a10b12"));
    }

    @Test
    public void shouldCompressInPlaceEmptyString() {
        final Character[] chars = doubleTheSize(new Character[0]);

        StringCompression.compressInPlace(doubleTheSize(chars));

        assertThat(chars, is(emptyArray()));
    }

    @Test
    public void shouldCompressSingleCharacterInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '1'}));
    }

    @Test
    public void shouldCompressTwoSameCharactersInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'a'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '2', null, null}));
    }

    @Test
    public void shouldCompressThreeSameCharactersInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'a', 'a'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '3', null, null, null, null}));
    }

    @Test
    public void shouldCompressMultipleTwoCharactersInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'a', 'a', 'b', 'b'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '3', 'b', '2', null, null, null, null, null, null}));
    }

    @Test
    public void shouldCompressMultipleTwoCharactersRepeatedInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'a', 'a', 'b', 'b', 'a'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '3', 'b', '2', 'a', '1', null, null, null, null, null, null}));
    }

    @Test
    public void shouldCompressSingleOccurancesInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'b', 'c', 'd', 'e'});

        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '1', 'b', '1', 'c', '1', 'd', '1', 'e', '1'}));
    }

    @Test
    public void shouldCompressMoreThan10CharactersInPlace() {
        final Character[] chars = doubleTheSize(new Character[] {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                        'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', });
        StringCompression.compressInPlace(chars);

        assertThat(chars, is(new Character[] {'a', '1', '0', 'b', '1', '2', null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null}));
    }

    @Test
    public void shouldConvertFromNumberToChar() {
        assertThat(Character.forDigit(0, 10), is('0'));
        assertThat(Character.forDigit(5, 10), is('5'));
        assertThat(Character.forDigit(9, 10), is('9'));
    }

    private Character[] doubleTheSize(final Character[] characters) {
        final Character[] doubleSizedArray = new Character[characters.length*2];
        for (int i = 0; i < characters.length; i++) {
            doubleSizedArray[i] = characters[i];
        }
        return doubleSizedArray;
    }

}
