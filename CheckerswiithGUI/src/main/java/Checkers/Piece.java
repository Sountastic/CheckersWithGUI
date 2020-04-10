package Checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Checkers.appOld.CheckersApp.TILE_SIZE;

public class Piece extends StackPane {
    private PieceType ptype;
    private boolean isWhite;
//    private boolean isItWhite(){
//        if (PieceType.Color.WHITE) return isWhite;
//        else if (Color == PieceType.Color.BLACK) return !isWhite;
//        else return false;
//    }

    public PieceType getPtype() {
        return ptype;
    }

    public Piece(PieceType ptype, int x, int y) {
        this.ptype = ptype;

        Circle pawn = new Circle(TILE_SIZE * 5);

//        pawn.setFill();
        pawn.setStroke(Color.BLACK);
        pawn.setStrokeWidth(TILE_SIZE * 0.02);

    }
}
