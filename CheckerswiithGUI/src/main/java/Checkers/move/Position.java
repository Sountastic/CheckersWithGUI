package Checkers.move;

public class Position {
    private Integer row;
    private Integer column;

    public Position(Integer row, Integer column) throws Exception {
        if (row > 7 || row < 0 || column > 7 || column < 0)
            throw new Exception("Invalid position");
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

}


