package Checkers.move;

import java.util.ArrayList;
import java.util.List;

public class Move {
    private Position startPosition;
    private Position endPosition;

    public Move(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public List<Move> split() {
        List<Move> moves = new ArrayList<>();

        int dX = endPosition.getColumn() - startPosition.getColumn();
        int dY = endPosition.getRow() - startPosition.getRow();

        if (Math.abs(dX) != Math.abs(dY)) {
            throw new ArithmeticException("Move is not diagonal! Cannot split");
        }
        int steps = Math.abs(dX);
        int xStep = (int) Math.signum(dX);
        int yStep = (int) Math.signum(dY);

        Position start = startPosition;
        Position end = endPosition;
        int newRow, newColumn;
        try {
            for (int i = 0; i < steps; i++) {
                newColumn = start.getColumn() + xStep;
                newRow = start.getRow() + yStep;
                end = (new Position(newColumn, newRow));
                moves.add(new Move(start, end));
                start = end;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }

    public int diagonalDirection() {
        int columnDiff = endPosition.getColumn() - startPosition.getColumn();
        int rowDiff = endPosition.getRow() - startPosition.getRow();
        return columnDiff == rowDiff ? columnDiff : 0;
    }

    @Override
    public String toString() {
        return "Move{" +
                "startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                '}';
    }
}