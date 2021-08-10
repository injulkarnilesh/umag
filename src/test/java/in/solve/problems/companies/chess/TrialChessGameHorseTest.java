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

public class TrialChessGameHorseTest {

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
  public void testHorseTwoAndHalfJumps() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.HORSE, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(8));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("C6"), Position.parse("E6"), Position.parse("F5"), Position.parse("F3"),
        Position.parse("C2"), Position.parse("E2"), Position.parse("B5"), Position.parse("B3")));
  }

  @Test
  public void testHorseTwoAndHalfJumpsNotToSameColorPiece() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece sameColorPawn2 = new Piece(Color.WHITE, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("F3"))
        .withPieceAt(sameColorPawn2, Position.parse("F5"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.HORSE, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(7));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("C6"), Position.parse("E6"), Position.parse("F3"),
        Position.parse("C2"), Position.parse("E2"), Position.parse("B5"), Position.parse("B3")));
  }

  @Test
  public void testHorseJumpsFromCorner() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.HORSE, Position.parse("A7"));

    assertThat(nextPossibleCells, hasSize(3));
    assertThat(nextPossibleCells, hasItems(
        Position.parse("C8"), Position.parse("C6"), Position.parse("B5")));
  }

}