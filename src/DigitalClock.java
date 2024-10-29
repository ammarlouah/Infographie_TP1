import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class DigitalClock extends JPanel implements ActionListener {

    private JLabel timeLabel;
    private Timer timer;

    public DigitalClock() {
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel);

        timer = new Timer(1000, this); // Update every second
        timer.start();
    }


    //Méthode pour mettre à jour l'affichage de l'horloge
    private String formatTime(Date date) {
        String timeString = "";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HH:mm:ss"); // Format 24 heures
        timeString = formatter.format(date);
        return timeString;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        timeLabel.setText(formatTime(now));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        //Calculer l'heure actuelle et calculer les angles des aiguilles
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int secondes = calendar.get(java.util.Calendar.SECOND);
        int minutes = calendar.get(java.util.Calendar.MINUTE);
        int heures = calendar.get(java.util.Calendar.HOUR);

        //Calculer les angles des aiguilles (à adapter selon le besoin)
        double angleSeconde = (double) secondes * 6;
        double angleMinute = (double) (minutes + secondes / 60.0) * 6;
        double angleHeure = (double) ((heures % 12 + minutes / 60.0) * 30);

        //Tracer les aiguilles (à implémenter avec les algorithmes de ligne)
        g2d.setColor(Color.WHITE);
        drawHand(g2d, angleSeconde, 10);
        drawHand(g2d, angleMinute, 15);
        drawHand(g2d, angleHeure, 20);


        //Tracer le cadran (cercle avec centre au milieu)
        g2d.setColor(Color.WHITE);
        g2d.drawOval(getWidth() / 2 - 150, getHeight() / 2 - 150, 300, 300); //Cadran
    }


    private void drawHand(Graphics2D g2d, double angle, int length){
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        double radians = Math.toRadians(angle);
        int x2 = (int) Math.round(centerX + length * Math.cos(radians));
        int y2 = (int) Math.round(centerY - length * Math.sin(radians));
        g2d.drawLine(centerX, centerY, x2, y2);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Digital Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new DigitalClock());
        frame.setVisible(true);
    }
}