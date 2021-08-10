package in.solve.problems.companies.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ChessBoard {

  private static final int A = 'A';
  private static final int H = 'H';
  private static final int ONE = 1;

  private final Map<Position, Piece> cells = new HashMap<>();

  public boolean isOccupied(Position position) {
    return cells.containsKey(position);
  }

  public void movePiece(Piece piece, Position from, Position to) {
    this.cells.put(to, piece);
    this.cells.remove(from);
  }

  void place(Piece piece, Position at) {
    this.cells.put(at, piece);
  }

  public Piece pieceAt(Position position) {
    return this.cells.get(position);
  }

  Position[][] gePositions() {
    Position[][] positions = new Position[8][8];
    for (int i = 0; i < 8; i ++) {
      for (int j = 0; j < 8; j++) {
        positions[i][j] = new Position((char)(A + i), (ONE + j));
      }
    }
    return positions;
  }

  public static class Position {
    private int row;
    private char column;
    private String id;

    Position(char column, int row) {
      this.column = column;
      this.row = row;
      this.id = column + String.valueOf(row);
    }

    public static Position parse(String id) {
      if (id == null || id.length() != 2) {
        throw new RuntimeException("Invalid Position " + id);
      }
      final char idColumn = id.charAt(0);
      final char idRow = id.charAt(1);
      if (idColumn < A || idColumn > H) {
        throw new RuntimeException("Invalid Column Position " + id);
      }
      final int row = Character.getNumericValue(idRow);
      if (row < 1 || row > 8) {
        throw new RuntimeException("Invalid Row Position " + id);
      }
      return new Position(idColumn, row);
    }

    public String geId() {
      return id;
    }

    @Override
    public String toString() {
      return "[" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Position position = (Position) o;
      return id.equals(position.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }

    public boolean hasPreviousRow() {
      return row > 1;
    }

    public boolean hasNextRow() {
      return row < 8;
    }

    public boolean hasPreviousColumn() {
      return column > A;
    }

    public boolean hasNextColumn() {
      return column < H;
    }

    public Optional<Position> previousRowPosition() {
      if (hasPreviousRow()) {
        return Optional.of(new Position(column, row - 1));
      }
      return Optional.empty();
    }

    public Optional<Position> nextRowPosition() {
      if (hasNextRow()) {
        return Optional.of(new Position(column, row + 1));
      }
      return Optional.empty();
    }

    public Optional<Position> nextColumnPosition() {
      if (hasNextColumn()) {
        return Optional.of(new Position((char)(column + 1), row));
      }
      return Optional.empty();
    }

    public Optional<Position> previousColumnPosition() {
      if (hasPreviousColumn()) {
        return Optional.of(new Position((char)(column - 1), row));
      }
      return Optional.empty();
    }

  }

}
