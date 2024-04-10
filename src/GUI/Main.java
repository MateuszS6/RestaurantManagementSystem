package GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Main extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    public static final Color PRIMARY_TEXT_COLOUR = new Color(0xFF777777, true);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE HH:mm");
    private JPanel panel;

    public Main() {
        // Window setup
        setTitle("Lancaster's Restaurant Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

//        panel = new LoginPage(this).getMainPanel();
        panel = new MainInterface(this, null).getMainPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);
        pack();
        setLocationRelativeTo(null); // Centre on screen
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * A custom method for adding an icon image to a JLabel with smooth scaling
     */
    public void addLabelIcon(JLabel label, String filename, int width, int height) {
        label.setText("");
        label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource('/' + filename)))
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
    }

    public void switchPanel(MyPanel newPanel) {
        remove(panel);
        panel = newPanel.getMainPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);
        revalidate();
    }
}
