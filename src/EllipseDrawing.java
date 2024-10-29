import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class EllipseDrawing extends JPanel {

    private int centerX, centerY, a, b;

    public EllipseDrawing(int centerX, int centerY, int a, int b) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.a = a;
        this.b = b;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Méthode trigonométrique
        drawEllipseTrigonometric(g2d, centerX, centerY, a, b);

        // Méthode polynomiale (optionnel)
        //drawEllipsePolynomial(g2d, centerX, centerY, a, b);
    }

    private void drawEllipseTrigonometric(Graphics2D g2d, int xc, int yc, int a, int b) {
        for (double angle = 0; angle < Math.PI * 2; angle += 0.01) { // Pas plus petit pour plus de précision
            double x = a * Math.cos(angle) + xc;
            double y = b * Math.sin(angle) + yc;
            g2d.fillRect((int) Math.round(x), (int) Math.round(y), 1, 1);

            //Dessiner les quatres symétries
            g2d.fillRect((int) Math.round(-x + 2 * xc), (int) Math.round(y), 1, 1);
            g2d.fillRect((int) Math.round(x), (int) Math.round(-y + 2 * yc), 1, 1);
            g2d.fillRect((int) Math.round(-x + 2 * xc), (int) Math.round(-y + 2 * yc), 1, 1);
        }
    }

    // Méthode polynomiale (optionnel) :
    /*private void drawEllipsePolynomial(Graphics2D g2d, int xc, int yc, int a, int b) {
        for (double x = -a; x <= a; x += 0.1) { // Ajuster le pas pour plus de précision
            double y = Math.sqrt(b * b * (1 - (x * x) / (a * a))) + yc;
            g2d.fillRect((int) Math.round(x + xc), (int) Math.round(y), 1, 1);
            g2d.fillRect((int) Math.round(-x + xc), (int) Math.round(y), 1, 1);
        }
    }*/


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ellipse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        EllipseDrawing panel = new EllipseDrawing(200, 200, 150, 80); // Changez a, b pour différentes formes d'ellipse
        panel.setPreferredSize(new Dimension(400, 400));
        frame.add(panel);

        frame.setVisible(true);
    }
}