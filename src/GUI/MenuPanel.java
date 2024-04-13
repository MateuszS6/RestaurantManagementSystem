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
    private JButton approveButton;

    public MenuPanel(DBConnection connection) {
        super("Menu", connection);

        // Buttons
        approveButton.addActionListener(this);

        // TODO: Menu to PDF
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
        if (e.getSource() == approveButton) {
            System.out.println("Approve Button pressed");
        }
    }
}
