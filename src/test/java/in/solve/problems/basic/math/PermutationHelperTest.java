package in.solve.problems.basic.math;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PermutationHelperTest {

    @Test
    public void shouldFindPermutationOfNull() {
        final String permutation = PermutationHelper.findNthPermutation(null, 100);
        assertNull(permutation);
    }

    @Test
    public void shouldFind1stPermutationOf1CharString() {
        final String permutation = PermutationHelper.findNthPermutation("a", 1);
        assertThat(permutation, is("a"));
    }

    @Test
    public void shouldFindPermutationsOf2CharString() {
        String permutation = PermutationHelper.findNthPermutation("ab", 1);
        assertThat(permutation, is("ab"));

        permutation = PermutationHelper.findNthPermutation("ab", 2);
        assertThat(permutation, is("ba"));
    }

    @Test
    public void shouldFindPermutationOf3CharString() {
        final String str = "bac";
        assertThat(PermutationHelper.findNthPermutation(str, 1), is("abc"));
        assertThat(PermutationHelper.findNthPermutation(str, 2), is("acb"));

        assertThat(PermutationHelper.findNthPermutation(str, 3), is("bac"));
        assertThat(PermutationHelper.findNthPermutation(str, 4), is("bca"));

        assertThat(PermutationHelper.findNthPermutation(str, 5), is("cab"));
        assertThat(PermutationHelper.findNthPermutation(str, 6), is("cba"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNisMoreThanPermutations() {
        PermutationHelper.findNthPermutation("abc", 7);
    }

    @Test
    public void shouldFindPermutationOf4CharString() {
       final String str = "cadb";
        assertThat(PermutationHelper.findNthPermutation(str, 1), is("abcd"));
        assertThat(PermutationHelper.findNthPermutation(str, 2), is("abdc"));
        assertThat(PermutationHelper.findNthPermutation(str, 3), is("acbd"));
        assertThat(PermutationHelper.findNthPermutation(str, 4), is("acdb"));
        assertThat(PermutationHelper.findNthPermutation(str, 5), is("adbc"));
        assertThat(PermutationHelper.findNthPermutation(str, 6), is("adcb"));

        assertThat(PermutationHelper.findNthPermutation(str, 7), is("bacd"));
        assertThat(PermutationHelper.findNthPermutation(str, 8), is("badc"));
        assertThat(PermutationHelper.findNthPermutation(str, 9), is("bcad"));
        assertThat(PermutationHelper.findNthPermutation(str, 10), is("bcda"));
        assertThat(PermutationHelper.findNthPermutation(str, 11), is("bdac"));
        assertThat(PermutationHelper.findNthPermutation(str, 12), is("bdca"));

        assertThat(PermutationHelper.findNthPermutation(str, 13), is("cabd"));
        assertThat(PermutationHelper.findNthPermutation(str, 14), is("cadb"));
        assertThat(PermutationHelper.findNthPermutation(str, 15), is("cbad"));
        assertThat(PermutationHelper.findNthPermutation(str, 16), is("cbda"));
        assertThat(PermutationHelper.findNthPermutation(str, 17), is("cdab"));
        assertThat(PermutationHelper.findNthPermutation(str, 18), is("cdba"));

        assertThat(PermutationHelper.findNthPermutation(str, 19), is("dabc"));
        assertThat(PermutationHelper.findNthPermutation(str, 20), is("dacb"));
        assertThat(PermutationHelper.findNthPermutation(str, 21), is("dbac"));
        assertThat(PermutationHelper.findNthPermutation(str, 22), is("dbca"));
        assertThat(PermutationHelper.findNthPermutation(str, 23), is("dcab"));
        assertThat(PermutationHelper.findNthPermutation(str, 24), is("dcba"));

    }
}
