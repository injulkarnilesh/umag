package in.solve.problems.companies.chess;

import java.util.List;

public interface PieceStrategy {

  List<Move> possibleMoves(MovementFactory movementFactory);

}
