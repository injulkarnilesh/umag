package in.solve.problems.companies.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BishopStrategy implements PieceStrategy {

  private final static BishopStrategy INSTANCE = new BishopStrategy();

  private BishopStrategy() {
  }

  public static BishopStrategy getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Move> possibleMoves(MovementFactory movementFactory) {
    final List<Move> allMoves = new ArrayList<>();
    final List<Move> diagonalForwardLeftMoves = movesWithRepetitiveMovements(movementFactory.diagonalForwardLeftMovement());
    final List<Move> diagonalForwardRightMoves = movesWithRepetitiveMovements(movementFactory.diagonalForwardRightMovement());
    final List<Move> diagonalBackwardLeftMoves = movesWithRepetitiveMovements(movementFactory.diagonalBackwardLeftMovement());
    final List<Move> diagonalBackwardRightMoves = movesWithRepetitiveMovements(movementFactory.diagonalBackwardRightMovement());

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
