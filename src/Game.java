import java.util.Random;

public class Game {

    private Board board;
    private Player[] players;

    public Game() {
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
    }

    public void play() {
        generateBoard();
        assignBeginningPositions();
//
//        while (!isGameOver()) {
//            turn(playerOne);
//            if (!isGameOver()) {
//                turn(playerTwo);
//            }
//        }
//
//        announceWinner();
    }

    private void assignBeginningPositions() {
        // should only place player in a range of 4 rows away from the wall closest to the player
        Random randomStartPos = new Random();
        int startRow = randomStartPos.nextInt(4);
        int startCol = randomStartPos.nextInt(board.getWidth());
        players[0].setStartingPosition(new Position(startRow, startCol));
        players[1].setStartingPosition(new Position(board.getHeight()-1-startRow, board.getWidth()-1-startCol));
    }

    private void generateBoard() {
        board = new Board(6, 7, 7);
    }

    public static void main(String[] args) {
        Game filler = new Game();
        filler.play();
        System.out.println(filler.board);
    }
}
