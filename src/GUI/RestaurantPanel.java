package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel tablesPanel;
    private JButton[] tables;
    private JComboBox dateComboBox;
    private JLabel tableNumberLabel;
    private JLabel statusLabel;
    private JRadioButton a20RadioButton;
    private JRadioButton a25RadioButton;
    private JRadioButton a30RadioButton;

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

    // Custom create for table buttons
    private void createUIComponents() {
        tablesPanel = new JPanel(new GridLayout(5, 3)); // 15 spaces for table buttons
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thicker margins
        tables = new JButton[15];
        for (int b = 0; b < tables.length; b++) {
            tables[b] = new JButton(String.valueOf(b + 1)); // Add new button to array using index + 1 as table number
            tables[b].setFocusable(false);
            tables[b].setBackground(Main.HIGHLIGHT_COLOUR);
            tables[b].setForeground(Color.BLACK);
            tables[b].setFont(new Font("Arial Black", Font.PLAIN, 16));
            tables[b].addActionListener(this);
            tablesPanel.add(tables[b]); // Add button to panel
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
