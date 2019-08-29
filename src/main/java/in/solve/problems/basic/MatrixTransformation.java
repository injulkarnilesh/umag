package in.solve.problems.basic;

public class MatrixTransformation<T> {

    private final String[][] matrix;

    private MatrixTransformation(final String[][] matrix) {
        this.matrix = matrix;
    }

    public static MatrixTransformation forMatrix(final String[][] matrix) {
        return new MatrixTransformation(matrix);
    }

    public String[] spiral() {
        final int maxColumn = (matrix.length == 0? 0: matrix[0].length) - 1;
        final int maxRow = matrix.length - 1;
        final int size = matrix.length * (matrix.length == 0? 0: matrix[0].length);
        final String[] spiral = new String[size];
        int spiralFilledLength = 0;

        int visitingRightRow = 0;
        int visitingDownColumn = maxColumn;
        int visitingLeftRow = maxRow;
        int visitingUpColumn = 0;

        boolean canGoRight = maxColumn >=0;
        boolean canGoDown;
        boolean canGoLeft;
        boolean canGoUp;

        while (spiralFilledLength < size) {

            if (canGoRight) {
                final String[] toLeftRow = traverseRight(visitingRightRow, visitingUpColumn, visitingDownColumn);
                merge(spiral, spiralFilledLength, toLeftRow);
                spiralFilledLength += toLeftRow.length;
                visitingRightRow++;
                canGoDown = (visitingLeftRow - visitingRightRow) >=0;
            } else {
                break;
            }

            if (canGoDown) {
                final String[] toDownColumn = traverseDown(visitingDownColumn, visitingRightRow, visitingLeftRow);
                merge(spiral, spiralFilledLength, toDownColumn);
                spiralFilledLength += toDownColumn.length;
                visitingDownColumn--;
                canGoLeft = (visitingDownColumn - visitingUpColumn) >= 0;
            } else {
                break;
            }

            if (canGoLeft) {
                final String[] toRightRow = traverseLeft(visitingLeftRow, visitingDownColumn, visitingUpColumn);
                merge(spiral, spiralFilledLength, toRightRow);
                spiralFilledLength += toRightRow.length;
                visitingLeftRow--;
                canGoUp = (visitingLeftRow - visitingRightRow) >=0;
            } else {
                break;
            }

            if (canGoUp) {
                final String[] toUpColumn = traverseUp(visitingUpColumn, visitingLeftRow, visitingRightRow);
                merge(spiral, spiralFilledLength, toUpColumn);
                spiralFilledLength += toUpColumn.length;
                visitingUpColumn++;
                canGoRight = (visitingDownColumn - visitingUpColumn) >= 0;
            } else {
                break;
            }

        }

        return spiral;
    }

    private void merge(String[] target, int from, String[] source) {
        int targetIndex = from;
        for (int i = 0; i < source.length; i++) {
            target[targetIndex++] = source[i];
        }
    }

    private String[] traverseRight(int row, int start, int end) {
        final String[] traversed = new String[end - start + 1];
        int traversedIndex = 0;
        for (int i = start; i <= end; i++) {
            traversed[traversedIndex++] = matrix[row][i];
        }
        return traversed;
    }

    private String[] traverseDown(int column, int start, int end) {
        final String[] traversed = new String[end - start + 1];
        int traversedIndex = 0;
        for (int i = start; i <= end; i++) {
            traversed[traversedIndex++] = matrix[i][column];
        }
        return traversed;
    }

    private String[] traverseLeft(int row, int start, int end) {
        final String[] traversed = new String[start - end + 1];
        int traversedIndex = 0;
        for (int i = start; i >= end; i--) {
            traversed[traversedIndex++] = matrix[row][i];
        }
        return traversed;
    }

    private String[] traverseUp(int column, int start, int end) {
        final String[] traversed = new String[start - end + 1];
        int traversedIndex = 0;
        for (int i = start; i >= end; i--) {
            traversed[traversedIndex++] = matrix[i][column];
        }
        return traversed;
    }

}
