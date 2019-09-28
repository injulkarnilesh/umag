package in.solve.problems.basic.math;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PermutationHelperTest {

    @Test
    public void shouldFindPermutationOfNull() {
        final String permutation = PermutationHelper.nthPermutation(null, 100);
        assertNull(permutation);
    }

    @Test
    public void shouldFind1stPermutationOf1CharString() {
        final String permutation = PermutationHelper.nthPermutation("a", 1);
        assertThat(permutation, is("a"));
    }

    @Test
    public void shouldFindPermutationsOf2CharString() {
        String permutation = PermutationHelper.nthPermutation("ab", 1);
        assertThat(permutation, is("ab"));

        permutation = PermutationHelper.nthPermutation("ab", 2);
        assertThat(permutation, is("ba"));
    }

    @Test
    public void shouldFindPermutationOf3CharString() {
        final String str = "bac";
        assertThat(PermutationHelper.nthPermutation(str, 1), is("abc"));
        assertThat(PermutationHelper.nthPermutation(str, 2), is("acb"));

        assertThat(PermutationHelper.nthPermutation(str, 3), is("bac"));
        assertThat(PermutationHelper.nthPermutation(str, 4), is("bca"));

        assertThat(PermutationHelper.nthPermutation(str, 5), is("cab"));
        assertThat(PermutationHelper.nthPermutation(str, 6), is("cba"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNisMoreThanPermutations() {
        PermutationHelper.nthPermutation("abc", 7);
    }

    @Test
    public void shouldFindPermutationOf4CharString() {
       final String str = "cadb";
        assertThat(PermutationHelper.nthPermutation(str, 1), is("abcd"));
        assertThat(PermutationHelper.nthPermutation(str, 2), is("abdc"));
        assertThat(PermutationHelper.nthPermutation(str, 3), is("acbd"));
        assertThat(PermutationHelper.nthPermutation(str, 4), is("acdb"));
        assertThat(PermutationHelper.nthPermutation(str, 5), is("adbc"));
        assertThat(PermutationHelper.nthPermutation(str, 6), is("adcb"));

        assertThat(PermutationHelper.nthPermutation(str, 7), is("bacd"));
        assertThat(PermutationHelper.nthPermutation(str, 8), is("badc"));
        assertThat(PermutationHelper.nthPermutation(str, 9), is("bcad"));
        assertThat(PermutationHelper.nthPermutation(str, 10), is("bcda"));
        assertThat(PermutationHelper.nthPermutation(str, 11), is("bdac"));
        assertThat(PermutationHelper.nthPermutation(str, 12), is("bdca"));

        assertThat(PermutationHelper.nthPermutation(str, 13), is("cabd"));
        assertThat(PermutationHelper.nthPermutation(str, 14), is("cadb"));
        assertThat(PermutationHelper.nthPermutation(str, 15), is("cbad"));
        assertThat(PermutationHelper.nthPermutation(str, 16), is("cbda"));
        assertThat(PermutationHelper.nthPermutation(str, 17), is("cdab"));
        assertThat(PermutationHelper.nthPermutation(str, 18), is("cdba"));

        assertThat(PermutationHelper.nthPermutation(str, 19), is("dabc"));
        assertThat(PermutationHelper.nthPermutation(str, 20), is("dacb"));
        assertThat(PermutationHelper.nthPermutation(str, 21), is("dbac"));
        assertThat(PermutationHelper.nthPermutation(str, 22), is("dbca"));
        assertThat(PermutationHelper.nthPermutation(str, 23), is("dcab"));
        assertThat(PermutationHelper.nthPermutation(str, 24), is("dcba"));
    }

    @Test
    public void shouldFindPermutationsOfNullString() {
        assertThat(PermutationHelper.permutations(null), hasSize(0));
    }

    @Test
    public void shouldFindPermutationsOfSingleCharacterString() {
        final Set<String> permutations = PermutationHelper.permutations("a");
        assertThat(permutations, hasSize(1));
        assertThat(permutations, hasItem("a"));
    }

    @Test
    public void shouldGetPermutationsOf2CharString() {
        final Set<String> permutations = PermutationHelper.permutations("ba");
        assertThat(permutations, hasSize(2));
        assertThat(permutations, hasItems("ab", "ba"));
    }

    @Test
    public void shouldGetPermutationsOf3CharString() {
        final Set<String> permutations = PermutationHelper.permutations("bac");
        assertThat(permutations, hasSize(6));
        assertThat(permutations, hasItems("abc", "acb", "bac", "bca", "cab", "cba"));
    }

    @Test
    public void shouldGetPermutationsOf4CharString() {
        final Set<String> permutations = PermutationHelper.permutations("abdc");
        assertThat(permutations, hasSize(24));
        assertThat(permutations, hasItems("abcd", "abdc", "acbd", "acdb", "adbc", "adcb",
                                          "bacd", "badc", "bcad", "bcda", "bdac", "bdca",
                                          "cabd", "cadb", "cbad", "cbda", "cdab", "cdba",
                                          "dabc", "dacb", "dbac", "dbca", "dcab", "dcba"));
    }

    @Test
    public void shouldGetPermutationsOf3CharStringWithDups() {
        final Set<String> permutations = PermutationHelper.permutations("abb");
        assertThat(permutations, hasSize(3));
        assertThat(permutations, hasItems("abb", "bab", "bba"));
    }

    @Test
    public void shouldGetPermutationsOf4CharStringWithDups() {
        final Set<String> permutations = PermutationHelper.permutations("abbc");
        assertThat(permutations, hasSize(12));
        assertThat(permutations, hasItems("abbc", "abcb", "acbb",
                                          "bbac", "bbca", "bacb",
                                          "babc", "bcab", "bcba",
                                          "cabb", "cbab", "cbba"));
    }

    @Test
    public void shouldGetPermutationsOf5CharStringWithDups() {
        final Set<String> permutations = PermutationHelper.permutations("abbcb");
        /*  (str.length !)
            --------------
            (eachCharacterAppearanceCount)!

            (5!) / (1(a) ! * 3(b)! * 1(c)!)
         */
        assertThat(permutations, hasSize(20));
    }

    @Test
    public void shouldGetPermutationsOf10CharString() {
        final String str = "abcdefghij";
        final Set<String> permutations = PermutationHelper.permutations(str);
        assertThat(permutations, hasSize(PermutationHelper.factorial(str.length())));
    }

    @Test
    public void shouldGetPermutationsOf10CharStringWithDups() {
        final Set<String> permutations = PermutationHelper.permutations("abbbcdeeff");
        /*
            3628800/ (3!)*(2!)*(2!)
         */
        assertThat(permutations, hasSize(151200));
    }

}
