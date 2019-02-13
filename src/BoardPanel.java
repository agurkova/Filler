import javax.swing.*;
import java.awt.*;

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
        for (int i = 0; i < xpoints.length; i++) {
            xpoints[i] = (int) (hexSide * xpointsDouble[i]);
        }
        int [] ypoints = new int[ypointsDouble.length];
        for (int i = 0; i < ypoints.length; i++) {
            ypoints[i] = (int) (hexSide * ypointsDouble[i]);
        }

        for (int r = 0; r < gameBoard.getHeight(); r++) {
            for (int c = 0; c < gameBoard.getWidth(); c++) {
                Hex hex = gameBoard.getHex(new Position(r, c));
                int cx = (int) (hexSide * (c * 3  + 1 + (r % 2) * 1.5));
                int cy = (int) ((r + 1) * SIN60 * hexSide);
                g.setColor(GraphicalInterface.COLOURS[hex.getColour()]);
                Polygon p = new Polygon(xpoints, ypoints, xpoints.length);
                p.translate(cx, cy);
                g.fillPolygon(p);
            }
        }

    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }


}
