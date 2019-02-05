import java.util.Random;

public class Board {

    private Hex[][] board;
    private int height;
    private int width;

    public Board(int height, int width, int numberOfColours) {
        this.height = height;
        this.width = width;

        board = new Hex[height][width];
        Random randomColour = new Random();

        for (int r = 0; r < height / 2; r++ ) { // assuming that the height is even
            for (int c = 0; c < width; c++) {
                int colour = randomColour.nextInt(numberOfColours);
                board[r][c] = new Hex(colour);
                board[height-1-r][width-1-c] = new Hex((colour + 1) % numberOfColours);
            }
        }

    }

    @Override
    public String toString() {
        String stringArray = "";
        for (int r = 0; r < height; r++) {
            if (r % 2 == 0) {
                stringArray += " ";
            }
            for (int c = 0; c < width; c++) {
                stringArray += board[r][c].getColour() + " ";
            }
            stringArray += "\n";
        }
        return stringArray;
    }
}
