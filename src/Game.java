public class Game {

    private Board board;

    public Game() {

    }

    public void play() {
        generateBoard();
//        assignBeginningPositions();
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

    private void generateBoard() {
        board = new Board(6, 7, 7);
    }

    public static void main(String[] args) {
        Game filler = new Game();
        filler.play();
        System.out.println(filler.board);
    }
}
