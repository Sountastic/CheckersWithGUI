package Checkers.move;

public class Position {
    private Integer row;
    private Integer column;

    public Position(Integer column, Integer row) throws Exception {
        if (row > 7 || row < 0 || column > 7 || column < 0)
            throw new Exception("Invalid position");
        this.column = column;
        this.row = row;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

}


