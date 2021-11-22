package in.solve.problems.ctci;

import org.junit.Test;

import static java.util.Arrays.deepEquals;
import static org.junit.Assert.fail;

public class Q6MatrixRotationTest {

    @Test
    public void test1x1MatrixRotation() {
        int [][] matrix = new int[][]{{1}};
        int [][] expectedMatrix = new int[][]{{1}};

        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test2x2MatrixRotation() {
        int [][] matrix = new int[][]{
                {1, 2},
                {3, 4}};
        int [][] expectedMatrix = new int[][]{
                {3, 1},
                {4, 2}};

        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test3x3MatrixRotation() {
        int [][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int [][] expectedMatrix = new int[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}};

        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test4x4Rotation() {
        int [][] matrix = new int[][]
                {{1, 2, 3, 4},
                 {5, 6, 7, 8},
                 {9, 10, 11, 12},
                 {13, 14, 15, 16} };

        int [][] expectedMatrix = new int[][]
                {{13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7, 3},
                {16, 12, 8, 4} };

        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test5x5Rotation() {
        int [][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 16, 18, 19, 20},
                {21, 22, 23, 24, 25}};

        int [][] expectedMatrix = new int[][]{
                {21, 16, 11, 6,  1},
                {22, 16, 12, 7, 2},
                {23, 18, 13, 8, 3},
                {24, 19, 14, 9, 4},
                {25, 20, 15, 10, 5}};


        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test6x6Rotation() {
        int [][] matrix = new int[][]{
                {1,  2,  3,  4,  5,  6},
                {7,  8,  9,  10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}};

        int [][] expectedMatrix = new int[][]{
                {31,  25,  19,  13,  7,  1},
                {32,  26,  20,  14, 8, 2},
                {33, 27, 21, 15, 9, 3},
                {34, 28, 22, 16, 10, 4},
                {35, 29, 23, 17, 11, 5},
                {36, 30, 24, 18, 12, 6}};

        Q6MatrixRotation.rotate(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void testSafeRotatingNullMatrix() {
        Q6MatrixRotation.rotate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotAcceptingNonSquareMatrix() {
        int [][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6}};
        Q6MatrixRotation.rotate(matrix);
    }

    private void assertEquals(int [][] matrix, int [][] expectedMatrix) {
        boolean matches = deepEquals(matrix, expectedMatrix);
        if (!matches) {
            System.out.println("Matrix");
            print(matrix);

            System.out.println("Expected Matrix");
            print(expectedMatrix);

            fail("Matrix mismatch, check console for details");
        }
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.print("\n");
        }
    }
}
