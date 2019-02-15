import javax.swing.*;
import java.awt.*;

/**
 * Graphical representation of the game board
 *
 * @author (Alice Gurkova)
 * @version (02/14/2019)
 */
public class BoardPanel extends JPanel {

    private static final double SIN60 = Math.sin(Math.PI / 3);
    private Board gameBoard;

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        if (gameBoard == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        double hexSide = (width / 3.0) / gameBoard.getWidth();
        double [] xpointsDouble = {-1, -0.5, 0.5, 1, 0.5, -0.5};
        double [] ypointsDouble = {0, -SIN60, -SIN60, 0, SIN60, SIN60};
        int [] xpoints = new int[xpointsDouble.length];

        // Scale the standard-sized Hex to the screen dimensions
        for (int i = 0; i < xpoints.length; i++) {
            xpoints[i] = (int) (hexSide * xpointsDouble[i]);
        }
        int [] ypoints = new int[ypointsDouble.length];
        for (int i = 0; i < ypoints.length; i++) {
            ypoints[i] = (int) (hexSide * ypointsDouble[i]);
        }

        // Move the Polygon (which represents the Hex) into each of the Hex locations
        //  ...and draw the Polygon with the colour of the Hex
        for (int r = 0; r < gameBoard.getHeight(); r++) {
            for (int c = 0; c < gameBoard.getWidth(); c++) {
                Hex hex = gameBoard.getHex(new Position(r, c));
                int cx = (int) (hexSide * (c * 3  + 1 + (r % 2) * 1.5));
                int cy = (int) ((r + 1) * SIN60 * hexSide);
                g.setColor(GraphicalInterface.COLOURS[hex.getColour()]);
                Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
                p.translate(cx, cy);
                graphics.fillPolygon(p);
            }
        }

        // draw the outline of each of the Hexes with a 2-pixel brush/line
        graphics.setColor(Color.black);
        if (graphics instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) graphics;
            Stroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke);
        }
        for (int r = 0; r < gameBoard.getHeight(); r++) {
            for (int c = 0; c < gameBoard.getWidth(); c++) {
                int cx = Math.toIntExact(Math.round(hexSide * (c * 3 + 1.5 + (r % 2) * 1.5)));
                int cy = Math.toIntExact(Math.round(((r + 1) * SIN60 + 0.5) * hexSide));
                Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
                p.translate(cx, cy);
                graphics.drawPolygon(p);
            }
        }

        // if there are players then draw their positions
        if (players == null) {
            return;
        }
        for (int i = 0; i < players.length; i++) {
            Position startingPosition = players[i].getStartingPosition();
            int r = startingPosition.getRow();
            int c = startingPosition.getCol();
            int cx = (int) (hexSide * (c * 3  + 1.5 + (r % 2) * 1.5));
            int cy = (int) (((r + 1) * SIN60 + 0.5) * hexSide);
            graphics.setColor(GraphicalInterface.COLOURS[(players[i].getColour() + 1) % GraphicalInterface.COLOURS.length]);
            graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 16));
            graphics.drawChars(Integer.toString(i + 1).toCharArray(), 0, 1, cx, cy);
        }


    }

    /**
     * Updates the reference of the game board to be drawn in the next cycle
     * @param gameBoard an instance of the Board class (it is the actual game board)
     */
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Updates the references of the players to be used when drawing the board
     * @param players Array of players
     */
    public void setPlayers(Player[] players)
    {
        this.players = players;
    }
}
