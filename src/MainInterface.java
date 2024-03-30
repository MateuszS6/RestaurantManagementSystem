import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainInterface implements ActionListener {
    private Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JLabel welcomeLabel;
    private JButton signOutButton;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton orderButton;
    private JButton tablesButton;
    private JLabel poweredByLabel;
    private JLabel test;

    public MainInterface(Main m, String user) {
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addIcon(poweredByLabel, "team-icon.png", -1, 30);

        // Buttons
        signOutButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        orderButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);

        updateTime();
        Timer timer = new Timer(60000, e -> updateTime());
        timer.start();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE HH:mm");
        String time = dateFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) {
            main.switchPanel(new LoginPage(main).getMainPanel());
        } else if (e.getSource() == dashboardButton) {
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
