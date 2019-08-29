package in.solve.problems.basic;

import org.junit.Test;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MatrixTransformationTest {

    @Test
    public void shouldSpiralEmptyMatrix() {
        final String[] spiralMatrix = MatrixTransformation.forMatrix(new String[0][0])
                .spiral();

        assertThat(spiralMatrix, is(arrayWithSize(0)));
    }

    @Test
    public void shouldSpiralSingleElementMatrix() {
        final String[][] matrix = { { "Element" } };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(1)));
        assertThat(spiral[0], is(matrix[0][0]));
    }

    @Test
    public void shouldSpiralTwoElementSingleRowMatrix() {
        final String[][] matrix = { { "A", "B" } };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(2)));
        assertThat(spiral, is(new String[] { "A", "B" }));
    }

    @Test
    public void shouldSpiralThreeElementSingleRowMatrix() {
        final String[][] matrix = { { "A", "B", "C" } };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(3)));
        assertThat(spiral, is(new String[] { "A", "B", "C" }));
    }

    @Test
    public void shouldSpiral2X2Matrix() {
        final String[][] matrix = {
                { "A", "B" },
                { "C", "D" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(4)));
        assertThat(spiral, is(new String[] { "A", "B", "D", "C" }));
    }

    @Test
    public void shouldSpiral2X3Matrix() {
        final String[][] matrix = {
                { "A", "B", "C" },
                { "D", "E", "F" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(6)));
        assertThat(spiral, is(new String[] { "A", "B", "C", "F", "E", "D" }));
    }

    @Test
    public void shouldSpiral2X1Matrix() {
        final String[][] matrix = {
                { "A" },
                { "B" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(2)));
        assertThat(spiral, is(new String[] { "A", "B" }));
    }

    @Test
    public void shouldSpiral3X2Matrix() {
        final String[][] matrix = {
                { "0,0", "0,1" },
                { "1,0", "1,1" },
                { "2,0", "2,1" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(6)));
        assertThat(spiral, is(new String[] { "0,0", "0,1", "1,1", "2,1", "2,0", "1,0" }));
    }

    @Test
    public void shouldSpiral3X3Matrix() {
        final String[][] matrix = {
                { "0,0", "0,1", "0,2" },
                { "1,0", "1,1", "1,2" },
                { "2,0", "2,1", "2,2" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(9)));
        assertThat(spiral, is(new String[] { "0,0", "0,1", "0,2", "1,2", "2,2", "2,1", "2,0", "1,0", "1,1"}));
    }

    @Test
    public void shouldSpiral4X4Matrix() {
        final String[][] matrix = {
                { "0,0", "0,1", "0,2", "0,3" },
                { "1,0", "1,1", "1,2", "1,3" },
                { "2,0", "2,1", "2,2", "2,3" },
                { "3,0", "3,1", "3,2", "3,3" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(16)));
        assertThat(spiral, is(new String[] { "0,0", "0,1", "0,2",  "0,3",
                "1,3", "2,3", "3,3", "3,2", "3,1", "3,0", "2,0",
                "1,0", "1,1", "1,2", "2,2", "2,1"}));
    }


    @Test
    public void shouldSpiral5X6Matrix() {
        final String[][] matrix = {
                { "0,0", "0,1", "0,2", "0,3", "0,4", "0,5" },
                { "1,0", "1,1", "1,2", "1,3", "1,4", "1,5" },
                { "2,0", "2,1", "2,2", "2,3", "2,4", "2,5" },
                { "3,0", "3,1", "3,2", "3,3", "3,4", "3,5" },
                { "4,0", "4,1", "4,2", "4,3", "4,4", "4,5" }
        };

        final String[] spiral = MatrixTransformation.forMatrix(matrix)
                .spiral();

        assertThat(spiral, is(arrayWithSize(30)));
        assertThat(spiral, is(new String[] { "0,0", "0,1", "0,2",  "0,3", "0,4", "0,5",
                "1,5", "2,5", "3,5", "4,5", "4,4", "4,3", "4,2",
                "4,1", "4,0", "3,0", "2,0", "1,0", "1,1", "1,2",
                "1,3", "1,4", "2,4", "3,4", "3,3", "3,2", "3,1",
                "2,1", "2,2", "2,3"}));
    }
}
