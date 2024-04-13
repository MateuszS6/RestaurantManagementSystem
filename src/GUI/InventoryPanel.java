package GUI;

import JDBC.Ingredient;

import javax.swing.*;
import java.util.List;

public class InventoryPanel extends MyPanel {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JTable ingredientsTable;
    private JTable deliveriesTable;

    public InventoryPanel(MyPanel parent) {
        super("Inventory", parent.getConnection());
    }

    private void createUIComponents() {
        List<Ingredient> ingredients = getConnection().getIngredientsAvailable();
//        List<Ingredient> deliveries = getConnection().getDeliveries();
        String[] columnNames = {"ID", "Name", "Quantity"};
        String[][] ingredientsData = new String[ingredients.size()][columnNames.length];
        for (Ingredient i : ingredients) ingredientsData[i.getID() - 1] = i.getInfo();

        ingredientsTable = new JTable(ingredientsData, columnNames);

        deliveriesTable = new JTable();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
