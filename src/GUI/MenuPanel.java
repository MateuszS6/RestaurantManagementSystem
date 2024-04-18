package GUI;

import JDBC.Dish;
import JDBC.WinePairing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The MenuPanel class represents the menu management panel in the Lancaster's Restaurant Management system.
 * It allows users to view, add, remove, and approve dishes and wine pairings.
 * The class extends MyPanel and implements ActionListener interface to handle button clicks.
 */
public class MenuPanel extends MyPanel implements ActionListener {
    /**
     * The main JPanel associated with the menu panel.
     */
    private JPanel mainPanel;

    /**
     * The tabbed pane to display dishes and wine pairings.
     */
    private JTabbedPane tabbedPane;

    /**
     * The table to display dish information.
     */
    private JTable dishTable;

    /**
     * The table to display wine pairing information.
     */
    private JTable wineTable;

    /**
     * The button to add a new dish or wine pairing.
     */
    private JButton addButton;

    /**
     * The button to remove a selected dish or wine pairing.
     */
    private JButton removeButton;

    /**
     * The button to approve changes to the menu and export it to PDF.
     */
    private JButton approveButton;

    /**
     * Constructs a new MenuPanel object with the specified parent panel.
     *
     * @param parent the parent panel
     */
    public MenuPanel(MyPanel parent) {
        super("Menu", parent.getConnection());

        // Buttons
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        approveButton.addActionListener(this); // TODO: Menu to PDF
    }

    // Custom-create for dish and wine tables
    private void createUIComponents() {
        List<Dish> dishes = getConnection().getMenu().getDishes(); // Dishes
        List<WinePairing> wines = getConnection().getWines(); // Wine pairings

        // Populate dish arrays with data from the DB connection
        String[] menuColumnNames = {"ID", "Name", "Description", "Price", "Allergens"};
        String[][] dishData = new String[dishes.size()][menuColumnNames.length];
        for (int d = 0; d < dishes.size(); d++) dishData[d] = dishes.get(d).getInfo();

        // Populate wine arrays with data from the DB connection
        String[] wineColumnNames = {"ID", "Menu Item ID", "Name"};
        String[][] wineData = new String[wines.size()][wineColumnNames.length];
        for (int w = 0; w < wines.size(); w++) wineData[w] = wines.get(w).getInfo();

        // Create dish table
        dishTable = new JTable(dishData, menuColumnNames);

        // Fit dish table column widths to display full text
        changeColumnWidth(dishTable, 0, 10);
        changeColumnWidth(dishTable, 1, 120);
        changeColumnWidth(dishTable, 2, 260);
        changeColumnWidth(dishTable, 3, 10);

        // Create wine table
        wineTable = new JTable(wineData, wineColumnNames);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Functionality for buttons exists in SQL classes
        if (e.getSource() == addButton) System.out.println("Add Button pressed");
        else if (e.getSource() == removeButton) System.out.println("Remove Button pressed");
        else if (e.getSource() == approveButton) System.out.println("Approve Button pressed");
    }

    // Change a column width of a table
    private void changeColumnWidth(JTable table, int column, int width) {
        table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
}
