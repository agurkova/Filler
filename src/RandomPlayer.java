import java.util.Random;

/**
 * A computer player that chooses its colours at random
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class RandomPlayer extends Player {

    /**
     * Makes a random selection of a colour
     * Pretends to 'think' for one second
     * @param availableColours all colours sans either of the players' colours
     * @return the randomly selected colour
     */
    public int makeColourChoice(int[] availableColours) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return availableColours[new Random().nextInt(5)];
    }
}
