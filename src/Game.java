import java.util.Random;

public class Game {

    private Board board;
    private Player[] players;
    private UserInterface ui;

    public Game() {
        ui = new TextInterface();
        players = new Player[2];
        players[0] = new HumanPlayer(ui);
        players[1] = new HumanPlayer(ui);
    }

    public void play() {
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

    private boolean isGameOver() {
        return (players[0].getScore() + players[1].getScore() == board.getHeight() * board.getWidth());
    }

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

    private void generateBoard() {
        board = new Board(6, 7, 7);
    }

    public static void main(String[] args) {
        Game filler = new Game();
        filler.play();
        System.out.println(filler.board);
    }

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
