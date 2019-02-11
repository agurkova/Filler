import java.util.Scanner;

public class TextInterface implements UserInterface {
    private Scanner scanner = new Scanner(System.in);

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

    @Override
    public void updateBoard(Board board) {
        System.out.println(board);
    }

    @Override
    public void updatePlayerInfo(Player[] players) {
        System.out.println(players[0]);
        System.out.println(players[1]);
    }

    @Override
    public void announceWinner(Player winner, Player loser) {
        System.out.println("GAME OVER!!");
        System.out.println("The winner is: " + winner + " :)");
        System.out.println("The loser is: " + loser + " :(");
    }
}
