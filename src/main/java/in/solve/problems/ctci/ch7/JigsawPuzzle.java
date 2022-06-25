package in.solve.problems.ctci.ch7;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

public class JigsawPuzzle {

    private List<Piece> remainingPiecesToBePlaced = Lists.newArrayList();
    private PiecePlacement startingPiecePlacement;

    private JigsawPuzzle(List<Piece> remainingPiecesToBePlaced) {
        this.remainingPiecesToBePlaced = Lists.newArrayList(remainingPiecesToBePlaced);
    }

    public static JigsawPuzzle init(List<Piece> allPieces) {
        return new JigsawPuzzle(allPieces);
    }

    public PiecePlacement startWith(Piece piece, Orientation orientation) {
        this.startingPiecePlacement = PiecePlacement.place(piece, orientation);
        remainingPiecesToBePlaced.remove(piece);
        return startingPiecePlacement;
    }

    public PiecePlacementResult place(PiecePlacement nearTo, Side side, Piece piece, Orientation orientation) {
        PiecePlacementResult piecePlacementResult = nearTo.placeNeighbor(side, piece, orientation);
        remainingPiecesToBePlaced.remove(piece);
        return piecePlacementResult;
    }

    public PiecePlacementResult place(PiecePlacement nearTo, Side side, Piece piece) {
        return place(nearTo, side, piece, Orientation._0);
    }

    public PiecePlacement getStartingPiecePlacement() {
        return startingPiecePlacement;
    }

    public static class PiecePlacement {
        private Piece piece;
        private Orientation orientation;
        private Map<Side, PiecePlacement> neighbors = Maps.newHashMap();

        private PiecePlacement(Piece piece, Orientation orientation) {
            this.piece = piece;
            this.orientation = orientation;
        }

        public static PiecePlacement place(Piece piece, Orientation orientation) {
            return new PiecePlacement(piece, orientation);
        }

        PiecePlacementResult placeNeighbor(Side side, Piece otherPiece, Orientation orientation) {
            if (neighbors.containsKey(side)) {
                return PiecePlacementResult.failure(side + " is already occupied");
            }
            Side newPieceSide = side.opposite();
            PieceEdge thisEdge = getEdge(side);
            PieceEdge otherEdge = otherPiece.getEdgeWhenOriented(newPieceSide, orientation);
            if (thisEdge.fits(otherEdge)) {
                Direction targetDirection = side.direction;
                Direction oppositeDirection = targetDirection.opposite();
                List<Side> startingSidesToCheckNeighbors = Side.getByDirection(oppositeDirection);

                for (Side startingSide : startingSidesToCheckNeighbors) {
                    Optional<PiecePlacement> neighbor1 = this.getNeighbor(startingSide, side);
                    if (neighbor1.isPresent()) {
                        PieceEdge neighbor1Edge = neighbor1.get().getEdge(startingSide.opposite());
                        PieceEdge matchingPieceEdge = otherPiece.getEdgeWhenOriented(startingSide, orientation);
                        if (!neighbor1Edge.fits(matchingPieceEdge)) {
                            return PiecePlacementResult.failure(otherPiece + "[" + matchingPieceEdge + "]" + " does not fit with existing piece " + neighbor1.get().piece + "[" + neighbor1Edge + "]");
                        }
                    }
                    Optional<PiecePlacement> neighbor2 = this.getNeighbor(startingSide, side, side, startingSide.opposite());
                    if (neighbor2.isPresent()) {
                        PieceEdge neighbor2Edge = neighbor2.get().getEdge(side.opposite());
                        PieceEdge matchingPieceEdge = otherPiece.getEdgeWhenOriented(side, orientation);
                        if (!neighbor2Edge.fits(matchingPieceEdge)) {
                            return PiecePlacementResult.failure(otherPiece + "[" + side + "]" + " does not fit with existing piece " + neighbor2.get().piece + "[" + neighbor2Edge + "]");
                        }
                    }
                }

                PiecePlacement newlyPlacedPiece = new PiecePlacement(otherPiece, orientation);
                this.neighbors.put(side, newlyPlacedPiece);
                newlyPlacedPiece.neighbors.put(newPieceSide, this);
                return PiecePlacementResult.success(newlyPlacedPiece);
            } else {
                return PiecePlacementResult.failure(this.piece + "[" + thisEdge + "]" + " does not fit " + otherPiece + "[" + otherEdge + "]");
            }
        }

        private PieceEdge getEdge(Side side) {
            return this.piece.getEdge(side.rotated(this.orientation));
        }

        public Optional<PiecePlacement> getNeighbor(Side side) {
            return Optional.ofNullable(neighbors.get(side));
        }

        public Optional<PiecePlacement> getNeighbor(Side side, Side ...sides) {
            Optional<PiecePlacement> targetNeighbor = getNeighbor(side);
            for (Side nextSide : sides) {
                if (targetNeighbor.isPresent()) {
                    targetNeighbor = targetNeighbor.get().getNeighbor(nextSide);
                } else {
                    return Optional.empty();
                }
            }
            return targetNeighbor;
        }

    }

    public static class PiecePlacementResult {
        private final PiecePlacement newPlacement;
        private final String errorMessage;
        private final boolean success;

        private PiecePlacementResult(PiecePlacement newPlacement, String errorMessage, boolean success) {
            this.newPlacement = newPlacement;
            this.errorMessage = errorMessage;
            this.success = success;
        }

        public static PiecePlacementResult success(PiecePlacement piecePlacement) {
            return new PiecePlacementResult(piecePlacement, null, true);
        }

        public static PiecePlacementResult failure(String errorMessage) {
            return new PiecePlacementResult(null, errorMessage, false);
        }

        public PiecePlacement getNewPlacement() {
            return newPlacement;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    public enum Orientation {
        _0(0), _90(90), _180(180), _270(270);

        int angleChange;
        Orientation(int angle) {
            this.angleChange = angle;
        }
    }

    public static class PieceBuilder {
        private final String id;
        private Map<Side, PieceEdge> sideEdges = Maps.newHashMap();

        public PieceBuilder(String id) {
            this.id = id;
        }

        public static PieceBuilder withAllPlainEdges(String id) {
            PieceBuilder pieceBuilder = new PieceBuilder(id);
            pieceBuilder.withEdge(Side.TOP, PieceEdge.PLAIN);
            pieceBuilder.withEdge(Side.BOTTOM, PieceEdge.PLAIN);
            pieceBuilder.withEdge(Side.LEFT, PieceEdge.PLAIN);
            pieceBuilder.withEdge(Side.RIGHT, PieceEdge.PLAIN);
            return pieceBuilder;
        }

        public PieceBuilder withEdge(Side side, PieceEdge edge) {
            this.sideEdges.put(side, edge);
            return this;
        }

        public Piece build() {
            Optional<Side> missingSide = Arrays.stream(Side.values()).filter(side -> !sideEdges.containsKey(side)).findFirst();
            if (missingSide.isPresent()) {
                throw new IllegalArgumentException("Missing side definition: " + missingSide.get());
            }
            return new Piece(this);
        }

    }

    public static class Piece {
        private final String id;
        private Map<Side, PieceEdge> sideEdges;

        public Piece(PieceBuilder pieceBuilder) {
            this.id = pieceBuilder.id;
            this.sideEdges = pieceBuilder.sideEdges;
        }

        public PieceEdge getEdge(Side side) {
            return this.sideEdges.get(side);
        }

        public PieceEdge getEdgeWhenOriented(Side side, Orientation orientation) {
            Side sideAfterOrientation = side.rotated(orientation);
            return this.sideEdges.get(sideAfterOrientation);
        }

        @Override
        public String toString() {
            return "Piece{" +
                    "id='" + id + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;
            return Objects.equals(id, piece.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public enum Direction {
        VERTICAL, HORIZONTAL;

        Direction opposite() {
            return this == VERTICAL? HORIZONTAL : VERTICAL;
        }

    }

    public enum Side {
        TOP(0, Direction.VERTICAL),
        RIGHT(90, Direction.HORIZONTAL),
        BOTTOM(180, Direction.VERTICAL),
        LEFT(270, Direction.HORIZONTAL);

        int angle;
        Direction direction;
        Side(int angle, Direction direction) {
            this.angle = angle;
            this.direction = direction;
        }

        public Side  rotate(Orientation orientation) {
            int changedAngle = (this.angle + orientation.angleChange) % 360;
            return parse(changedAngle);
        }

        public Side rotated(Orientation orientation) {
            int changedAngle = (360 + this.angle - orientation.angleChange) % 360;
            return parse(changedAngle);
        }

        public static Side parse(int angle) {
            for (Side value : values()) {
                if (value.angle == angle) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid Side Angle : " + angle);
        }

        public Side opposite() {
            int changedAngle = (this.angle + 180) % 360;
            return parse(changedAngle);
        }

        public static List<Side> getByDirection(Direction direction) {
            return Arrays.stream(values()).filter(value -> value.direction == direction).collect(Collectors.toList());
        }

    }

    public enum PieceEdge {
        PLAIN {
            @Override
            public boolean fits(PieceEdge otherEdge) {
                return otherEdge == PLAIN;
            }
        }, OUTERWARD {
            @Override
            public boolean fits(PieceEdge otherEdge) {
                return otherEdge == INNERWARD;
            }
        }, INNERWARD {
            @Override
            public boolean fits(PieceEdge otherEdge) {
                return otherEdge == OUTERWARD;
            }
        };

        public boolean fits(PieceEdge otherEdge) {
            return false;
        }

    }

}
