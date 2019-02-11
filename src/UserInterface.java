public interface UserInterface {
    int getChosenColour(int[] availableColours);
    void updateBoard(Board board);
    void updatePlayerInfo(Player[] players);
    void announceWinner(Player winner, Player loser);
}
