package algorithms.mazeGenerators;

public class Position {
    int rowPos;
    int colPos;

    public Position(int row, int col) {
        this.rowPos = row;
        this.colPos = col;
    }

    public Position(Position P) {
        this.rowPos = P.getRowIndex();
        this.colPos = P.getColumnIndex();
    }

    public int getRowIndex() {
        return rowPos;
    }

    public int getColumnIndex() {
        return colPos;
    }

    @Override
    public String toString() {
        return "{" + rowPos +
                "," + colPos +
                '}';
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Position position = (Position) o;
        if (position == null || getClass() != o.getClass()) return false;
        return rowPos == position.getRowIndex() && colPos == position.getColumnIndex();
    }

    //    public boolean equals(Position p) {
//        if (this == p) return true;
//        if (p == null) return false;
//        return rowPos == p.rowPos && colPos == p.colPos;
//    }

}
