package in.solve.problems.companies.chess;

import java.util.List;

public class KillOnlyMove extends Move {

  public KillOnlyMove(List<Movement> movements) {
    super(movements);
  }

  @Override
  public boolean killOnlyMove() {
    return true;
  }
}
