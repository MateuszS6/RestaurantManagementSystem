import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface implements ActionListener {
    private Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JPanel displayPanel;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton analyticsButton;
    private JLabel logo;
    private JLabel poweredBy;

    public MainInterface(Main main) {
        this.main = main;
        this.main.MySetIcon(logo, "images/restaurant-logo.jpeg", -1, 150);
        this.main.MySetIcon(poweredBy, "images/team-icon.png", -1, 30);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
