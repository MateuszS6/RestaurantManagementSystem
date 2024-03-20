import javax.swing.*;
import java.awt.*;

public class Main {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    private JPanel screen;

    public Main() {
        // Window setup
        JFrame window = new JFrame("Restaurant Management");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        screen = new AdminInterface().getMainPanel();
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(screen);
        window.pack();
        window.setLocationRelativeTo(null); // Centre on screen
    }

    public static void main(String[] args) {
        new Main();
    }
}
