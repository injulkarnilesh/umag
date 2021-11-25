package in.solve.problems.ctci.ch1;

public class Q6MatrixRotation {

    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Can not rotate non same-width-same-height matrix");
        }
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) { //half of the length needs rotation
            //i could be used, bur row, col is more readable
            int row = i;
            int col = i;
            for (int j = 0; j < n - (i * 2) - 1; j++) { //go deeper layer then reduce elements to rotate
                //copy left top element into temp
                int temp = matrix[row][col + j];
                //copy left bottom element into left top
                matrix[row][col + j] = matrix[n - i - 1 - j][col];
                //copy right bottom element into left bottom
                matrix[n - i - 1 - j][col] = matrix[n - i - 1][n - i - 1 - j];
                //copy right top element into right bottom
                matrix[n - i - 1][n - i -1 -j] = matrix[row + j][n - i -1];
                // copy temp element into right top
                matrix[row + j][n - i - 1] = temp;
            }
        }

    }

}
