public class GridLocation {
    private int row;
    private int column;

    public GridLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other && other != null) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        GridLocation otherGL = (GridLocation) other;
        if (this.row == otherGL.getRow() && this.column == otherGL.getColumn()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.row * 1000 + this.column;
    }

    public String toString() {
        return "(" + this.row + "|" + this.column + ")";
    }
}
