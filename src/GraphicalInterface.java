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

    @Override
    public int getChosenColour(int[] availableColours) {
        return 0;
    }

    @Override
    public void updateBoard(Board board) {
        boardPanel.setGameBoard(board);
        boardPanel.repaint();
    }

    @Override
    public void updatePlayerInfo(Player[] players) {

    }

    @Override
    public void announceWinner(Player winner, Player loser) {

    }
}
