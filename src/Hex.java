/**
 * The smallest part of the game board that can be individually colonised
 * Maintains its colour and colony status
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class Hex {

    private Player team;
    private int colour;

    /**
     * Creates a new Hex of a given colour
     * @param colour assigned colour
     */
    public Hex(int colour) {
        this.colour = colour;
    }

    /**
     * Returns the team that has colonised the Hex, null if it is unoccupied
     * @return the player that owns the Hex or null if unowned Hex
     */
    public Player getTeam() {
        return team;
    }

    /**
     * Updates the Hex's colony status to the given player
     * @param team the player
     */
    public void setTeam(Player team) {
        this.team = team;
    }

    /**
     * Returns the current colour of the Hex
     * @return current assigned colour of the Hex
     */
    public int getColour() {
        return colour;
    }

    /**
     * Updates the Hex's colour to a new given one
     * @param colour new chosen colour
     */
    public void setColour(int colour) {
        this.colour = colour;
    }


}
