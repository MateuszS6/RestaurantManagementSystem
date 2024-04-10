package GUI;

import JDBC.DBConnection;
import JDBC.Dish;
import JDBC.Menu;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class MenuPanel extends TestPanel {
    private JTable menuTable;

    public MenuPanel(DBConnection connection) {
        getContentLabel().setText("Menu");

        List<Dish> dishes = connection.getMenu().getDishes();
        String[] columns = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] data = new String[dishes.size()][columns.length];
        for (Dish dish : dishes) data[dish.getId() - 1] = dish.getInfo();

        menuTable = new JTable(data, columns);
        menuTable.setFillsViewportHeight(true);
        getContentPanel().add(new JScrollPane(menuTable));
    }
}