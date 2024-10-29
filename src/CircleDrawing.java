import javax.swing.*;
import java.awt.*;

public class CircleDrawing extends JPanel {

    private int centerX, centerY, radius;

    public CircleDrawing(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Méthode de Bresenham pour le cercle
        drawCircleBresenham(g2d, centerX, centerY, radius);

        // Méthode trigonométrique (optionnel)
        //drawCircleTrigonometric(g2d, centerX, centerY, radius);

    }

    private void drawCircleBresenham(Graphics2D g2d, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        plotCirclePoints(g2d, xc, yc, x, y);

        while (y >= x) {
            x++;
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                y--;
                d = d + 4 * (x - y) + 10;
            }
            plotCirclePoints(g2d, xc, yc, x, y);
        }
    }

    private void plotCirclePoints(Graphics2D g2d, int xc, int yc, int x, int y) {
        g2d.fillRect(xc + x, yc + y, 1, 1);
        g2d.fillRect(xc - x, yc + y, 1, 1);
        g2d.fillRect(xc + x, yc - y, 1, 1);
        g2d.fillRect(xc - x, yc - y, 1, 1);
        g2d.fillRect(xc + y, yc + x, 1, 1);
        g2d.fillRect(xc - y, yc + x, 1, 1);
        g2d.fillRect(xc + y, yc - x, 1, 1);
        g2d.fillRect(xc - y, yc - x, 1, 1);
    }


    // Méthode trigonométrique (optionnel):
    /*private void drawCircleTrigonometric(Graphics2D g2d, int xc, int yc, int r) {
        for (double angle = 0; angle < Math.PI * 2; angle += 0.01) { // Ajuster le pas pour plus de précision
            int x = (int) (r * Math.cos(angle)) + xc;
            int y = (int) (r * Math.sin(angle)) + yc;
            g2d.fillRect(x, y, 1, 1);
        }
    }*/


    public static void main(String[] args) {
        JFrame frame = new JFrame("Cercle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        CircleDrawing panel = new CircleDrawing(200, 200, 100);
        panel.setPreferredSize(new Dimension(400, 400));
        frame.add(panel);

        frame.setVisible(true);
    }
}