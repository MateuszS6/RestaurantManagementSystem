import javax.swing.*;

public class UserInterface {
    private JPanel mainPanel;

    public UserInterface() {
        // Window setup
        JFrame window = new JFrame("Restaurant Management");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1296, 759);
        window.setLocationRelativeTo(null); // Centre on screen
        window.setResizable(false);
        window.setVisible(true);
        window.add(mainPanel);
    }
}
