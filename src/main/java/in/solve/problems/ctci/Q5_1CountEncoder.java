package in.solve.problems.ctci;

import java.util.Optional;

public class Q5_1CountEncoder {

    public static Optional<String> countEncode(String input) {
        if (input == null || input.length() ==0) {
            return Optional.ofNullable(input);
        }
        StringBuffer output = new StringBuffer();
        char lastChar = input.charAt(0);
        int lastCount = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == lastChar) {
                lastCount++;
            } else {
                output.append(lastChar).append(lastCount);
                lastChar = input.charAt(i);
                lastCount = 1;
                if (output.length() > input.length()) {
                    return Optional.empty();
                }
            }
        }
        if (lastChar == input.charAt(input.length() - 1)) {
            output.append(lastChar).append(lastCount);
            if (output.length() > input.length()) {
                return Optional.empty();
            }
        } else {
            output.append(lastCount).append(1);
            if (output.length() > input.length()) {
                return Optional.empty();
            }
        }
        return Optional.of(output.toString());
    }
}
