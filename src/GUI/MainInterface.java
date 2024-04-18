package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * The MainInterface class represents the main interface of the Lancaster's Restaurant Management system.
 * It displays various functionality panels and allows users to navigate between them.
 * The class implements ActionListener interface to handle button clicks.
 */
public class MainInterface extends MyPanel implements ActionListener {
    /**
     * The main application instance.
     */
    private final Main main;

    /**
     * The main JPanel associated with the main interface.
     */
    private JPanel mainPanel;

    /**
     * The side panel for displaying navigation buttons.
     */
    private JPanel sidePanel;

    /**
     * The content panel for displaying functionality panels.
     */
    private JPanel contentPanel;

    /**
     * The current inner content panel being displayed.
     */
    private MyPanel innerContentPanel;

    /**
     * The label to display a welcome message.
     */
    private JLabel welcomeLabel;

    /**
     * The label to display the current time.
     */
    private JLabel timeLabel;

    /**
     * The label to display the restaurant logo.
     */
    private JLabel logoLabel;

    /**
     * The label to display powered by information.
     */
    private JLabel poweredByLabel;

    /**
     * The label to display the title of the content panel.
     */
    private JLabel contentLabel;

    /**
     * The button to sign out from the system.
     */
    private JButton signOutButton;

    /**
     * The button to navigate to the restaurant panel.
     */
    private JButton restaurantButton;

    /**
     * The button to navigate to the menu panel.
     */
    private JButton menuButton;

    /**
     * The button to navigate to the inventory panel.
     */
    private JButton inventoryButton;

    /**
     * The button to navigate to the staff panel.
     */
    private JButton staffButton;

    /**
     * The button to navigate to the sales panel.
     */
    private JButton salesButton;

    /**
     * The currently selected button in the side panel.
     */
    private JButton selectedButton;

    /**
     * The button to refresh the current content panel.
     */
    private JButton refreshButton;

    /**
     * Constructs a new MainInterface object with the specified main application instance and user.
     *
     * @param m    the main application instance
     * @param user the logged-in user
     */
    public MainInterface(Main m, String user) {
        main = m;
        welcomeLabel.setText("Welcome, " + user + '!');
        main.addLabelIcon(logoLabel, "restaurant-logo.jpeg", -1, 150);
        main.addLabelIcon(poweredByLabel, "team-icon.png", -1, 30);

        // Initial content displayed
        connect();
        switchContentPanel(new RestaurantPanel(this), restaurantButton);

        // Buttons
        signOutButton.addActionListener(this);
        restaurantButton.addActionListener(this);
        menuButton.addActionListener(this);
        inventoryButton.addActionListener(this);
        staffButton.addActionListener(this);
        salesButton.addActionListener(this);
        refreshButton.addActionListener(this);

        // Time
        updateTime(); // Get current time to display
        new Timer(60000, e -> updateTime()).start(); // Refresh every minute
    }

    /**
     * Creates the UI components for the MainInterface class.
     * Initializes the content panel with a GridLayout and sets an empty border.
     */
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
        else if (button == refreshButton) reconnect(); // Re-instantiate current content panel
        if (button.getParent() == sidePanel) updateSideButtons(button);
    }

    /**
     * Updates the time label with the current system time formatted according to the DATE_FORMAT.
     */
    private void updateTime() {
        String time = Main.DATE_FORMAT.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    /**
     * Switches the content panel to the specified new panel and updates the content label and side buttons accordingly.
     *
     * @param newPanel the new panel to switch to
     * @param button   the button associated with the new panel
     */
    private void switchContentPanel(MyPanel newPanel, JButton button) {
        contentPanel.removeAll();
        innerContentPanel = newPanel;
        contentPanel.add(innerContentPanel.getMainPanel());
        main.revalidate();

        contentLabel.setText(newPanel.getTitle()); // Set content menu title
        updateSideButtons(button); // Switch colors to selected button
    }

    /**
     * Updates the visual appearance of side buttons to indicate the selected button.
     *
     * @param newButton the newly selected button
     */
    private void updateSideButtons(JButton newButton) {
        // Set current button to unselected color
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(0x3E4649));
            selectedButton.setForeground(new Color(0xC0C0C0));
        }
        // Set new button as selected and change to selected color
        selectedButton = newButton;
        selectedButton.setBackground(new Color(0xC3944E));
        selectedButton.setForeground(Color.WHITE);
    }

    /**
     * Reconnects to the database and refreshes the current content panel based on the innerContentPanel instance.
     */
    private void reconnect() {
        connect(); // Create new connection
        if (innerContentPanel instanceof RestaurantPanel) switchContentPanel(new RestaurantPanel(this), restaurantButton);
        else if (innerContentPanel instanceof MenuPanel) switchContentPanel(new MenuPanel(this), menuButton);
        else if (innerContentPanel instanceof InventoryPanel) switchContentPanel(new InventoryPanel(this), inventoryButton);
        else if (innerContentPanel instanceof StaffPanel) switchContentPanel(new StaffPanel(this), staffButton);
        else if (innerContentPanel instanceof SalesPanel) switchContentPanel(new SalesPanel(this), salesButton);
        System.out.println("Connection refreshed");
    }
}
