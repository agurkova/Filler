public class Game {

    private Board board;

    public Game() {

    }

    public void play() {
        generateBoard();
        assignBeginningPositions();

        while (!isGameOver()) {
            turn(playerOne);
            if (!isGameOver()) {
                turn(playerTwo);
            }
        }

        announceWinner();
    }

    private void generateBoard() {
        board = new Board(30, 22);
    }
}
