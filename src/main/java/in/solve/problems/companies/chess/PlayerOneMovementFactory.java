package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.ChessBoard.Position;
import java.util.Optional;
import java.util.function.Function;

public class PlayerOneMovementFactory implements MovementFactory {

  @Override
  public Movement leftMovement() {
    return new Movement(Position::previousColumnPosition);
  }

  @Override
  public Movement rightMovement() {
    return new Movement(Position::nextColumnPosition);
  }

  @Override
  public Movement forwardMovement() {
    return new Movement(Position::nextRowPosition);
  }

  @Override
  public Movement backwardMovement() {
    return new Movement(Position::previousRowPosition);
  }

  @Override
  public Movement diagonalForwardLeftMovement() {
    Function<Position, Optional<Position>> movement = position -> {
      final Optional<Position> forward = position.nextRowPosition();
      return forward.isPresent() ? forward.get().previousColumnPosition() : Optional.empty();
    };
    return new Movement(movement);
  }

  @Override
  public Movement diagonalForwardRightMovement() {
    Function<Position, Optional<Position>> movement = position -> {
      final Optional<Position> forward = position.nextRowPosition();
      return forward.isPresent() ? forward.get().nextColumnPosition() : Optional.empty();
    };
    return new Movement(movement);
  }

  @Override
  public Movement diagonalBackwardLeftMovement() {
    Function<Position, Optional<Position>> movement = position -> {
      final Optional<Position> backward = position.previousRowPosition();
      return backward.isPresent() ? backward.get().previousColumnPosition() : Optional.empty();
    };
    return new Movement(movement);
  }

  @Override
  public Movement diagonalBackwardRightMovement() {
    Function<Position, Optional<Position>> movement = position -> {
      final Optional<Position> backward = position.previousRowPosition();
      return backward.isPresent() ? backward.get().nextColumnPosition() : Optional.empty();
    };
    return new Movement(movement);
  }
}
