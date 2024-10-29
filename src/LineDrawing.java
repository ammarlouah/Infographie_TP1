import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class LineDrawing extends JPanel {

    private int startX, startY, endX, endY;

    public LineDrawing(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Méthode de Bresenham
        bresenhamLine(g2d, startX, startY, endX, endY);


        //Méthode Polynomiale
        polynomialLine(g2d, startX, startY, endX, endY);


    }

    private void bresenhamLine(Graphics2D g2d, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = (dx > dy ? dx : -dy) / 2;

        while (true) {
            g2d.fillRect(x0, y0, 1, 1); // Dessine un pixel
            if (x0 == x1 && y0 == y1) break;
            int e2 = err;
            if (e2 > -dx) { err -= dy; x0 += sx; }
            if (e2 < dy) { err += dx; y0 += sy; }
        }
    }



    private void polynomialLine(Graphics2D g2d, int x0, int y0, int x1, int y1){
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        double m = (double) (y1 - y0) / (x1 - x0);
        double b = y0 - m * x0;

        int x,y;
        int steps = Math.max(dx,dy);


        double xStep = (double) (x1-x0)/ steps;
        double yStep = m*xStep;


        for (x = x0, y = y0; x!=x1 || y!=y1; x = (int)Math.round(x + xStep) , y = (int)Math.round(y + yStep)){
            g2d.fillRect((int)Math.round(x), (int)Math.round(y), 1, 1);

        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Line Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        LineDrawing panel = new LineDrawing(50, 50, 350, 350); // Ligne diagonale
        panel.setPreferredSize(new Dimension(400, 400)); // Taille du panel
        frame.add(panel);

        frame.setVisible(true);
    }
}