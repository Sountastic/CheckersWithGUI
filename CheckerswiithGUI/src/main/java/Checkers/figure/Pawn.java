package Checkers.figure;

import Checkers.board.Board;
import Checkers.move.Move;
import Checkers.move.Position;

import java.util.List;

public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValidForFigure(Move move, Board board) {
        return isMoveDiagonalToEmptyField(move, board, 1);
    }

    @Override
    public boolean isMoveWithHitValid(Move move, Board board) {
        List<Move> moves = move.split();
        if (moves.size() == 2) {
            if (!isTargetEmpty(board, moves.get(0).getEndPosition())
                    && (board.getFigure(moves.get(0).getEndPosition()).getColor()) != board.getFigure(moves.get(0).getStartPosition()).getColor()
                    && isTargetEmpty(board, moves.get(1).getEndPosition()))
                return true;
        } else {
            return false;
        }
        return false;
    }

    @Override
    String getFigureSign() {
        return "P";
    }

    public boolean isMoveDiagonalToEmptyField(Move move, Board board, int stepLength) {
        Position start = move.getStartPosition();
        Position end = move.getEndPosition();
        boolean endRowCorrect = start.getRow() + (1 * color.direction) == end.getRow();
        boolean endColumnCorrect = start.getColumn() - stepLength == end.getColumn() || start.getColumn() + stepLength == end.getColumn();
        return isTargetEmpty(board, end) && endRowCorrect && endColumnCorrect;
    }

    private boolean isTargetEmpty(Board board, Position end) {
        return board.getFigure(end.getRow(), end.getColumn()) instanceof None;
    }
}

