package in.solve.problems.ctci.ch5;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BitManipulationUtil {

    public static int binarySubStringReplacement(int n, int m, int from, int to) {
        int mMask =  m << from;  //  00000 1111001 0000000
        int nRightMask = (1 << from) - 1;  // 00000 0000000 0111111
        int nLeftMask = (1 << to) -  1;  // 00000 1111111 1111111
        int nMaskNegation = nLeftMask ^ nRightMask; // 00000 11111111 0000000
        int nMask = ~ nMaskNegation; // 11111 0000000 1111111
        int maskedN = n & nMask; //  11011 1101010 1011111 & nMask = 11011  0000000 1011111
        int result = maskedN |  mMask;
        return result;
    }

    public static String decimalStringToBinary(String decimalString) {
        final int number;
        final double decimal;
        List<Character> nonNumericChars = decimalString.chars().filter(i -> !Character.isDigit(i)).mapToObj(i -> (char) i).collect(Collectors.toList());
        if (nonNumericChars.size() > 1) {
            throw new IllegalArgumentException(decimalString + " should have at-most one . as non numeric char");
        }
        if (nonNumericChars.size() == 1 && (!nonNumericChars.get(0).equals('.'))) {
            throw new IllegalArgumentException(decimalString + " have . as only num numeric char");
        }
        if (decimalString.contains(".")) {
            String[] split = decimalString.split("\\.");
            number = Integer.parseInt(split[0]);
            decimal = Double.parseDouble(decimalString) - number;
        } else {
            number = Integer.parseInt(decimalString);
            decimal = 0d;
        }
        String binaryNumber = toBinaryString(number);
        String binaryDecimal = decimalToBinaryString(decimal);
        return binaryNumber + "." + binaryDecimal;
    }

    private static String toBinaryString(int number) {
        String str = "";
        while (number != 0) {
            str = (number % 2) + str;
            number = number/2;
        }
        return str;
    }

    private static String decimalToBinaryString(double decimal) {
        String str = "";
        while (decimal != 0) {
            if (str.length() == 20) {
                return str;
            }
            double multipleOfTwo = decimal * 2;
            int decimalDigit = multipleOfTwo > 1? 1 : 0;
            str += decimalDigit;
            if(decimalDigit == 1) {
                decimal = multipleOfTwo - 1;
            } else {
                decimal = multipleOfTwo;
            }
        }
        return str;
    }

    public static int findNextNumberWithSameNumberOf1s(int number) {
        if (number <= 0) {
             throw new IllegalArgumentException("Invalid number " + number);
        }
        int index = 0;
        int numberOf1sToMove = 0;
        while (!bitAt(number, index)) {
            index++;
        }
        while (bitAt(number, index)) {
            index++;
            numberOf1sToMove++;
        }
        number = setBitAt(number, index, true);
        number = setBitAt(number, index-1, false);
        index--; index--;
        numberOf1sToMove--;

        for (int i = index; i >= numberOf1sToMove ; i--) {
            number = setBitAt(number, i, false);
        }
        for (int i = numberOf1sToMove - 1; i >= 0; i--) {
            number = setBitAt(number, i, true);
        }

        return number;
    }

    static boolean bitAt(int number, int index) {
        return (number & (1 << index)) > 0;
    }

    private static int setBitAt(int number, int index, boolean bit) {
        if (bit) {
            return number | (1 << index);
        } else {
            return number & ~(1 << index);
        }
    }

    public static boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0;
    }

    public static int countOfBitChangesBetween(int number1, int number2) {
        int xOr = number1 ^ number2;
        return countOf1s(xOr);
    }

    static int countOf1s(int number) {
        int count = 0;
        for (; number > 0; number >>= 1) {
            count +=  number & 1;
        }
        return count;
    }

    public static int findMissingNumber(List<BitNumber> numbers) {
        if (numbers == null || numbers.size() == 0) {
            throw new IllegalArgumentException("Numbers input list required");
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            List<BitNumber> oddNumbers = Lists.newArrayList();
            List<BitNumber> evenNumbers = Lists.newArrayList();
            for (int j = 0; j < numbers.size(); j++) {
                if (numbers.get(j).bitAt(i) == 1) {
                    oddNumbers.add(numbers.get(j));
                } else {
                    evenNumbers.add(numbers.get(j));
                }
            }
            if (oddNumbers.isEmpty() && evenNumbers.isEmpty()) {
                return result;
            }
            boolean missingNumberDigit = oddNumbers.size() < evenNumbers.size();
            result = setBitAt(result, i, missingNumberDigit);
            numbers = missingNumberDigit ? oddNumbers: evenNumbers;
        }

        return result;
    }

    static class BitNumber {
        private final int number;

        public BitNumber(int number) {
            this.number = number;
        }

        public int bitAt(int index) {
            return BitManipulationUtil.bitAt(this.number, index) ? 1 : 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BitNumber bitNumber = (BitNumber) o;
            return number == bitNumber.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

        @Override
        public String toString() {
            return "BitNumber{" +
                    "number=" + number +
                    '}';
        }
    }

}
