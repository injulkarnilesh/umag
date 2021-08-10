package in.solve.problems.companies.chess;

import java.util.List;

public class PawnStrategy implements PieceStrategy {

  private final static PawnStrategy INSTANCE = new PawnStrategy();

  private PawnStrategy() {
  }

  public static PawnStrategy getInstance() {
    return INSTANCE;
  }

  public List<Move> possibleMoves(MovementFactory movementFactory) {
    Move onePositionForward = Move.of(List.of(movementFactory.forwardMovement()));
    Move diagonalKillLeftForward = Move.killOnlyMove(List.of(movementFactory.diagonalForwardLeftMovement()));
    Move diagonalKillRightForward = Move.killOnlyMove(List.of(movementFactory.diagonalForwardRightMovement()));
    return List.of(onePositionForward, diagonalKillLeftForward, diagonalKillRightForward);
  }

}
