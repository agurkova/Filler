/**
 * The abstract definition of the game user interface
 */
public interface UserInterface {
    /**
     * Asks user(s) to enter the height and with of the board
     * @return the board dimensions
     */
    Position getBoardSize();

    /**
     * Asks the user(s) how many humans are playing the game (max. 2)
     * @return the number of humans playing
     */
    int getNumberOfHumans();

    /**
     * Asks the player for their name
     * Returns it
     * @param playerNumber the player number
     * @return the player's name
     */
    String getPlayerName(int playerNumber);

    /**
     * Allows the human player(s) to choose their colony's next colour
     * @param availableColours the colours excluding the colour of the current player and the opposing player
     * @return the chosen colour
     */
    int getChosenColour(int[] availableColours);

    /**
     * Updates the game board
     * @param board the game board
     */
    void updateBoard(Board board);

    /**
     * Updates the player's score after their turn
     * @param players the current player
     */
    void updatePlayerInfo(Player[] players);

    /**
     * Announces the outcome of the game
     * @param winner the winner
     * @param loser the loser
     */
    void announceWinner(Player winner, Player loser);
}
