package in.solve.problems.companies.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorseStrategy implements PieceStrategy {

  private final static HorseStrategy INSTANCE = new HorseStrategy();

  private HorseStrategy() {
  }

  public static HorseStrategy getInstance() {
    return INSTANCE;
  }

  @Override
  public List<Move> possibleMoves(MovementFactory movementFactory) {
    final List<Move> allMoves = new ArrayList<>();
    final Move forwardLeft = Move.jumpingMove(List.of(movementFactory.forwardMovement(), movementFactory.forwardMovement(), movementFactory.leftMovement()));
    final Move forwardRight = Move.jumpingMove(List.of(movementFactory.forwardMovement(), movementFactory.forwardMovement(), movementFactory.rightMovement()));

    final Move backwardLeft = Move.jumpingMove(List.of(movementFactory.backwardMovement(), movementFactory.backwardMovement(), movementFactory.leftMovement()));
    final Move backwardRight = Move.jumpingMove(List.of(movementFactory.backwardMovement(), movementFactory.backwardMovement(), movementFactory.rightMovement()));

    final Move leftForward = Move.jumpingMove(List.of(movementFactory.leftMovement(), movementFactory.leftMovement(), movementFactory.forwardMovement()));
    final Move leftBackward = Move.jumpingMove(List.of(movementFactory.leftMovement(), movementFactory.leftMovement(), movementFactory.backwardMovement()));

    final Move rightForward = Move.jumpingMove(List.of(movementFactory.rightMovement(), movementFactory.rightMovement(), movementFactory.forwardMovement()));
    final Move rightBackward = Move.jumpingMove(List.of(movementFactory.rightMovement(), movementFactory.rightMovement(), movementFactory.backwardMovement()));

    allMoves.add(forwardLeft);
    allMoves.add(forwardRight);
    allMoves.add(backwardLeft);
    allMoves.add(backwardRight);
    allMoves.add(leftForward);
    allMoves.add(leftBackward);
    allMoves.add(rightForward);
    allMoves.add(rightBackward);

    return allMoves;
  }

}
