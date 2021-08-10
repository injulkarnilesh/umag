package in.solve.problems.companies.chess;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import in.solve.problems.companies.chess.ChessBoard.Position;
import org.junit.Test;

public class ChessBoardTest {

  private ChessBoard board = new ChessBoard();

  @Test
  public void testChessBoardIs8By8() {
    Position[][] positions = board.gePositions();

    assertThat(positions, is(arrayWithSize(8)));
    for (int i = 0; i < 8; i++) {
      final Position[] row = positions[i];
      assertThat(row, is(arrayWithSize(8)));
    }
  }

  @Test
  public void testChessBoardCells() {
    final Position[][] positions = board.gePositions();
    for (int i = 0; i < positions.length; i++) {
      for (int j = 0; j < positions[i].length; j++) {
        assertThat(positions[i][j], is(not(nullValue())));
      }
    }
  }

  @Test
  public void testBoardCellIds() {
    final Position[][] positions = board.gePositions();
    int A = 'A';
    int one = 1;
    for (int i = 0; i < positions.length; i++) {
      for (int j = 0; j < positions[i].length; j++) {
        assertThat(positions[i][j].geId(), is(((char)(A + i)) + String.valueOf(one + j)));
      }
    }
  }

}
