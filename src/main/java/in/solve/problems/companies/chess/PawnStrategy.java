package in.solve.problems.companies.chess;

import java.util.Arrays;
import java.util.List;

public class PawnStrategy implements PieceStrategy {

  private final static PawnStrategy INSTANCE = new PawnStrategy();

  private PawnStrategy() {
  }

  public static PawnStrategy getInstance() {
    return INSTANCE;
  }

  public List<Move> possibleMoves(MovementFactory movementFactory) {
    Move onePositionForward = Move.of(Arrays.asList(movementFactory.forwardMovement()));
    Move diagonalKillLeftForward = Move.killOnlyMove(Arrays.asList(movementFactory.diagonalForwardLeftMovement()));
    Move diagonalKillRightForward = Move.killOnlyMove(Arrays.asList(movementFactory.diagonalForwardRightMovement()));
    return Arrays.asList(onePositionForward, diagonalKillLeftForward, diagonalKillRightForward);
  }

}
