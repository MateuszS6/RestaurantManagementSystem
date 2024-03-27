import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface implements ActionListener {
    private Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JButton dashboardButton;
    private JButton orderButton;
    private JButton menuButton;
    private JButton tablesButton;
    private JLabel welcome;
    private JLabel time;
    private JLabel logo;
    private JLabel poweredBy;
    private JLabel test;

    public MainInterface(Main main, String user) {
        this.main = main;
        welcome.setText("Welcome, " + user + '!');
        this.main.MySetIcon(logo, "images/restaurant-logo.jpeg", -1, 150);
        this.main.MySetIcon(poweredBy, "images/team-icon.png", -1, 30);

        // Side-panel buttons
        dashboardButton.addActionListener(this);
        orderButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardButton) {
            test.setText("Dashboard");
        } else if (e.getSource() == orderButton) {
            test.setText("Order");
        } else if (e.getSource() == menuButton) {
            test.setText("Menu");
        } else if (e.getSource() == tablesButton) {
            test.setText("Tables");
        }
    }
}
