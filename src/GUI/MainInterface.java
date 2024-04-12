package GUI;

import JDBC.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MainInterface extends MyPanel implements ActionListener {
    private final Main main;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private MyPanel innerContentPanel;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JLabel contentLabel;
    private JButton signOutButton;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton salesButton;
    private JButton tablesButton;
    private JButton staffButton;
    private JButton refreshButton;

    public MainInterface(Main m, String user) {
        super(null, new DBConnection());
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addLabelIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        // Initial content displayed
        switchContentPanel(new MenuPanel(getConnection()));

        // Buttons
        signOutButton.addActionListener(this);
        dashboardButton.addActionListener(this);
        salesButton.addActionListener(this);
        menuButton.addActionListener(this);
        tablesButton.addActionListener(this);
        staffButton.addActionListener(this);
        refreshButton.addActionListener(this);

        // Time
        updateTime();
        new Timer(60000, e -> updateTime()).start();
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
        innerContentPanel = newPanel;
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
        else if (e.getSource() == menuButton) switchContentPanel(new MenuPanel(getConnection()));
        else if (e.getSource() == tablesButton) switchContentPanel(new TablesPanel(getConnection()));
//        else if (e.getSource() == staffButton) switchContentPanel(new BasePanel());
        else if (e.getSource() == refreshButton) {
            reconnect();
            switchContentPanel(innerContentPanel);
        }
    }
}
