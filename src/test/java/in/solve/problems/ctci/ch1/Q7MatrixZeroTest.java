package in.solve.problems.ctci.ch1;


import in.solve.problems.ctci.ch1.Q7MatrixZero;
import org.junit.Test;

import static java.util.Arrays.deepEquals;
import static org.junit.Assert.fail;

public class Q7MatrixZeroTest {

    @Test
    public void testNullSafe() {
        Q7MatrixZero.zerofy(null);
    }

    @Test
    public void testZerofySingleElementArrayWith0() {
        int [][] matrix = {{0}};
        int [][] expectedMatrix = {{0}};

        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void testZerofySingleElementArrayWithNon0() {
        int [][] matrix = {{1}};
        int [][] expectedMatrix = {{1}};

        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test2x2MatrixZerofy() {
        int [][] matrix = new int[][]{
                {0, 2},
                {3, 0}};
        int [][] expectedMatrix = new int[][]{
                {0, 0},
                {0, 0}};

        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
    }


    @Test
    public void test2x2MatrixZerofySingle() {
        int [][] matrix = new int[][]{
                {0, 2},
                {3, 4}};
        int [][] expectedMatrix = new int[][]{
                {0, 0},
                {0, 4}};

        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test5x5Zerofy() {
        int [][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 0, 15},
                {16, 17, 18, 19, 20},
                {0, 22, 23, 24, 25}};

        int [][] expectedMatrix = new int[][]{
                {0, 2, 3, 0, 5},
                {0, 7, 8,0, 10},
                {0, 0, 0, 0, 0},
                {0, 17, 18, 0, 20},
                {0, 0, 0, 0, 0}};


        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void test5x5ZerofyWithNoZero() {
        int [][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};

        int [][] expectedMatrix = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};


        Q7MatrixZero.zerofy(matrix);

        assertEquals(matrix, expectedMatrix);
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