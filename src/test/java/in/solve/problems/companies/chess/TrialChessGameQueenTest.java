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

public class TrialChessGameQueenTest {

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
  public void testQueenMoveAllBoardAllDirections() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.QUEEN, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(27));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("D5"), Position.parse("D6"), Position.parse("D7"), Position.parse("D8"),
        Position.parse("E5"), Position.parse("F6"), Position.parse("G7"), Position.parse("H8"),
        Position.parse("E4"), Position.parse("F4"), Position.parse("G4"), Position.parse("H4"),
        Position.parse("E3"), Position.parse("F2"), Position.parse("G1"),
        Position.parse("D3"), Position.parse("D2"), Position.parse("D1"),
        Position.parse("C3"), Position.parse("B2"), Position.parse("A1"),
        Position.parse("A4"), Position.parse("B4"), Position.parse("C4"),
        Position.parse("C5"), Position.parse("B6"), Position.parse("A7")));
  }

  @Test
  public void testQueenMoveAllBoardAllDirectionsWithKillCheck() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece sameColorPawn2 = new Piece(Color.WHITE, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("G4"))
        .withPieceAt(sameColorPawn2, Position.parse("C4"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.QUEEN, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(23));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("D5"), Position.parse("D6"), Position.parse("D7"), Position.parse("D8"),
        Position.parse("E5"), Position.parse("F6"), Position.parse("G7"), Position.parse("H8"),
        Position.parse("E4"), Position.parse("F4"), Position.parse("G4"),
        Position.parse("E3"), Position.parse("F2"), Position.parse("G1"),
        Position.parse("D3"), Position.parse("D2"), Position.parse("D1"),
        Position.parse("C3"), Position.parse("B2"), Position.parse("A1"),
        Position.parse("C5"), Position.parse("B6"), Position.parse("A7")));
  }

  @Test
  public void testQueenMoveAllBoardAllDirectionsSomeLeftSideNearCorner() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.QUEEN, Position.parse("A7"));

    assertThat(nextPossibleCells, hasSize(21));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("A8"),
        Position.parse("B8"),
        Position.parse("B7"), Position.parse("C7"), Position.parse("D7"), Position.parse("E7"), Position.parse("F7"), Position.parse("G7"), Position.parse("H7"),
        Position.parse("B6"), Position.parse("C5"), Position.parse("D4"), Position.parse("E3"), Position.parse("F2"), Position.parse("G1"),
        Position.parse("A6"), Position.parse("A5"), Position.parse("A4"), Position.parse("A3"), Position.parse("A2"), Position.parse("A1")));
  }

}