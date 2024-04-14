package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MainInterface extends MyPanel implements ActionListener {
    private final Main main;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private MyPanel innerContentPanel;
    private JLabel welcomeLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JLabel poweredByLabel;
    private JLabel contentLabel;
    private JButton signOutButton;
    private JButton restaurantButton;
    private JButton menuButton;
    private JButton inventoryButton;
    private JButton staffButton;
    private JButton salesButton;
    private JButton selectedButton;
    private JButton refreshButton;

    public MainInterface(Main m, String user) {
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addLabelIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        // Initial content displayed
        connect();
        switchContentPanel(new SalesPanel(this), salesButton);

        // Buttons
        signOutButton.addActionListener(this);
        restaurantButton.addActionListener(this);
        menuButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        staffButton.addActionListener(this);
        salesButton.addActionListener(this);
        refreshButton.addActionListener(this);

        // Time
        updateTime();
        new Timer(60000, e -> updateTime()).start();
    }

    private void createUIComponents() {
        contentPanel = new JPanel(new GridLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == signOutButton) main.switchPanel(new LoginPage(main));
        else if (button == restaurantButton) switchContentPanel(new RestaurantPanel(this), restaurantButton);
        else if (button == menuButton) switchContentPanel(new MenuPanel(this), menuButton);
        else if (button == inventoryButton) switchContentPanel(new InventoryPanel(this), inventoryButton);
        else if (button == staffButton) switchContentPanel(new StaffPanel(this), staffButton);
        else if (button == salesButton) switchContentPanel(new SalesPanel(this), salesButton);
        else if (button == refreshButton) reconnect();
        if (button.getParent() == sidePanel) updateSideButtons(button);
    }

    private void updateTime() {
        String time = Main.DATE_FORMAT.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    private void switchContentPanel(MyPanel newPanel, JButton button) {
        contentPanel.removeAll();
        innerContentPanel = newPanel;
        contentPanel.add(innerContentPanel.getMainPanel());
        main.revalidate();

        // Visuals
        contentLabel.setText(newPanel.getTitle());
        updateSideButtons(button);
    }

    private void updateSideButtons(JButton newButton) {
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(0x3E4649));
            selectedButton.setForeground(new Color(0xC0C0C0));
        }
        selectedButton = newButton;
        selectedButton.setBackground(new Color(0xC3944E));
        selectedButton.setForeground(Color.WHITE);
    }

    private void reconnect() {
        connect();
        if (innerContentPanel instanceof RestaurantPanel) switchContentPanel(new RestaurantPanel(this), restaurantButton);
        else if (innerContentPanel instanceof MenuPanel) switchContentPanel(new MenuPanel(this), menuButton);
        else if (innerContentPanel instanceof InventoryPanel) switchContentPanel(new InventoryPanel(this), inventoryButton);
        else if (innerContentPanel instanceof StaffPanel) switchContentPanel(new StaffPanel(this), staffButton);
        else if (innerContentPanel instanceof SalesPanel) switchContentPanel(new SalesPanel(this), salesButton);
        System.out.println("Connection refreshed");
    }
}
