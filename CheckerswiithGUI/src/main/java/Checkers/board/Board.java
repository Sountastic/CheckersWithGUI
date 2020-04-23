package Checkers.board;

import Checkers.move.Move;
import Checkers.move.MoveExecutor;
import Checkers.move.Position;
import Checkers.figure.Figure;
import Checkers.figure.None;
import Checkers.figure.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<BoardRow> rows = new ArrayList<>();
    MoveExecutor me = new MoveExecutor();

    public Board() {
        for (int i = 0; i < 8; i++) {
            rows.add(new BoardRow());
        }
    }

    public void move(int oldX, int oldY, int newX, int newY) {
        System.out.printf("Move from old x:%d old y: %d to x:%d y:%d \n", oldX, oldY, newX, newY);
        try {
            Move move = new Move(new Position(oldX,oldY), new Position(newX, newY));
            me.doMove(move, this);
        } catch (Exception e) {
            System.out.println("Invalid move");
        }
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public Figure getFigure(Position position) {
        return getFigure(position.getColumn(), position.getRow());
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().set(col, figure);
    }

    public void setFigure(Position position, Figure figure) {
        setFigure(position.getColumn(), position.getRow(), figure);
    }

    public void init() {
        for (int row = 0; row < rows.size(); row++) {
            for (int col = 0; col < 8; col++) {
                if (((col + row) % 2 == 1) && (row < 3 || row > 4)) {
                    setFigure(col, row, new Pawn(getColor(row)));
                } else {
                    setFigure(col, row, new None());
                }
            }
        }
    }

    private Figure.Color getColor(int row) {
        return (row < 4) ? Figure.Color.WHITE : Figure.Color.BLACK;
    }

//    private void fillRow(int row) {
//
//        for (int col = 0; col < rows.size(); col++) {
//            Figure figure;
//            if (row < 3 || row > 4) {
//                figure = (col % 2 == 0) ? new Pawn(color) : new None();
//            } else {
//                figure = new None();
//            }
//            setFigure(row, col, figure);
//        }
//    }

    public boolean isFieldEmpty(Position position) {
        return getFigure(position.getColumn(), position.getRow()) instanceof None;
    }



}
