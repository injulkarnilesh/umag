package in.solve.problems.basic.arrays;

public class ArrayRotator {

    public static <T> T[] rotate(final T[] array, final int byNoOfElements) {
        if (array.length == 0 || array.length == 1 || byNoOfElements == 0) {
            return array;
        }
        for (int d = 0; d < byNoOfElements; d++) {
            T temp;
            for (int i = 0; i < array.length - 1; i++) {
                int targetIndex = i + 1;
                temp = array[targetIndex];
                array[targetIndex] = array[i];
                array[i] = temp;
            }
        }

        return array;
    }
}
