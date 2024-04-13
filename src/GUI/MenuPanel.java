package GUI;

import JDBC.Dish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JTable menuTable;
    private JButton approveButton;

    public MenuPanel(MyPanel parent) {
        super("Menu", parent.getConnection());

        // Buttons
        approveButton.addActionListener(this);

        // TODO: Menu to PDF
    }

    private void createUIComponents() {
        // Populate arrays with menu items
        List<Dish> dishes = getConnection().getMenu().getDishes();
        String[] columnNames = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] data = new String[dishes.size()][columnNames.length];
        for (Dish d : dishes) data[d.getID() - 1] = d.getInfo();

        // Menu table
        menuTable = new JTable(data, columnNames);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            System.out.println("Approve Button pressed");
        }
    }
}
