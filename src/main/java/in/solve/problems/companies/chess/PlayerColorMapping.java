package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.Piece.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerColorMapping {

  private Map<Color, Player> colorPlayerMap = new HashMap<>();
  private Map<Player, Color> playerColorMap = new HashMap<>();

  PlayerColorMapping(Map<Color, Player> colorPlayerMap) {
    this.colorPlayerMap = colorPlayerMap;
    colorPlayerMap.forEach((color, player) -> {
      playerColorMap.put(player, color);
    });
  }

  public Player player(Color color) {
    return colorPlayerMap.get(color);
  }

  public Color color(Player player) {
    return playerColorMap.get(player);
  }

  public static class Builder {
    private Map<Color, Player> colorPlayerMap = new HashMap<>();

    public Builder() {

    }

    public Builder whitePlayer(Player whitePlayer) {
      if (whitePlayer == null) {
        throw new RuntimeException("Player required " + whitePlayer);
      }
      this.colorPlayerMap.put(Color.WHITE, whitePlayer);
      final Player blackPlayer = Arrays.stream(Player.values()).filter(p -> whitePlayer != p)
          .findFirst().get();
      this.colorPlayerMap.put(Color.BLACK, blackPlayer);
      return this;
    }

    public PlayerColorMapping build() {
      if (!this.colorPlayerMap.containsKey(Color.BLACK)
      || !this.colorPlayerMap.containsKey(Color.WHITE)) {
        throw new RuntimeException("Player Color Mapping not Initialized");
      }
      return new PlayerColorMapping(this.colorPlayerMap);
    }

  }

}
