/**
 * A pair of co-ordinates on the game board
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class Position {
    private int row;
    private int col;

    /**
     * Creates a new instance with the given row and column
     * @param row given row
     * @param col given column
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the string representation of the position
     * @return the position as a string
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
