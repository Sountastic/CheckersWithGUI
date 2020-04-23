package Checkers.figure;

import Checkers.board.Board;
import Checkers.move.Move;
import Checkers.move.Position;

import java.util.List;

public class Queen extends Figure {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isMoveValidForFigure(Move move, Board board) {
        boolean isValid = true;
        if (move.diagonalDirection() != 0) {
            for (Move oneStep : move.split()) {
                if (!board.isFieldEmpty(oneStep.getEndPosition())) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    @Override
    public boolean isMoveWithHitValid(Move move, Board board) {
        List<Move> moves = move.split();

        Move lastMove = moves.get(moves.size() - 1);
        boolean positionIsCorrect = !isTargetEmpty(board, lastMove.getStartPosition()) && isTargetEmpty(board, lastMove.getEndPosition());

        if (positionIsCorrect) {
            for (Move moveBetween : moves.subList(1, moves.size() - 1)) {
                if (!isTargetEmpty(board, moveBetween.getStartPosition())) return false;
            }
        } else return false;
        return (board.getFigure(lastMove.getStartPosition()).getColor()) != board.getFigure(moves.get(0).getStartPosition()).getColor();
    }


    private boolean isTargetEmpty(Board board, Position end) {
        return board.getFigure(end.getColumn(), end.getRow()) instanceof None;
    }

    @Override
    String getFigureSign() {
        return "Q";
    }
}

