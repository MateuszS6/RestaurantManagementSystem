package GUI;

import JDBC.DBConnection;
import JDBC.Dish;

import javax.swing.*;
import java.awt.*;
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
        setTitle("Menu");

        List<Dish> dishes = connection.getMenu().getDishes();
        String[] columns = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] data = new String[dishes.size()][columns.length];
        for (Dish dish : dishes) data[dish.getId() - 1] = dish.getInfo();

        menuTable = new JTable(data, columns);
        menuTable.setFillsViewportHeight(true);
        mainPanel.add(new JScrollPane(menuTable));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
