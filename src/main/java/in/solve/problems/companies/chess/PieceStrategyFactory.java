package in.solve.problems.companies.chess;

import in.solve.problems.companies.chess.Piece.PieceType;

public class PieceStrategyFactory {

  private final static PieceStrategyFactory INSTANCE = new PieceStrategyFactory();

  private PieceStrategyFactory() {
  }

  public static PieceStrategyFactory getInstance() {
    return INSTANCE;
  }

  public PieceStrategy get(PieceType pieceType) {
    switch (pieceType) {
      case KING: return KingStrategy.getInstance();
      case QUEEN: return QueenStrategy.getInstance();
      case BISHOP: return BishopStrategy.getInstance();
      case HORSE: return HorseStrategy.getInstance();
      case ROOK: return RookStrategy.getInstance();
      case PAWN: return PawnStrategy.getInstance();
      default: throw new RuntimeException("Unknown piece: " + pieceType);
    }
  }

}
