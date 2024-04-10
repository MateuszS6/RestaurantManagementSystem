import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainInterface implements MyPanel, ActionListener {
    private Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JLabel contentLabel;
    private JButton signOutButton;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton orderButton;
    private JButton tablesButton;
    private JButton employeesButton;
    private JPanel contentPanel;

    public MainInterface(Main m, String user) {
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        logoLabel.setText("");
        poweredByLabel.setText("");
        main.addIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addIcon(poweredByLabel, "team-icon.png", -1, 30);
        contentLabel.setText("Dashboard");

        // Buttons
        signOutButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        orderButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);
        employeesButton.addActionListener(this);

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

    public void switchContentPanel(JPanel newPanel) {
        contentPanel.remove(contentPanel);
        contentPanel.add(newPanel);
        main.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) {
            main.switchPanel(new LoginPage(main));
        } else if (e.getSource() == dashboardButton) {
            contentLabel.setText("Dashboard");
//            switchContentPanel(new Dashboard());
        } else if (e.getSource() == orderButton) {
            contentLabel.setText("Order");
        } else if (e.getSource() == menuButton) {
            contentLabel.setText("Menu");
        } else if (e.getSource() == tablesButton) {
            contentLabel.setText("Tables");
        } else if (e.getSource() == employeesButton) {
            contentLabel.setText("Employees");
        }
    }
}
