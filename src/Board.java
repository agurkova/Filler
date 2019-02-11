import java.util.ArrayList;
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        String stringArray = "";
        for (int r = 0; r < height; r++) {
            if (r % 2 == 1) {
                stringArray += "\\_";
            }
            for (int c = 0; c < width; c++) {
                stringArray += "/" + board[r][c].getColour() + "\\_";
            }
            stringArray += "\n";
        }
        return stringArray;
    }

    public int colonise(Player player, int chosenColour) {
        ArrayList<Position> workQueue = new ArrayList<>();
        int score = 0;

        Position startingPos = player.getStartingPosition();
        Hex currentHex = getHex(startingPos);
        currentHex.setTeam(player);
        currentHex.setColour(chosenColour);
        workQueue.add(startingPos);
        score++;

        while (!workQueue.isEmpty()) {
            Position currentPos = workQueue.remove(0);
            ArrayList<Position> neighbours = getNeighbours(currentPos);
            for (Position curNeighbour : neighbours) {
                Hex hex = getHex(curNeighbour);
                if (hex.getTeam() == player ^ hex.getColour() == chosenColour) { // ^ exclusive or
                    hex.setTeam(player);
                    hex.setColour(chosenColour);
                    workQueue.add(curNeighbour);
                    score++;
                }
            }
        }

        return score;
    }

    private ArrayList<Position> getNeighbours(Position currentPos) {
        ArrayList<Position> neighbours = new ArrayList<>();
        int r = currentPos.getRow();
        int c = currentPos.getCol();

        if (currentPos.getRow() % 2 == 0) {
            checkAndAdd(new Position(r + 2, c), neighbours);
            checkAndAdd(new Position(r + 1, c), neighbours);
            checkAndAdd(new Position(r - 1, c), neighbours);
            checkAndAdd(new Position(r - 2, c), neighbours);
            checkAndAdd(new Position(r - 1, c - 1), neighbours);
            checkAndAdd(new Position(r + 1, c - 1), neighbours);
        } else {
            checkAndAdd(new Position(r + 2, c), neighbours);
            checkAndAdd(new Position(r + 1, c + 1), neighbours);
            checkAndAdd(new Position(r - 1, c + 1), neighbours);
            checkAndAdd(new Position(r - 2, c), neighbours);
            checkAndAdd(new Position(r - 1, c), neighbours);
            checkAndAdd(new Position(r + 1, c), neighbours);
        }
        return neighbours;
    }

    private void checkAndAdd(Position position, ArrayList<Position> neighbours) {
        if (position.getRow() >= 0 && position.getRow() < height && position.getCol() >= 0 && position.getCol() < width) {
            neighbours.add(position);
        }
    }

    public Hex getHex(Position position) {
        return board[position.getRow()][position.getCol()];
    }
}
