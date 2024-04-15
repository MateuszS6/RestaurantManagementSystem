package GUI;

import JDBC.Ingredient;

import javax.swing.*;
import java.util.List;

public class InventoryPanel extends MyPanel {
    private JPanel mainPanel;
    private JTable stockTable;
    private JTable deliveriesTable;

    public InventoryPanel(MyPanel parent) {
        super("Inventory", parent.getConnection());
    }

    // Custom-create for stock and delivery tables
    private void createUIComponents() {
        List<Ingredient> stock = getConnection().getStock();
        List<Ingredient> deliveries = getConnection().getDeliveries();

        // Populate stock and delivery tables with data from inherited connection
        String[] columns = {"ID", "Name", "Quantity"};
        stockTable = populateTable(stock, columns);
        deliveriesTable = populateTable(deliveries, columns);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Create new table with ingredient list and column names
    private JTable populateTable(List<Ingredient> ingredients, String[] columnNames) {
        String[][] data = new String[ingredients.size()][columnNames.length];
        for (int i = 0; i < ingredients.size(); i++) data[i] = ingredients.get(i).getInfo();
        return new JTable(data, columnNames);
    }
}
