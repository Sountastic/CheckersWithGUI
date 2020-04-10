package Checkers;

public class PieceType {

    protected Color color;
    public enum Color {

        WHITE(1), BLACK(-1), NONE(0);

        public final int direction;

        Color(int direction) {
            this.direction = direction;
        }

        public Color oppositeColor() {
            return (this == WHITE) ? BLACK : WHITE;
        }
    }


}//    public Color getColor() {
//        return color;
//    }

//    protected String getColorSign() {
//        switch (color) {
//            case NONE:
//                return " ";
//            case BLACK:
//                return "b";
//            case WHITE:
//                return "w";
//            default:
//                return "x";
//        }
//    }
