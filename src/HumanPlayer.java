public class HumanPlayer extends Player {
    private UserInterface ui;

    public HumanPlayer(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public int makeColourChoice(int[] availableColours) {
        return ui.getChosenColour(availableColours);
    }
}
