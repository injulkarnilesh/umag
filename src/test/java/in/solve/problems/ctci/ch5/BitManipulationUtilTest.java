package in.solve.problems.ctci.ch5;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class BitManipulationUtilTest {

    @Test
    public void testBinarySubStringOfTwoNumbers() {
        int n = 456031; //11011 1101010 1011111
        int m = 121;  //1111001
        int result = BitManipulationUtil.binarySubStringReplacement(n, m, 7, 13);
        assertThat(Integer.toBinaryString(n) + "/" + Integer.toBinaryString(m) + "=?",
                Integer.toBinaryString(result), is("11011 1111001 1011111".replaceAll(" ", "")));
    }

    @Test
    public void testBinarySubStringOfTwoNumbersGivenExample() {
        int n = Integer.parseInt("10000000000", 2);
        int m = Integer.parseInt("10101", 2);
        int result = BitManipulationUtil.binarySubStringReplacement(n, m, 2, 6);
        assertThat(Integer.toBinaryString(n) + "/" + Integer.toBinaryString(m) + "=?",
                Integer.toBinaryString(result), is("10001010100"));
    }


    @Test
    public void testDecimalStringToBinary() {
        assertThat(BitManipulationUtil.decimalStringToBinary("3.72"), is("11.10111000010100011110"));
        assertThat(BitManipulationUtil.decimalStringToBinary("143.72491"), is("10001111.10111001100100111011"));
    }

    @Test
    public void testFindingNexNumberWithSameNumberOf1s() {
        for (int i = 1; i < 100; i++) {
            verifyNextNumberOfSame1s(i);
        }
    }

    private void verifyNextNumberOfSame1s(int number) {
        int nextNumber = BitManipulationUtil.findNextNumberWithSameNumberOf1s(number);
        int countOf1sInNumber = BitManipulationUtil.countOf1s(number);
        int countOf1sInNextNumber = BitManipulationUtil.countOf1s(nextNumber);
        assertThat("nn = " + nextNumber + " > n=" + number, nextNumber, is(greaterThan(number)));
        String reasonCountMismatch = "nn/count1(nn) = " + nextNumber + "/" + countOf1sInNextNumber + " == n/count1(n) = " + number + "/" + countOf1sInNumber;
        assertThat(reasonCountMismatch, countOf1sInNextNumber, is(countOf1sInNumber));
        for (int numberInBetween = nextNumber-1; numberInBetween > number; numberInBetween--) {
            int countOf1sInNumberInBetween = BitManipulationUtil.countOf1s(numberInBetween);
            String reason = "Number in between has same 1s nb= " + numberInBetween + " for n= " + number + " nn= " + nextNumber;
            assertThat(reason, countOf1sInNumberInBetween, is(not(countOf1sInNumber)));
        }
    }


    @Test
    public void testIfNumberIsPowerOfTwo() {
        for (int i = 0; i < 20; i++) {
            int number = (int) Math.pow(2, i);
            assertTrue("n = " + number, BitManipulationUtil.isPowerOfTwo(number));
        }
        for (int i = 5; i < 20; i++) {
            int number = (int) Math.pow(2, i) - 3;
            assertFalse("n = " + number, BitManipulationUtil.isPowerOfTwo(number));
        }
        for (int i = 5; i < 20; i++) {
            int number = (int) Math.pow(2, i) + 4;
            assertFalse("n = " + number, BitManipulationUtil.isPowerOfTwo(number));
        }
    }

    @Test
    public void countNumberOfBitDifferences() {
        assertThat(BitManipulationUtil.countOfBitChangesBetween(31, 14), is(2));
    }

    @Test
    public void findMissingNumber() {
        List<BitManipulationUtil.BitNumber> allNumbers = IntStream.range(0, 1000).mapToObj(BitManipulationUtil.BitNumber::new).collect(Collectors.toList());
        for (int i = 0; i < 1000; i++) {
            final int missingNumber = i;
            List<BitManipulationUtil.BitNumber> allButMissing = allNumbers.stream().filter(num -> !num.equals(new BitManipulationUtil.BitNumber(missingNumber))).collect(Collectors.toList());
            int actualMissingNumber = BitManipulationUtil.findMissingNumber(allButMissing);
            assertThat(actualMissingNumber, is(missingNumber));
        }
    }
}

