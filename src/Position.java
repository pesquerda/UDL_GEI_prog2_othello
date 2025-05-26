

public class Position {

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        //throw new UnsupportedOperationException("Step 1");
    }

    public int getRow() {
        return row;
        //throw new UnsupportedOperationException("Step 1");
    }

    public int getColumn() {
        return column;
        //throw new UnsupportedOperationException("Step 1");
    }

    // For debugging and printing
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    // For testing (to allow the use of assertEquals)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
