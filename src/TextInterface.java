import java.util.Scanner;

/**
 * The Text User Interface for the game
 */
public class TextInterface implements UserInterface {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Asks user(s) to enter the height and with of the board
     * @return the board dimensions
     */
    @Override
    public Position getBoardSize()
    {
        int width;
        int height;
        while (true) {
            System.out.print("Please enter the board width (10-60: ");
            width = scanner.nextInt();
            if (width >= 10 && width <= 60) {
                break;
            }
            System.out.println("Invalid board width. Try again.");
        }
        while (true) {
            System.out.print("Please enter the board height (10-60: ");
            height = scanner.nextInt();
            if (height >= 10 && height <= 60) {
                break;
            }
            System.out.println("Invalid board height. Try again.");
        }
        return new Position(height, width);
    }

    /**
     * Asks the user(s) how many humans are playing the game (max. 2)
     * @return the number of humans playing
     */
    @Override
    public int getNumberOfHumans()
    {
        while (true) {
            System.out.print("Please enter the number of human players (0 - 2): ");
            int numberOfHumans = scanner.nextInt();
            if (numberOfHumans >= 0 && numberOfHumans <= 2) {
                return numberOfHumans;
            }
            System.out.println("Invalid number of human players. Please try again.");
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
        System.out.print(String.format("Enter the name of player #%s: ", playerNumber + 1));
        return scanner.next();
    }

    /**
     * Allows the human player(s) to choose their colony's next colour
     * @param availableColours the colours excluding the colour of the current player and the opposing player
     * @return the chosen colour
     */
    @Override
    public int getChosenColour(int[] availableColours) {
        while (true) {
            System.out.print("Please pick a colour: ");
            for (int curColour : availableColours) {
                System.out.print(curColour + " ");
            }
            System.out.println();

            int chosenColour = scanner.nextInt();
            for (int curColour : availableColours) {
                if (chosenColour == curColour) {
                    return chosenColour;
                }
            }

            System.out.println("Invalid choice!");
        }
    }

    /**
     * Prints the game board
     * @param board the game board
     */
    @Override
    public void updateBoard(Board board) {
        System.out.println(board);
    }

    /**
     * Prints the player's score after their turn
     * @param players the current player
     */
    @Override
    public void updatePlayerInfo(Player[] players) {
        System.out.println(players[0]);
        System.out.println(players[1]);
    }

    /**
     * Prints the outcome of the game
     * @param winner the winner
     * @param loser the loser
     */
    @Override
    public void announceWinner(Player winner, Player loser) {
        System.out.println("GAME OVER!!");
        System.out.println("The winner is: " + winner + " :)");
        System.out.println("The loser is: " + loser + " :(");
    }
}
