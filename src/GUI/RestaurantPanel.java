package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RestaurantPanel class represents the restaurant management panel in the Lancaster's Restaurant Management system.
 * It allows users to view and manage restaurant tables, including their status and availability.
 * The class extends MyPanel and implements ActionListener interface to handle button clicks.
 */
public class RestaurantPanel extends MyPanel implements ActionListener {
    /**
     * The main JPanel associated with the restaurant panel.
     */
    private JPanel mainPanel;

    /**
     * The panel to display restaurant tables.
     */
    private JPanel tablesPanel;

    /**
     * An array of buttons representing restaurant tables.
     */
    private JButton[] tables;

    /**
     * The combo box to select dates for viewing table status.
     */
    private JComboBox dateComboBox;

    /**
     * The label displaying the table number.
     */
    private JLabel tableNumberLabel;

    /**
     * The label displaying the status of the selected table.
     */
    private JLabel statusLabel;

    /**
     * The radio button indicating a 20% occupancy rate.
     */
    private JRadioButton a20RadioButton;

    /**
     * The radio button indicating a 25% occupancy rate.
     */
    private JRadioButton a25RadioButton;

    /**
     * The radio button indicating a 30% occupancy rate.
     */
    private JRadioButton a30RadioButton;

    /**
     * Constructs a new RestaurantPanel object with the specified parent panel.
     *
     * @param parent the parent panel
     */
    public RestaurantPanel(MyPanel parent) {
        super("Restaurant", parent.getConnection());

        dateComboBox.addActionListener(e -> {
            for (JButton t : tables) {
                // Status varies between dates for tables 10, 11, and 12
                if (t.getText().equals("10") || t.getText().equals("11") || t.getText().equals("12")) {
                    if (dateComboBox.getSelectedIndex() == 0) t.setBackground(Main.HIGHLIGHT_COLOUR); // 3/1/24
                    else if (dateComboBox.getSelectedIndex() == 1) t.setBackground(Color.LIGHT_GRAY); // 5/1/24
                }
            }
        });
    }

    /**
     * Custom-create method for table buttons.
     * Creates a grid layout for displaying table buttons in the tablesPanel.
     * Each button represents a table, with numbers ranging from 1 to 15.
     * Sets custom properties for each button, such as background color, font, and action listener.
     */
    private void createUIComponents() {
        // Create a panel with a 5x3 grid layout to accommodate 15 table buttons
        tablesPanel = new JPanel(new GridLayout(5, 3));

        // Add thicker margins to the panel for better visual appearance
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize an array to hold the table buttons
        tables = new JButton[15];

        // Populate the array with table buttons
        for (int b = 0; b < tables.length; b++) {
            // Create a new button with the table number as its label
            tables[b] = new JButton(String.valueOf(b + 1));

            // Set button properties: focusable, background color, text color, and font
            tables[b].setFocusable(false);
            tables[b].setBackground(Main.HIGHLIGHT_COLOUR);
            tables[b].setForeground(Color.BLACK);
            tables[b].setFont(new Font("Arial Black", Font.PLAIN, 16));

            // Add an action listener to handle button clicks
            tables[b].addActionListener(this);

            // Add the button to the tablesPanel
            tablesPanel.add(tables[b]);
        }
    }


    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton t : tables) {
            if (e.getSource() == t) {
                tableNumberLabel.setText(t.getText()); // Set table number to selected table
                if (t.getBackground() == Main.HIGHLIGHT_COLOUR) statusLabel.setText("Status: Vacant");
                else if (t.getBackground() == Color.LIGHT_GRAY) statusLabel.setText("Status: Booked"); // Booked if grey
            }
        }
    }
}
