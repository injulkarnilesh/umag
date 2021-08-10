package in.solve.problems.companies.chess;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import in.solve.problems.companies.chess.ChessBoard.Position;
import in.solve.problems.companies.chess.Piece.Color;
import in.solve.problems.companies.chess.Piece.PieceType;
import in.solve.problems.companies.chess.PlayerColorMapping.Builder;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TrialChessGameKingTest {

  private PlayerColorMapping playerColorMapping;
  private PlayerMovementFactory playerMovementFactory;
  private PieceStrategyFactory pieceStrategyFactory;
  private TrialChessGame trialChessGame;

  @Before
  public void setUp() throws Exception {
    playerColorMapping = new Builder().whitePlayer(Player.PLAYER1).build();
    playerMovementFactory = PlayerMovementFactory.getInstance();
    pieceStrategyFactory = PieceStrategyFactory.getInstance();
    trialChessGame = new TrialChessGame(playerColorMapping, playerMovementFactory, pieceStrategyFactory);
  }

  @Test
  public void testKingMoveOnePositionAllDirections() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.KING, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(8));
    assertThat(nextPossibleCells, hasItems(Position.parse("D5"), Position.parse("C4"),
        Position.parse("D3"), Position.parse("E4"), Position.parse("C5"), Position.parse("E5"),
        Position.parse("C3"), Position.parse("E3")));
  }

  @Test
  public void testKingMoveOnePositionAllDirectionsWithPiecesNearBy() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece sameColorPawn2 = new Piece(Color.WHITE, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("E4"))
        .withPieceAt(sameColorPawn2, Position.parse("D3"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.KING, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(7));
    assertThat(nextPossibleCells, hasItems(Position.parse("D5"), Position.parse("C4"),
        Position.parse("E4"), Position.parse("C5"), Position.parse("E5"),
        Position.parse("C3"), Position.parse("E3")));
  }

  @Test
  public void testKingMoveOnePositionFromLeftSide() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.KING, Position.parse("A4"));

    assertThat(nextPossibleCells, hasSize(5));
    assertThat(nextPossibleCells, hasItems(Position.parse("A5"), Position.parse("B5"),
        Position.parse("B4"), Position.parse("B3"), Position.parse("A3")));
  }

  @Test
  public void testKingMoveOnePositionFromRightSide() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.KING, Position.parse("H6"));

    assertThat(nextPossibleCells, hasSize(5));
    assertThat(nextPossibleCells, hasItems(Position.parse("H7"), Position.parse("G7"),
        Position.parse("G6"), Position.parse("G5"), Position.parse("H5")));
  }

  @Test
  public void testKingMoveOnePositionFromCorner() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.KING, Position.parse("A8"));

    assertThat(nextPossibleCells, hasSize(3));
    assertThat(nextPossibleCells, hasItems(Position.parse("B8"), Position.parse("B7"), Position.parse("A7")));
  }

}