package in.solve.problems.basic.string;

public class StringCompression {

    public static String compress(final String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        final StringBuilder stringBuilder = new StringBuilder(str.length());
        int count = 1;
        Character lastChar = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            final Character currentChar = str.charAt(i);

            if (currentChar.equals(lastChar)) {
                count++;
            } else {
                stringBuilder.append(lastChar);
                stringBuilder.append(count);
                count = 1;
            }

            lastChar = currentChar;
        }

        stringBuilder.append(lastChar);
        stringBuilder.append(count);

        return stringBuilder.toString();
    }

    public static void compressInPlace(final Character[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        int arraySize = chars.length/2;
        Character lastChar = chars[arraySize - 1];
        int count = 1;
        int indexFromLast = chars.length - 1;
        for (int i = arraySize - 2; i >= 0; i--) {
            Character currentChar = chars[i];
            if (currentChar.equals(lastChar)) {
                count++;
            } else {
                int division = count;
                do {
                    int modulo = division%10;
                    division = division/10;
                    chars[indexFromLast--] = Character.forDigit(modulo, 10);
                } while (division != 0);
                chars[indexFromLast--] = lastChar;
                count = 1;
            }
            lastChar = currentChar;
        }

        int division = count;
        do {
            int modulo = division%10;
            division = division/10;
            chars[indexFromLast--] = Character.forDigit(modulo, 10);
        } while (division != 0);
        chars[indexFromLast--] = lastChar;

        int resultFrom = indexFromLast;

        while (resultFrom >= 0) {
            chars[resultFrom--] = null;
        }

        indexFromLast++;
        int i = 0;
        for (;indexFromLast < chars.length; i++, indexFromLast++) {
            chars[i] = chars[indexFromLast];
        }

        for (;i<chars.length;i++) {
            chars[i] = null;
        }

    }

}
