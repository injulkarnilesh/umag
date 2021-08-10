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

public class TrialChessGamePawnTest {

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
  public void testPawnMoveOnePositionForward() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.PAWN, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(1));
    assertThat(nextPossibleCells, hasItems(Position.parse("D5")));
  }

  @Test
  public void testPawnMoveOnePositionForwardFromSide() {
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.PAWN, Position.parse("A4"));

    assertThat(nextPossibleCells, hasSize(1));
    assertThat(nextPossibleCells, hasItems(Position.parse("A5")));
  }

  @Test
  public void testPawnMoveOnePositionDiagonallyForKill() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece oppositePawn2 = new Piece(Color.BLACK, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("C5"));
    trialChessGame.withPieceAt(oppositePawn2, Position.parse("E5"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.PAWN, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(3));
    assertThat(nextPossibleCells, hasItems(Position.parse("D5"),
        Position.parse("C5"), Position.parse("E5")));
  }

  @Test
  public void testPawnDoesNotKillSamePlayer() {
    Piece oppositePawn1 = new Piece(Color.BLACK, PieceType.PAWN);
    Piece sameColorPawn2 = new Piece(Color.WHITE, PieceType.PAWN);
    trialChessGame.withPieceAt(oppositePawn1, Position.parse("C5"))
        .withPieceAt(sameColorPawn2, Position.parse("E5"));
    final List<Position> nextPossibleCells = trialChessGame
        .getNextPossibleCellsForPieceAt(Player.PLAYER1, PieceType.PAWN, Position.parse("D4"));

    assertThat(nextPossibleCells, hasSize(2));
    assertThat(nextPossibleCells, hasItems(Position.parse("D5"),
        Position.parse("C5")));
  }

}