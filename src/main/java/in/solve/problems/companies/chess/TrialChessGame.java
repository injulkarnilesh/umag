package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.ChessBoard.Position;
import in.solve.problems.companies.chess.Piece.Color;
import in.solve.problems.companies.chess.Piece.PieceType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrialChessGame {

  private final ChessBoard board;
  private final PlayerColorMapping playerColorMapping;
  private final PlayerMovementFactory playerMovementFactory;
  private final PieceStrategyFactory pieceStrategyFactory;

  public TrialChessGame(PlayerColorMapping playerColorMapping,
      PlayerMovementFactory playerMovementFactory,
      PieceStrategyFactory pieceStrategyFactory) {
    this.board = new ChessBoard();
    this.playerMovementFactory = playerMovementFactory;
    this.pieceStrategyFactory = pieceStrategyFactory;
    this.playerColorMapping = playerColorMapping;
  }

  public TrialChessGame withPieceAt(Piece piece, Position position) {
    this.board.place(piece, position);
    return this;
  }

  public List<Position> getNextPossibleCellsForPieceAt(Player player, PieceType pieceType, Position from) {
    final Color color = playerColorMapping.color(player);
    final Piece piece = new Piece(color, pieceType);
    board.place(piece, from);
    final MovementFactory movementFactory = playerMovementFactory.get(player);
    final PieceStrategy pieceStrategy = pieceStrategyFactory.get(pieceType);
    final List<Move> possibleMoves = pieceStrategy.possibleMoves(movementFactory);
    return possibleMoves.stream()
        .map(move -> move.apply(board, from))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

}
