package in.solve.problems.companies.chess;

public interface MovementFactory {

  Movement leftMovement();
  Movement rightMovement();
  Movement forwardMovement();
  Movement backwardMovement();

  Movement diagonalForwardLeftMovement();
  Movement diagonalForwardRightMovement();
  Movement diagonalBackwardLeftMovement();
  Movement diagonalBackwardRightMovement();

}
