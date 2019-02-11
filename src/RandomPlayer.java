import java.util.Random;

public class RandomPlayer extends Player {
    public int makeColourChoice(int[] availableColours) {
        return availableColours[new Random().nextInt(5)];
    }
}
