package GUI;

import JDBC.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MainInterface extends MyPanel implements ActionListener {
    private final Main main;
    private final DBConnection connection;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JButton signOutButton;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton salesButton;
    private JButton tablesButton;
    private JButton staffButton;
    private JLabel contentLabel;

    public MainInterface(Main m, String user) {
        main = m;
        connection = new DBConnection();
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addLabelIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        switchContentPanel(new TablesPanel(connection));

        // Buttons
        signOutButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        salesButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);
        staffButton.addActionListener(this);

        updateTime();
        Timer timer = new Timer(60000, e -> updateTime());
        timer.start();
    }

    public void updateTime() {
        String time = Main.DATE_FORMAT.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    public void switchContentPanel(MyPanel newPanel) {
        contentLabel.setText(newPanel.getTitle());
        contentPanel.removeAll();
        contentPanel.add(newPanel.getMainPanel());
        main.revalidate();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) main.switchPanel(new LoginPage(main));
//        else if (e.getSource() == dashboardButton) switchContentPanel(new BasePanel());
//        else if (e.getSource() == salesButton) switchContentPanel(new BasePanel());
        else if (e.getSource() == menuButton) switchContentPanel(new MenuPanel(connection));
        else if (e.getSource() == tablesButton) switchContentPanel(new TablesPanel(connection));
//        else if (e.getSource() == staffButton) switchContentPanel(new BasePanel());
    }
}
