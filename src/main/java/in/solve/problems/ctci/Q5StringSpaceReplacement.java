package in.solve.problems.ctci;

public class Q5StringSpaceReplacement {

    public static void replaceSpaceInString(char[] input, int length) {
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] == ' ') {
                spaceCount++;
            }
        }
        if (spaceCount == 0) {
            return;
        }
        int lastIndex = length + (spaceCount * 2) - 1;
        if (input.length - 1  != lastIndex) {
            throw new IllegalArgumentException("Input and length incorrect");
        }
        for (int i = length - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                input[lastIndex] = '0';
                input[lastIndex-1] = '2';
                input[lastIndex-2] = '%';
                lastIndex -= 3;
            } else {
                input[lastIndex] = input[i];
                lastIndex -= 1;
            }

        }
    }

}
