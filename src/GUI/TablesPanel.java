package GUI;

import JDBC.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablesPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel tablesPanel;
    private JButton[] tables;
    private JLabel infoLabel;

    public TablesPanel(DBConnection connection) {
        super("Tables", connection);

    }

    private void createUIComponents() {
        tablesPanel = new JPanel(new GridLayout(5, 3));
        tables = new JButton[15];
        for (int b = 0; b < tables.length; b++) {
            tables[b] = new JButton("TABLE " + (b + 1));
            tables[b].setFocusable(false);
            tables[b].setBackground(new Color(0x01A2FF));
            tables[b].setForeground(Color.WHITE);
            tables[b].setFont(new Font("Arial Black", Font.PLAIN, 18));
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
        for (JButton table : tables) {
            if (e.getSource() == table) {
                infoLabel.setText(table.getText());
            }
        }
    }
}
