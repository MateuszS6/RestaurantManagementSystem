import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Main extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    private JPanel panel;

    public Main() {
        // Window setup
        setTitle("Lancaster's Restaurant Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        panel = new LoginPage(this).getMainPanel();
//        panel = new MainInterface(this, "DEV").getMainPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(panel);
        pack();
        setLocationRelativeTo(null); // Centre on screen
    }

    public static void main(String[] args) {
        new Main();
    }

    public void switchPanel(JPanel newPanel) {
        remove(panel);
        panel = newPanel;
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);
        pack();
    }

    public void addIcon(JLabel label, String filename, int width, int height) {
        label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource('/' + filename)))
                .getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
    }
}
