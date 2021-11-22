package in.solve.problems.ctci;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static in.solve.problems.ctci.Q5_1CountEncoder.countEncode;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class Q5_1CountEncoderTest {

    @Test
    public void testNullEncoding() {
        assertFalse(countEncode(null).isPresent());
    }

    @Test
    public void testEmptyEncoding() {
        assertThat(countEncode("").get(), isEmptyString());
    }

    @Test
    public void testEncodingSingleDuplicatesString() {
        assertThat(countEncode("aaaaabc").get(), is("a5b1c1"));
    }

    @Test
    public void testEncodingTwoCharDuplicationString() {
        assertThat(countEncode("aaaaabccc").get(), is("a5b1c3"));
    }

    @Test
    public void testEncodingMultipleCharDuplicationString() {
        assertThat(countEncode("aaaaabcccddeeeefgg").get(), is("a5b1c3d2e4f1g2"));
    }

    @Test
    public void testNotEncodedIfEncodedStringLongerCaseNoDupe() {
        assertFalse(countEncode("abcdef").isPresent());
    }

    @Test
    public void testNotEncodedIfEncodedStringLongerCaseOneCharDupe() {
        assertFalse(countEncode("aabcdef").isPresent());
    }

    @Test
    public void testNotEncodedIfEncodedStringLongerCaseOneCharMultiDupe() {
        assertFalse(countEncode("aabccdeff").isPresent());
    }
}
