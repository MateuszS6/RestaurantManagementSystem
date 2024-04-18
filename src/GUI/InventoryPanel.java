package GUI;

import JDBC.Ingredient;

import javax.swing.*;
import java.util.List;

/**
 * The InventoryPanel class represents a panel for displaying inventory-related information.
 * It extends the MyPanel class and provides functionality for managing stock and deliveries.
 */
public class InventoryPanel extends MyPanel {
    /**
     * The main JPanel associated with the inventory panel.
     */
    private JPanel mainPanel;

    /**
     * The JTable for displaying stock information.
     */
    private JTable stockTable; // UI bound

    /**
     * The JTable for displaying deliveries information.
     */
    private JTable deliveriesTable; // UI bound

    /**
     * Constructs a new InventoryPanel object with the specified parent panel.
     *
     * @param parent the parent panel
     */
    public InventoryPanel(MyPanel parent) {
        super("Inventory", parent.getConnection());
    }

    /**
     * Custom-create method for initializing UI components.
     * Populates the stock and deliveries tables with data from the database connection.
     */
    private void createUIComponents() {
        List<Ingredient> stock = getConnection().getStock();
        List<Ingredient> deliveries = getConnection().getDeliveries();

        // Populate stock and delivery tables with data from inherited connection
        String[] columns = {"ID", "Name", "Quantity"};
        stockTable = populateTable(stock, columns);
        deliveriesTable = populateTable(deliveries, columns);
    }

    /**
     * Returns the main JPanel associated with the inventory panel.
     *
     * @return the main JPanel associated with the inventory panel
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Creates a new JTable with the specified ingredient list and column names.
     *
     * @param ingredients the list of ingredients to display in the table
     * @param columnNames the column names for the table
     * @return the populated JTable
     */
    private JTable populateTable(List<Ingredient> ingredients, String[] columnNames) {
        String[][] data = new String[ingredients.size()][columnNames.length];
        for (int i = 0; i < ingredients.size(); i++) data[i] = ingredients.get(i).getInfo();
        return new JTable(data, columnNames);
    }
}
