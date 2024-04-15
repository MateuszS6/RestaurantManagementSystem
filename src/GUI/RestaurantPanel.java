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

    public RestaurantPanel(MyPanel parent) {
        super("Tables", parent.getConnection());

        dateComboBox.addActionListener(e -> {
            for (JButton t : tables) {
                if (t.getText().equals("10") || t.getText().equals("11") || t.getText().equals("12")) {
                    if (dateComboBox.getSelectedIndex() == 0) t.setBackground(Main.HIGHLIGHT_COLOUR);
                    else if (dateComboBox.getSelectedIndex() == 1) t.setBackground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void createUIComponents() {
        tablesPanel = new JPanel(new GridLayout(5, 3));
        tablesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tables = new JButton[15];
        for (int b = 0; b < tables.length; b++) {
            tables[b] = new JButton(String.valueOf(b + 1));
            tables[b].setFocusable(false);
//            tables[b].setBorderPainted(false);
            tables[b].setBackground(Main.HIGHLIGHT_COLOUR);
            tables[b].setForeground(Color.BLACK);
            tables[b].setFont(new Font("Arial Black", Font.PLAIN, 16));
            tables[b].addActionListener(this);
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
                if (t.getBackground() == Main.HIGHLIGHT_COLOUR) statusLabel.setText("Status: Vacant");
                else if (t.getBackground() == Color.LIGHT_GRAY) statusLabel.setText("Status: Booked");
            }
        }
    }
}
