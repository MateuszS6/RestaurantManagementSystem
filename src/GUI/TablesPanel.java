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

    public TablesPanel(MyPanel parent) {
        super("Tables", parent.getConnection());

    }

    private void createUIComponents() {
        tablesPanel = new JPanel(new GridLayout(5, 3));
        tables = new JButton[15];
        for (int b = 0; b < tables.length; b++) {
            tables[b] = new JButton("Table " + (b + 1));
//            tables[b].setFocusable(false);
//            tables[b].setBorderPainted(false);
//            tables[b].setBackground(tablesPanel.getBackground());
//            tables[b].setForeground(new Color(0xC3944E));
//            tables[b].setFont(new Font("Arial Black", Font.PLAIN, 18));
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
