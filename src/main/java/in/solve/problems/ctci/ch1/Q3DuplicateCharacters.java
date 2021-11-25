package in.solve.problems.ctci.ch1;

public class Q3DuplicateCharacters {

    public static String removeDuplicateCharacters(StringBuilder input) {
        if (input == null) {
            return null;
        }
        if (input.length() == 0) {
            return input.toString();
        }

        int lastIndex = input.length();
        int lastDuplicates = 0;
        for (int i = 0; i < input.length(); i++) {
            boolean allDupeEnd = false;
            int dupCount = 0;
            for (int j = i+1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    dupCount++;
                    if (j == input.length() -1) {
                        if (dupCount == j - i) {
                            lastIndex = i + 1;
                            allDupeEnd = true;
                        } else {
                            lastDuplicates++;
                        }
                    }
                } else if (dupCount != 0) {
                    input.setCharAt(j - dupCount, input.charAt(j));
                }
            }
            if (allDupeEnd) {
                break;
            }
        }

        return input.substring(0, (lastIndex - lastDuplicates));
    }

}
