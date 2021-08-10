package in.solve.problems.companies.chess;

public class PlayerMovementFactory {

  private final static PlayerMovementFactory INSTANCE = new PlayerMovementFactory();

  private PlayerMovementFactory() {
  }

  public static PlayerMovementFactory getInstance() {
    return INSTANCE;
  }


  public MovementFactory get(Player player) {
    switch (player) {
      case PLAYER1: return new PlayerOneMovementFactory();
      default: throw new RuntimeException("Player not recognised: " + player);
    }
  }

}
