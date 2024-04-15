package GUI;

import JDBC.Dish;
import JDBC.WinePairing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPanel extends MyPanel implements ActionListener {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JTable dishesTable;
    private JTable winesTable;
    private JButton addButton;
    private JButton removeButton;
    private JButton approveButton;
    private String[][] menuData;

    public MenuPanel(MyPanel parent) {
        super("Menu", parent.getConnection());

        // Buttons
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        approveButton.addActionListener(this); // TODO: Menu to PDF
    }

    private void createUIComponents() {
        List<Dish> dishes = getConnection().getMenu().getDishes();
        List<WinePairing> wines = getConnection().getWines();

        // Populate arrays with menu items
        String[] menuColumnNames = {"ID", "Name", "Description", "Price", "Allergens"};
        menuData = new String[dishes.size()][menuColumnNames.length];
        for (int d = 0; d < dishes.size(); d++) menuData[d] = dishes.get(d).getInfo();

        String[] wineColumnNames = {"ID", "Menu Item ID", "Name"};
        String[][] wineData = new String[wines.size()][wineColumnNames.length];
        for (int w = 0; w < wines.size(); w++) wineData[w] = wines.get(w).getInfo();

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
        if (e.getSource() == addButton) {
            System.out.println("Add Button pressed");
        } else if (e.getSource() == removeButton) {
            System.out.println("Remove Button pressed");
        } else if (e.getSource() == approveButton) {
            System.out.println("Approve Button pressed");
        }
    }

    private void changeColumnWidth(JTable table, int column, int width) {
        table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
}
