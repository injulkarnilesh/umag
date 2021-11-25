package in.solve.problems.ctci.ch1;

public class Q8StringRotation {

    public static boolean areRotation(String input1, String input2) {
        if (input1 == null || input2 == null || input1.length() != input2.length()) {
            return false;
        }
        String concatenated = input1 + input1;
        return concatenated.contains(input2);
    }

}
