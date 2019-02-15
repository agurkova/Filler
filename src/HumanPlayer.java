/**
 * Represents a human player
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class HumanPlayer extends Player {
    private UserInterface ui;

    /**
     * Creates a new instance and links it to a provided user interface
     * @param ui the specified user interface
     */
    public HumanPlayer(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Uses the user interface to ask the player to select a colour
     * @param availableColours all colours sans either of the players' colours
     * @return selected colour
     */
    @Override
    public int makeColourChoice(int[] availableColours) {
        return ui.getChosenColour(availableColours);
    }
}
