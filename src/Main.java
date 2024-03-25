import javax.swing.*;
import java.awt.*;

public class Main {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 560;
    private JFrame window;
    private JPanel panel;

    public Main() {
        // Window setup
        window = new JFrame("Lancaster's Restaurant Management");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
//        panel = new LoginPage(this).getMainPanel();
        panel = new MainInterface(this).getMainPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null); // Centre on panel
    }

    public static void main(String[] args) {
        new Main();
    }

    public void switchPanel(JPanel newPanel) {
        window.remove(panel);
        panel = newPanel;
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(panel);
        window.pack();
    }

    public void MySetIcon(JLabel label, String filename, int width, int height) {
        label.setIcon(new ImageIcon(new ImageIcon(filename).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH)));
    }
}
