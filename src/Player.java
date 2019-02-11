import java.util.Random;

public abstract class Player {

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

    public abstract int makeColourChoice(int[] availableColours);

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "startingPosition=" + startingPosition +
                ", colour=" + colour +
                ", score=" + score +
                '}';
    }
}
