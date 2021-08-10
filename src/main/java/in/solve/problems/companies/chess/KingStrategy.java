package in.solve.problems.companies.chess;

import java.util.List;

public class KingStrategy implements PieceStrategy {

  private final static KingStrategy INSTANCE = new KingStrategy();

  private KingStrategy() {
  }

  public static KingStrategy getInstance() {
    return INSTANCE;
  }

  public List<Move> possibleMoves(MovementFactory movementFactory) {
    Move onePositionForward = Move.of(List.of(movementFactory.forwardMovement()));
    Move onePositionBackward = Move.of(List.of(movementFactory.backwardMovement()));
    Move onePositionLeft = Move.of(List.of(movementFactory.leftMovement()));
    Move onePositionRight = Move.of(List.of(movementFactory.rightMovement()));

    Move diagonallyForwardRight = Move.of(List.of(movementFactory.diagonalForwardRightMovement()));
    Move diagonallyForwardLeft = Move.of(List.of(movementFactory.diagonalForwardLeftMovement()));
    Move diagonallyBackwardRight = Move.of(List.of(movementFactory.diagonalBackwardRightMovement()));
    Move diagonallyBackwardLeft = Move.of(List.of(movementFactory.diagonalBackwardLeftMovement()));

    return List.of(onePositionForward, onePositionBackward, onePositionLeft, onePositionRight,
        diagonallyForwardLeft, diagonallyForwardRight, diagonallyBackwardLeft, diagonallyBackwardRight);
  }

}
