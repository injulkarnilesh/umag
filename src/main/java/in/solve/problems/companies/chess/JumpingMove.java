package in.solve.problems.companies.chess;

import java.util.List;

public class JumpingMove extends Move {

  public JumpingMove(List<Movement> movements) {
    super(movements);
  }

  @Override
  public boolean canJump() {
    return true;
  }
}
