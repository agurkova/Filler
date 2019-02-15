import java.util.Random;

/**
 * Controls the game flow and interacts with the players via a user interface
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class Game {

    private Board board;
    private Player[] players;
    private UserInterface ui;

    /**
     * Creates a new Game and initialises the user interface
     */
    public Game() {
        ui = new GraphicalInterface();
        players = new Player[2];
        players[0] = new RandomPlayer();
        players[1] = new RandomPlayer();
    }

    /**
     * Launches the game
     * @param args Command line arguments (they are not used)
     */
    public static void main(String[] args) {
        Game filler = new Game();
        filler.play();
    }

    /**
     * The main game loop
     */
    public void play()
    {
        boolean continuePlaying = true;
        while (continuePlaying) {
            choosePlayers();
            generateBoard();
            assignBeginningPositions();
            ui.updatePlayerInfo(players);
            ui.updateBoard(board);

        for (int i = 0; !isGameOver(); i++) {
            turn(players[i % 2]);
            ui.updatePlayerInfo(players);
            ui.updateBoard(board);
        }

        announceWinner();
    }

    /**
     * Computes and announces the winner and loser
     */
    private void announceWinner() {
        Player winner;
        Player loser;

        if (players[0].getScore() > players[1].getScore()) {
            winner = players[0];
            loser = players[1];
        } else {
            winner = players[1];
            loser = players[0];
        }

        ui.announceWinner(winner, loser);
    }

    /**
     * Checks if all the Hexes have been colonised
     * @return true if there are no more unclaimed Hexes
     */
    private boolean isGameOver() {
        return (players[0].getScore() + players[1].getScore() == board.getHeight() * board.getWidth());
    }

    /**
     * Gives a random starting position to each player
     * Performs 'the first turn' on their behalf
     */
    private void assignBeginningPositions() {
        // should only place player in a range of 4 rows away from the wall closest to the player
        Random randomStartPos = new Random();
        int startRow = randomStartPos.nextInt(4);
        int startCol = randomStartPos.nextInt(board.getWidth());

        Position startingPositionOne = new Position(startRow, startCol);
        players[0].setStartingPosition(startingPositionOne);
        Hex startingHex = board.getHex(startingPositionOne);
        startingHex.setTeam(players[0]);
        players[0].setColour(startingHex.getColour());

        Position startingPositionTwo = new Position(board.getHeight()-1-startRow, board.getWidth()-1-startCol);
        players[1].setStartingPosition(startingPositionTwo);
        startingHex = board.getHex(startingPositionTwo);
        startingHex.setTeam(players[1]);
        players[1].setColour(startingHex.getColour());

        int score = board.colonise(players[0], players[0].getColour());
        players[0].setScore(score);
        score = board.colonise(players[1], players[1].getColour());
        players[1].setScore(score);
    }

    /**
     * Prepares the game board by asking for the size and generating it
     */
    private void generateBoard() {
        board = new Board(6, 7, 7);
    }

    /**
     * Allows player to choose colour out of unused colours
     * Executes the turn
     * @param player the current player
     */
    public void turn(Player player) {
        int[] availableColours = new int[5];

        for (int colour = 0, i = 0; colour < 7; colour++) {
            if (colour != players[0].getColour() && colour != players[1].getColour()) {
                availableColours[i++] = colour;
            }
        }

        int chosenColour = player.makeColourChoice(availableColours);
        int score = board.colonise(player, chosenColour);
        player.setColour(chosenColour);
        player.setScore(score);
    }
}
