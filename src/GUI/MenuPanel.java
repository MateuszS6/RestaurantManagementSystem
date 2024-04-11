package GUI;

import JDBC.DBConnection;
import JDBC.Dish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JTable menuTable;
    private JButton refreshButton;
    private JButton addButton;
    private JButton removeButton;

    public MenuPanel(DBConnection connection) {
        super("Menu", connection);

        // Buttons
        refreshButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
    }

    private void createUIComponents() {
        // Populate arrays with menu items
        List<Dish> dishes = getConnection().getMenu().getDishes();
        String[] columns = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] data = new String[dishes.size()][columns.length];
        for (Dish dish : dishes) data[dish.getId() - 1] = dish.getInfo();

        // Menu table
        menuTable = new JTable(data, columns);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            System.out.println("Refresh Button pressed");
        } else if (e.getSource() == addButton) {
            System.out.println("Add Button pressed");
        } else if (e.getSource() == removeButton) {
            System.out.println("Remove Button pressed");
        }
    }
}
