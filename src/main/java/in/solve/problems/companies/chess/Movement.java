package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.ChessBoard.Position;
import java.util.Optional;
import java.util.function.Function;

public class Movement {

  private final Function<Position, Optional<Position>> positionUpdateBehaviour;

  public Movement(Function<Position, Optional<Position>> positionUpdateBehaviour) {
    this.positionUpdateBehaviour = positionUpdateBehaviour;
  }

  public Optional<Position> apply(Position position) {
    return positionUpdateBehaviour.apply(position);
  }

}
