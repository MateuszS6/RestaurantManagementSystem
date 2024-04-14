package GUI;

import JDBC.Dish;
import JDBC.WinePairing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JTable dishesTable;
    private JTable winesTable;
    private JButton approveButton;

    public MenuPanel(MyPanel parent) {
        super("Menu", parent.getConnection());

        // Buttons
        approveButton.addActionListener(this);

        // TODO: Menu to PDF
    }

    private void createUIComponents() {
        List<Dish> dishes = getConnection().getMenu().getDishes();
        List<WinePairing> wines = getConnection().getWines();

        // Populate arrays with menu items
        String[] menuColumnNames = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] menuData = new String[dishes.size()][menuColumnNames.length];
        for (Dish d : dishes) menuData[d.getID() - 1] = d.getInfo();

        String[] wineColumnNames = {"ID", "Menu Item ID", "Name"};
        String[][] wineData = new String[wines.size()][wineColumnNames.length];
        for (WinePairing w : wines) wineData[w.getID() - 1] = w.getInfo();

        // Menu table
        dishesTable = new JTable(menuData, menuColumnNames);
        changeColumnWidth(dishesTable, 0, 10);
        changeColumnWidth(dishesTable, 1, 120);
        changeColumnWidth(dishesTable, 2, 260);
        changeColumnWidth(dishesTable, 3, 10);

        // Wines table
        winesTable = new JTable(wineData, wineColumnNames);
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

    private void changeColumnWidth(JTable table, int column, int width) {
        table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
}
