import java.util.Random;

public class RandomPlayer extends Player {
    public int makeColourChoice(int[] availableColours) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return availableColours[new Random().nextInt(5)];
    }
}
