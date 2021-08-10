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

public class TrialChessGameBishopTest {

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
  public void testBishopMoveDiagonallyAllBoard() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.BISHOP, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(13));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("E5"), Position.parse("F6"), Position.parse("G7"), Position.parse("H8"),
        Position.parse("E3"), Position.parse("F2"), Position.parse("G1"),
        Position.parse("C3"), Position.parse("B2"), Position.parse("A1"),
        Position.parse("C5"), Position.parse("B6"), Position.parse("A7")));
  }

  @Test
  public void testBishopMoveAllBoardDiagonallyFromSomeLeftSideNearCorner() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.BISHOP, Position.parse("A7"));

    assertThat(nextPossibleCells, hasSize(7));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("B8"),
        Position.parse("B6"), Position.parse("C5"), Position.parse("D4"), Position.parse("E3"), Position.parse("F2"), Position.parse("G1")));
  }

  @Test
  public void testBishopMoveDiagonallyAllBoardWithPiecesOnTheWay() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece sameColorPawn2 = new Piece(Color.WHITE, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("F2"))
        .withPieceAt(sameColorPawn2, Position.parse("B6"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.BISHOP, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(10));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("E5"), Position.parse("F6"), Position.parse("G7"), Position.parse("H8"),
        Position.parse("E3"), Position.parse("F2"),
        Position.parse("C3"), Position.parse("B2"), Position.parse("A1"),
        Position.parse("C5")));
  }

}