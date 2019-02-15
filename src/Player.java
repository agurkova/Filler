import java.util.Random;

public abstract class Player {

    private Position startingPosition;
    private int colour;
    private int score;

    /**
     * Returns the name of the player
     * @return name of player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Stores the name of the player
     * @param name name of the player
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the 'home base'
     * @return the co-ordinates of the beginning Hex
     */
    public Position getStartingPosition() {
        return startingPosition;
    }

    /**
     * Stores the position of the player's first Hex
     * @param startingPosition the co-ordinates of the starting Hex of the player
     */
    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    /**
     * Returns the last selected colour of the player (i.e. their current colour)
     * @return the player's colour
     */
    public int getColour() {
        return colour;
    }

    /**
     * Stores the colour of the player
     * @param colour colour of the player
     */
    public void setColour(int colour) {
        this.colour = colour;
    }

    /**
     * Returns the colour selected by the player
     * @param availableColours all colours sans either of the players' colours
     * @return the selected colour
     */
    public abstract int makeColourChoice(int[] availableColours);

    /**
     * Returns the current score of the player
     * @return total number of player's colonised Hexes
     */
    public int getScore() {
        return score;
    }

    /**
     * Stores the player's score
     * @param score the player's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the strings representation of the player
     * @return the player as a string
     */
    @Override
    public String toString() {
        return "Player{" +
                "startingPosition=" + startingPosition +
                ", colour=" + colour +
                ", score=" + score +
                '}';
    }
}
