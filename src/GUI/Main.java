package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Main extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    public static final Color HIGHLIGHT_COLOUR = new Color(0xC3944E); // Theme colour from taken from menu PDF
    public static final Color PRIMARY_TEXT_COLOUR = new Color(0xFF9B9B9B, true);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE HH:mm"); // Time format
    private JPanel panel; // Current panel

    public Main() {
        // Window setup
        setTitle("Lancaster's Restaurant Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        switchPanel(new LoginPage(this)); // Username: Linda    Password: pass
        setLocationRelativeTo(null); // Centre on screen
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * A custom method for adding an icon image to a JLabel with smooth scaling
     */
    public void addLabelIcon(JLabel label, String filename, int width, int height) {
        label.setText(""); // Remove text
        // Get the image icon using the file name from resources
        label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource('/' + filename)))
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
    }

    protected void switchPanel(MyPanel newPanel) {
        if (panel != null) remove(panel); // Remove current panel if any
        panel = newPanel.getMainPanel(); // Set new panel
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Fit panel to frame size
        add(panel); // Add panel to frame
        pack(); // Size frame to match panel size
    }
}
