package in.solve.problems.companies.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RookStrategy implements PieceStrategy {

  private final static RookStrategy INSTANCE = new RookStrategy();

  private RookStrategy() {
  }

  public static RookStrategy getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Move> possibleMoves(MovementFactory movementFactory) {
    final List<Move> allMoves = new ArrayList<>();
    final List<Move> diagonalForwardLeftMoves = movesWithRepetitiveMovements(movementFactory.leftMovement());
    final List<Move> diagonalForwardRightMoves = movesWithRepetitiveMovements(movementFactory.rightMovement());
    final List<Move> diagonalBackwardLeftMoves = movesWithRepetitiveMovements(movementFactory.forwardMovement());
    final List<Move> diagonalBackwardRightMoves = movesWithRepetitiveMovements(movementFactory.backwardMovement());

    allMoves.addAll(diagonalForwardLeftMoves);
    allMoves.addAll(diagonalForwardRightMoves);
    allMoves.addAll(diagonalBackwardLeftMoves);
    allMoves.addAll(diagonalBackwardRightMoves);

    return allMoves;
  }

  private List<Move> movesWithRepetitiveMovements(Movement theMovement) {
    return IntStream.range(1, 9)
        .mapToObj(i -> {
          List<Movement> movements = new ArrayList<>();
          for (int j = 0; j < i; j++) {
            movements.add(theMovement);
          }
          return Move.of(movements);
        }).collect(Collectors.toList());
  }
}
