import javax.swing.*;
import java.awt.*;

public class GraphicalInterface implements UserInterface {

    public static final Color[] COLOURS = {Color.red, Color.green, Color.blue, Color.yellow, Color.white, Color.magenta, Color.orange};
    private final JLabel playerOneInfo;
    private final BoardPanel boardPanel;

    public GraphicalInterface() {
        JFrame mainWindow = new JFrame();

        //make sure the program exits when the frame closes
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Hex Virus");
        mainWindow.setSize(700,600);

        //This will center the JFrame in the middle of the screen
        mainWindow.setLocationRelativeTo(null);
        JPanel infoPanel = new JPanel();
        playerOneInfo = new JLabel("Player One");
        infoPanel.add(playerOneInfo);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        boardPanel = new BoardPanel();
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        JPanel colourPanel = new JPanel();

        mainWindow.add(infoPanel, BorderLayout.NORTH);
        mainWindow.add(mainPanel, BorderLayout.CENTER);
        mainWindow.add(colourPanel, BorderLayout.SOUTH);

        mainWindow.setVisible(true);

    }

    /**
     * Presents the user with a selection of board sizes
     * Chosen size is returned to the game (the dimensions)
     * @return the chosen size's dimensions
     */
    @Override
    public Position getBoardSize()
    {
        String [] modes = new String[] {"Small", "Medium", "Large", "XL", "XXL"};
        Position [] sizes = new Position[] {
                new Position(16, 10),
                new Position(30, 20),
                new Position(40, 30),
                new Position(60, 40),
                new Position(80, 50),
                new Position(90, 60),
        };
        while (true) {
            String gameMode = (String) JOptionPane.showInputDialog(
                    JOptionPane.getRootFrame(),
                    "Select Board Size:",
                    "Board Size",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    modes,
                    modes[2]);
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].equals(gameMode)) {
                    return sizes[i];
                }
            }
        }
    }

    /**
     * Allows the user to choose the game mode
     * @return the number of human players
     */
    @Override
    public int getNumberOfHumans()
    {
        String [] modes = new String[] {"Computer vs. Computer", "Human vs. Computer", "Human vs. Human"};
        while (true) {
            String gameMode = (String) JOptionPane.showInputDialog(
                    JOptionPane.getRootFrame(),
                    "Select Players:",
                    "Game Mode",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    modes,
                    modes[2]);
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].equals(gameMode)) {
                    return i;
                }
            }
        }
    }

    /**
     * Asks the player for their name
     * Returns it
     * @param playerNumber the player number
     * @return the player's name
     */
    @Override
    public String getPlayerName(int playerNumber)
    {
        return JOptionPane.showInputDialog(String.format("What is player's %s name?", playerNumber + 1));
    }

    /**
     * Allows the human player(s) to choose their colony's next colour
     * Enables the colour selection buttons for availableColours
     * Waits for one of the buttons to be pressed
     * @param availableColours the colours excluding the colour of the current player and the opposing player
     * @return the chosen colour
     */
    @Override
    public int getChosenColour(int[] availableColours) {
        return 0;
    }

    /**
     * Redraws the board after a player's turn
     * @param board the game board
     */
    @Override
    public void updateBoard(Board board) {
        boardPanel.setGameBoard(board);
        boardPanel.repaint();
    }

    /**
     * Updates the player's score after their turn
     * @param players the current player
     */
    @Override
    public void updatePlayerInfo(Player[] players) {

    }

    /**
     * Checks if there was a draw
     * Calculates the totalScore in order to calculate each player's percentages of colonisation
     * Shows the outcome on the screen
     * @param winner the winner
     * @param loser the loser
     */
    @Override
    public void announceWinner(Player winner, Player loser) {

    }
}
