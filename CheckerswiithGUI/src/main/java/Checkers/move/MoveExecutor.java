package Checkers.move;

import Checkers.board.Board;
import Checkers.figure.Figure;
import Checkers.figure.None;
import Checkers.figure.Pawn;
import Checkers.figure.Queen;

import java.util.List;

public class MoveExecutor {
    private Figure.Color currentPlayerColor = Figure.Color.WHITE;
    private int whitePlayerScore = 12;
    private int blackPlayerScore = 12;

    public void doMove(Move move, Board board) {
        Figure selectedFigure = board.getFigure(move.getStartPosition());
        if (currentPlayerColor == selectedFigure.getColor()) {
            if (selectedFigure.isMoveWithHitValid(move, board)) {
                doHit(move, board);
                if (isPawnToBeQueen(move, board)) {
                    setNewQueen(move, board);
                }
                switchColor();
            } else if (selectedFigure.isMoveValidForFigure(move, board)) {
                board.setFigure(move.getEndPosition(), selectedFigure);
                board.setFigure(move.getStartPosition(), new None());
                if (isPawnToBeQueen(move, board)) {
                    setNewQueen(move, board);
                }
                switchColor();
            } else {
                System.out.println("Error! Invalid move.");
            }
        } else {
            System.out.println("Invalid move. Choose " + getCurrentPlayerColor() + " figure!");
        }
    }

    private void doHit(Move move, Board board) {

        Figure selectedFigure = board.getFigure(move.getStartPosition());
        if (currentPlayerColor == selectedFigure.getColor()) {
            List<Move> moves = move.split();
            Move lastMove = moves.get(moves.size() - 1);
            board.setFigure(move.getStartPosition(), new None());
            board.setFigure(move.getEndPosition(), selectedFigure);
            board.setFigure(lastMove.getStartPosition(), new None());

            switch (opponentPlayerColor()) {
                case WHITE:
                    --whitePlayerScore;
                    break;
                case BLACK:
                    --blackPlayerScore;
                    break;
            }
        } else {
            System.out.println("Invalid move. Choose " + getCurrentPlayerColor() + " figure!");
        }
        if (selectedFigure instanceof Pawn) {
            if (selectedFigure.getColor() == Figure.Color.WHITE) {
                if (move.getEndPosition().getRow() == 7) {
                    board.setFigure(move.getEndPosition(), new Queen(Figure.Color.WHITE));
                }
            } else if (selectedFigure.getColor() == Figure.Color.BLACK) {
                if (move.getEndPosition().getRow() == 0) {
                    board.setFigure(move.getEndPosition(), new Queen(Figure.Color.BLACK));
                }
            }
        }
    }

    public boolean isPawnToBeQueen(Move move, Board board) {
        Figure selectedFigure = board.getFigure(move.getStartPosition());
        if (selectedFigure instanceof Pawn) {
            if (selectedFigure.getColor() == Figure.Color.WHITE) {
                if (move.getEndPosition().getRow() == 7) {
                    return true;
                }
            } else if (selectedFigure.getColor() == Figure.Color.BLACK) {
                if (move.getEndPosition().getRow() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setNewQueen(Move move, Board board) {
        Figure selectedFigure = board.getFigure(move.getStartPosition());
        if (selectedFigure.getColor() == Figure.Color.WHITE) {
            board.setFigure(move.getEndPosition(), new Queen(Figure.Color.WHITE));
        } else {
            board.setFigure(move.getEndPosition(), new Queen(Figure.Color.BLACK));
        }
    }

    private void switchColor() {
        currentPlayerColor = opponentPlayerColor();
    }

    private Figure.Color opponentPlayerColor() {
        return currentPlayerColor == Figure.Color.WHITE ? Figure.Color.BLACK : Figure.Color.WHITE;
    }

    public Figure.Color getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public int getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public int getBlackPlayerScore() {
        return blackPlayerScore;
    }
}
