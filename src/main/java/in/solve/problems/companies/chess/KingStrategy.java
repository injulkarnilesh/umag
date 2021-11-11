package in.solve.problems.companies.chess;

import java.util.Arrays;
import java.util.List;

public class KingStrategy implements PieceStrategy {

  private final static KingStrategy INSTANCE = new KingStrategy();

  private KingStrategy() {
  }

  public static KingStrategy getInstance() {
    return INSTANCE;
  }

  public List<Move> possibleMoves(MovementFactory movementFactory) {
    Move onePositionForward = Move.of(Arrays.asList(movementFactory.forwardMovement()));
    Move onePositionBackward = Move.of(Arrays.asList(movementFactory.backwardMovement()));
    Move onePositionLeft = Move.of(Arrays.asList(movementFactory.leftMovement()));
    Move onePositionRight = Move.of(Arrays.asList(movementFactory.rightMovement()));

    Move diagonallyForwardRight = Move.of(Arrays.asList(movementFactory.diagonalForwardRightMovement()));
    Move diagonallyForwardLeft = Move.of(Arrays.asList(movementFactory.diagonalForwardLeftMovement()));
    Move diagonallyBackwardRight = Move.of(Arrays.asList(movementFactory.diagonalBackwardRightMovement()));
    Move diagonallyBackwardLeft = Move.of(Arrays.asList(movementFactory.diagonalBackwardLeftMovement()));

    return Arrays.asList(onePositionForward, onePositionBackward, onePositionLeft, onePositionRight,
        diagonallyForwardLeft, diagonallyForwardRight, diagonallyBackwardLeft, diagonallyBackwardRight);
  }

}
