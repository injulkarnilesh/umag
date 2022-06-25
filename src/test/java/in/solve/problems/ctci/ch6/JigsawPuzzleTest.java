package in.solve.problems.ctci.ch6;

import com.google.common.collect.Lists;
import in.solve.problems.ctci.ch6.JigsawPuzzle.Orientation;
import in.solve.problems.ctci.ch6.JigsawPuzzle.PieceBuilder;
import in.solve.problems.ctci.ch6.JigsawPuzzle.PieceEdge;
import in.solve.problems.ctci.ch6.JigsawPuzzle.Side;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class JigsawPuzzleTest {

    public static final String DOES_NOT_FIT = "does not fit";
    public static final String ALREADY_OCCUPIED = "already occupied";
    public static final String DOES_NOT_FIT_WITH_EXISTING_PIECE = "does not fit with existing piece";

    @Test
    public void testPlay4Pieces() {
        JigsawPuzzle.Piece a = PieceBuilder.withAllPlainEdges("A").withEdge(Side.RIGHT, PieceEdge.OUTERWARD).withEdge(Side.BOTTOM, PieceEdge.OUTERWARD).build();
        //  ___
        // |   >
        //  -V-
        JigsawPuzzle.Piece b = PieceBuilder.withAllPlainEdges("B").withEdge(Side.LEFT, PieceEdge.INNERWARD)
                .withEdge(Side.BOTTOM, PieceEdge.OUTERWARD).build();
        //  ___
        // >   |
        //   v
        JigsawPuzzle.Piece c = PieceBuilder.withAllPlainEdges("C").withEdge(Side.LEFT, PieceEdge.INNERWARD).withEdge(Side.BOTTOM, PieceEdge.INNERWARD).build();
        // ----
        // >   |
        //  -^-
        JigsawPuzzle.Piece d = PieceBuilder.withAllPlainEdges("D").withEdge(Side.LEFT, PieceEdge.OUTERWARD).withEdge(Side.BOTTOM, PieceEdge.INNERWARD).build();
        //  ---
        // <   |
        //  -^-

        JigsawPuzzle.Piece e = PieceBuilder.withAllPlainEdges("E").withEdge(Side.TOP, PieceEdge.OUTERWARD).withEdge(Side.BOTTOM, PieceEdge.OUTERWARD).withEdge(Side.RIGHT, PieceEdge.OUTERWARD).build();
        //  -^-
        // |   >
        //  -V-

        JigsawPuzzle puzzle = JigsawPuzzle.init(Lists.newArrayList(a, b, c, d));

        JigsawPuzzle.PiecePlacement aPlaced = puzzle.startWith(a, Orientation._0);

        JigsawPuzzle.PiecePlacementResult placementResult = puzzle.place(aPlaced, Side.RIGHT, d);
        assertFalse(placementResult.isSuccess());
        assertThat(placementResult.getErrorMessage(), containsString(DOES_NOT_FIT));

        JigsawPuzzle.PiecePlacementResult bResult = puzzle.place(aPlaced, Side.RIGHT, b);
        assertTrue(bResult.isSuccess());
        JigsawPuzzle.PiecePlacement bPlaced = bResult.getNewPlacement();

        JigsawPuzzle.PiecePlacementResult tryCResult = puzzle.place(bPlaced, Side.BOTTOM, c);
        assertFalse(tryCResult.isSuccess());
        assertThat(tryCResult.getErrorMessage(), containsString(DOES_NOT_FIT));

        JigsawPuzzle.PiecePlacementResult cResult = puzzle.place(bPlaced, Side.BOTTOM, c, Orientation._90);
        assertTrue(cResult.isSuccess());
        JigsawPuzzle.PiecePlacement cPlaced = cResult.getNewPlacement();

        JigsawPuzzle.PiecePlacementResult tryDResult = puzzle.place(bPlaced, Side.BOTTOM, d);
        assertFalse(tryDResult.isSuccess());
        assertThat(tryDResult.getErrorMessage(), containsString(ALREADY_OCCUPIED));

        JigsawPuzzle.PiecePlacementResult eTry = puzzle.place(cPlaced, Side.LEFT, e);
        assertFalse(eTry.isSuccess());
        assertThat(eTry.getErrorMessage(), containsString(DOES_NOT_FIT_WITH_EXISTING_PIECE));

        JigsawPuzzle.PiecePlacementResult dPlaced = puzzle.place(cPlaced, Side.LEFT, d, Orientation._180);
        assertTrue(dPlaced.isSuccess());

    }
}