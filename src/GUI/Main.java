package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * The Main class represents the main application window for Lancaster's Restaurant Management system.
 * It extends JFrame to create a GUI window and manages the switching of panels within the application.
 */
public class Main extends JFrame {
    /**
     * The width of the main application window.
     */
    public static final int WIDTH = 1000;

    /**
     * The height of the main application window.
     */
    public static final int HEIGHT = 560;

    /**
     * The highlight color used for theme elements in the GUI.
     */
    public static final Color HIGHLIGHT_COLOUR = new Color(0xC3944E); // Theme colour from taken from menu PDF

    /**
     * The primary text color used in the GUI.
     */
    public static final Color PRIMARY_TEXT_COLOUR = new Color(0xFF9B9B9B, true);

    /**
     * The date format used for displaying time in the application.
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE HH:mm"); // Time format

    /**
     * The current panel being displayed in the main application window.
     */
    private JPanel panel; // Current panel

    /**
     * Constructs a new Main object and sets up the main application window.
     * Initializes the window properties, sets the title, and displays the login panel.
     */
    public Main() {
        // Window setup
        setTitle("Lancaster's Restaurant Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        switchPanel(new LoginPage(this)); // Username: Linda    Password: pass
        setLocationRelativeTo(null); // Centre on screen
    }

    /**
     * The entry point of the application.
     * Creates a new Main object to start the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * A custom method for adding an icon image to a JLabel with smooth scaling.
     *
     * @param label    the JLabel to which the icon will be added
     * @param filename the filename of the image to be added
     * @param width    the width of the icon
     * @param height   the height of the icon
     */
    public void addLabelIcon(JLabel label, String filename, int width, int height) {
        label.setText(""); // Remove text
        // Get the image icon using the file name from resources
        label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource('/' + filename)))
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
    }

    /**
     * Switches the current panel displayed in the main application window.
     * Removes the current panel, sets the new panel, and adjusts the window size accordingly.
     *
     * @param newPanel the new panel to be displayed
     */
    protected void switchPanel(MyPanel newPanel) {
        if (panel != null) remove(panel); // Remove current panel if any
        panel = newPanel.getMainPanel(); // Set new panel
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Fit panel to frame size
        add(panel); // Add panel to frame
        pack(); // Size frame to match panel size
    }
}
