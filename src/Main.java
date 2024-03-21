import javax.swing.*;
import java.awt.*;

public class Main {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    private JPanel panel;

    public Main() {
        // Window setup
        JFrame window = new JFrame("Lancaster's Restaurant Management");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        panel = new LoginPage().getMainPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null); // Centre on panel
    }

    public static void main(String[] args) {
        new Main();
    }
}
