import java.util.ArrayList;
import java.util.Random;

/**
 * The game board that contains the hexes and the logic to colonise the board.
 * Uses a 2D Array of type Hex elements to keep the game state.
 * Logically in the game each Hex has six neighbours, the relationship between
 * the two is described in the doc for getNeighbours method
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class Board {

    private Hex[][] board;
    private int height;
    private int width;

    /**
     * Creates a new board of the given dimensions and fills it with Hexes of
     * random colours, making sure to mirror the board relative to its centre.
     * @param height height of the game board
     * @param width width of the game board
     * @param numberOfColours number of colours offered in game
     */
    public Board(int height, int width, int numberOfColours) {
        this.height = height;
        this.width = width;

        board = new Hex[height][width];
        Random randomColour = new Random();

        for (int r = 0; r < height / 2; r++ ) { // assuming that the height is even
            for (int c = 0; c < width; c++) {
                int colour = randomColour.nextInt(numberOfColours);
                board[r][c] = new Hex(colour);
                // take mod in case colour is the last number in our sequence so that
                //   ...we don't use an unknown (it becomes the first colour)
                board[height-1-r][width-1-c] = new Hex((colour + 1) % numberOfColours);
            }
        }

    }

    /**
     * Returns the height of the game board
     * @return height of the game board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the game board
     * @return width of the game board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Generates a string representation of the game board, example:
     * /6\_/5\_/0\_/5\_/5\_/2\_/5\_/1\_/0\_/5\_
     * \_/0\_/3\_/1\_/5\_/5\_/5\_/6\_/4\_/6\_/5\_
     * /5\_/5\_/2\_/4\_/2\_/6\_/4\_/4\_/4\_/2\_
     * \_/1\_/4\_/4\_/4\_/1\_/4\_/0\_/1\_/2\_/6\_
     * /4\_/3\_/0\_/0\_/2\_/4\_/4\_/4\_/1\_/2\_
     * \_/3\_/2\_/5\_/5\_/5\_/3\_/1\_/1\_/4\_/5\_
     * /0\_/3\_/2\_/1\_/5\_/2\_/5\_/5\_/5\_/2\_
     * \_/3\_/5\_/5\_/5\_/0\_/3\_/5\_/3\_/6\_/6\_
     * /6\_/0\_/5\_/0\_/6\_/6\_/6\_/2\_/4\_/1\_
     * \_/6\_/1\_/2\_/6\_/3\_/6\_/6\_/1\_/6\_/0\_
     * @return text based game board
     */
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

    /**
     * Applies a player's colour selection to the board:
     * - Changing the player's Hexes to chosen colour
     * - Locates neighbouring Hexes of chosen colour and adds them to the player's colony
     * - Claims and colours Hexes that are completely surrounded by the player's colony
     *  (i.e. colours Hexes when they have no available path to the other player's colony)
     * - Computes the size of the player's colony
     *
     * @param player the current player (whose turn it is)
     * @param chosenColour the colour the player chooses
     * @return total score of the player (i.e. number of Hexes they've colonised)
     */
    public int colonise(Player player, int chosenColour) {
        ArrayList<Position> workQueue = new ArrayList<>();
        int score = 0;

        Position startingPos = player.getStartingPosition();
        Hex currentHex = getHex(startingPos);
        currentHex.setTeam(player);
        currentHex.setColour(chosenColour);
        workQueue.add(startingPos);
        score++;

        // algorithm that works through all neighbouring Hexes
        // formally known as 'Breadth First Search on a Graph' (BFS)
        while (!workQueue.isEmpty()) {
            Position currentPos = workQueue.remove(0);
            ArrayList<Position> neighbours = getNeighbours(currentPos);
            for (Position curNeighbour : neighbours) {
                Hex hex = getHex(curNeighbour);
                if (hex.getTeam() == player ^ hex.getColour() == chosenColour) {
                    // ^ exclusive or (one or the other but NOT both)
                    // we don't need a Hex with both statements b/c it means
                    //  ...it is already claimed by the current player
                    hex.setTeam(player);
                    hex.setColour(chosenColour);
                    workQueue.add(curNeighbour);
                    score++;
                }
            }
        }

        // Now that the method has claimed all the direct possible neighbours
        //  ...it needs to claim all Hexes that have no path to the other player's colony
        boolean[][] visited = new boolean[height][width]; //Hexes that have already been checked
        boolean[][] virusZone = new boolean[height][width]; //Hexes that are possibly reachable by other player

        // Looks through the whole board (top to bottom, left to right) for any unclaimed Hexes
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (visited[r][c]) { // visited array is automatically set to false
                    continue;
                }
                Position curPos = new Position(r, c);
                Hex curHex = getHex(curPos);

                visited[r][c] = true;
                if (curHex.getTeam() == player) {
                    continue;
                } else if (curHex.getTeam() != null){
                    virusZone[r][c] = true;
                    continue;
                }

                // If at this point in the code, then an unclaimed and non-visited Hex has been found
                // We need to check if it is reachable or not
                // We use the BFS algorithm while keeping track of all the unclaimed Hexes, in order to
                //  ...be able to say if all the checked Hexes are found to be reachable or not
                boolean vulnerable = false;
                ArrayList<Position> investigatedBlob = new ArrayList<>();

                investigatedBlob.add(curPos);
                workQueue.add(curPos);
                while (!workQueue.isEmpty()) {
                    Position currentPos = workQueue.remove(0);
                    ArrayList<Position> neighbours = getNeighbours(currentPos);

                    for (Position curNeighbour : neighbours) {
                        if (virusZone[curNeighbour.getRow()][curNeighbour.getCol()]) {
                            vulnerable = true;
                        }
                        if (visited[curNeighbour.getRow()][curNeighbour.getCol()]) {
                            continue;
                        }

                        Hex hex = getHex(curNeighbour);
                        if (hex.getTeam() == null) {
                            investigatedBlob.add(curNeighbour);
                            workQueue.add(curNeighbour);
                        } else if (hex.getTeam() != player) {
                            virusZone[curNeighbour.getRow()][curNeighbour.getCol()] = true;
                            vulnerable = true; //We have found the other team's Hex among the neighbours
                        }

                        visited[curNeighbour.getRow()][curNeighbour.getCol()] = true;
                    }
                }

                // Goes through all Hexes that have been discovered and marks them as reachable or not
                for (Position investigatedPos : investigatedBlob) {
                    if (vulnerable) { //if it is true
                        virusZone[investigatedPos.getRow()][investigatedPos.getCol()] = true;
                    } else {
                        Hex investigatedHex = getHex(investigatedPos);
                        investigatedHex.setTeam(player);
                        investigatedHex.setColour(chosenColour);
                        score++;
                    }
                }
            }
        }

        return score;
    }

    /**
     * Returns the neighbours' positions in the form of an ArrayList of the Hex in the currenPos position
     * @param currentPos position of the current Hex
     * @return ArrayList of the positions of all the Hex's neighbours
     */
    public ArrayList<Position> getNeighbours(Position currentPos) {
        ArrayList<Position> neighbours = new ArrayList<>();
        int r = currentPos.getRow();
        int c = currentPos.getCol();

        // Each Hex has 6 neighbours and their locations depend on whether or not they are in an even or odd row
        //       [0]   [1]   [2]   [3]
        // [0]   /6\_  /5\_  /0\_  /5\_
        // [1]   \_/0  \_/3  \_/1  \_/5
        // [2]   /5\_  /5\_  /2\_  /4\_
        // [3]   \_/1  \_/4  \_/4  \_/4
        // [4]   /4\_  /3\_  /0\_  /0\_
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

    /**
     * Checks whether or not the position of a possible neighbour is withing the game board bounds
     * @param position position of the possible Hex neighbour
     * @param neighbours valid neighbours of the current Hex
     */
    private void checkAndAdd(Position position, ArrayList<Position> neighbours) {
        if (position.getRow() >= 0 && position.getRow() < height && position.getCol() >= 0 && position.getCol() < width) {
            neighbours.add(position);
        }
    }

    /**
     * Returns the Hex at a given position
     * @param position position of the Hex
     * @return Hex at that position
     */
    public Hex getHex(Position position) {
        return board[position.getRow()][position.getCol()];
    }
}
