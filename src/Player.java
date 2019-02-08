import java.util.Random;

public class Player {

    private Position startingPosition;
    private int colour;
    private int score;

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int makeColourChoice(int[] availableColours) {
        return availableColours[new Random().nextInt(5)];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
