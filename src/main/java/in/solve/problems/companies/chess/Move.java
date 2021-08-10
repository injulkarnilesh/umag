package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.ChessBoard.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Move {

  private final List<Movement> movements;

  protected Move(List<Movement> movements) {
    this.movements = movements;
  }

  public static Move of(List<Movement> movements) {
    return new Move(movements);
  }

  public static Move jumpingMove(List<Movement> movements) {
    return new JumpingMove(movements);
  }

  public static Move killOnlyMove(List<Movement> movements) {
    return new KillOnlyMove(movements);
  }

  public List<Movement> getMovements() {
    return new ArrayList<>(movements);
  }

  public Optional<Position> apply(ChessBoard board, Position startPosition) {
    Position tempPosition = startPosition;
    for (int i = 0; i < movements.size(); i++) {
      Movement movement = movements.get(i);
      final Optional<Position> possibleNextPosition = movement.apply(tempPosition);
      if (possibleNextPosition.isPresent()) {
        final Position nextPosition = possibleNextPosition.get();
        if (!canJump() && (i != (movements.size() - 1))) {
          if (board.isOccupied(nextPosition)) {
            return Optional.empty();
          }
        }
        tempPosition = nextPosition;
      } else {
        return Optional.empty();
      }
    }
    if (killOnlyMove()) {
      if (board.isOccupied(tempPosition)) {
        if (board.pieceAt(startPosition).getColor() != board.pieceAt(tempPosition).getColor()) {
          return Optional.of(tempPosition);
        }
      }
      return Optional.empty();
    }
    if (board.isOccupied(tempPosition)) {
      if (board.pieceAt(startPosition).getColor() == board.pieceAt(tempPosition).getColor()) {
        return Optional.empty();
      }
    }
    return Optional.of(tempPosition);
  }

  protected boolean canJump() {
    return false;
  }

  protected boolean killOnlyMove() {
    return false;
  }

}
