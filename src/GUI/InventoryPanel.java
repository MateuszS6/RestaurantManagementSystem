package GUI;

import JDBC.Ingredient;

import javax.swing.*;
import java.util.List;

public class InventoryPanel extends MyPanel {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JTable stockTable;
    private JTable deliveriesTable;

    public InventoryPanel(MyPanel parent) {
        super("Inventory", parent.getConnection());
    }

    private void createUIComponents() {
        List<Ingredient> stock = getConnection().getStock();
        List<Ingredient> deliveries = getConnection().getDeliveries();

        // Populate tables
        String[] columns = {"ID", "Name", "Quantity"};
        stockTable = populateTable(stock, columns);
        deliveriesTable = populateTable(deliveries, columns);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JTable populateTable(List<Ingredient> ingredients, String[] columnNames) {
        String[][] data = new String[ingredients.size()][columnNames.length];
        for (int i = 0; i < ingredients.size(); i++) data[i] = ingredients.get(i).getInfo();
        return new JTable(data, columnNames);
    }
}
