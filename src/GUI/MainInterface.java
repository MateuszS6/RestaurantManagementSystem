package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MainInterface implements MyPanel, ActionListener {
    private final Main main;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JButton signOutButton;
    private JButton dashboardButton;
    private JButton menuButton;
    private JButton orderButton;
    private JButton tablesButton;
    private JButton employeesButton;

    public MainInterface(Main m, String user) {
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addLabelIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        contentPanel = new MenuPanel().getMainPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

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

    public void updateTime() {
        String time = Main.DATE_FORMAT.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    public void switchContentPanel(MyPanel newPanel) {
        mainPanel.remove(contentPanel);
        contentPanel = newPanel.getMainPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        main.revalidate();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signOutButton) main.switchPanel(new LoginPage(main));
        else if (e.getSource() == dashboardButton) switchContentPanel(new TestPanel());
        else if (e.getSource() == orderButton) switchContentPanel(new TestPanel());
        else if (e.getSource() == menuButton) switchContentPanel(new MenuPanel());
        else if (e.getSource() == tablesButton) switchContentPanel(new TestPanel());
        else if (e.getSource() == employeesButton) switchContentPanel(new TestPanel());
    }
}
