package GUI;

import javax.swing.*;

public class MenuPanel extends TestPanel {
    private JTable menuTable;

    public MenuPanel() {
        getContentLabel().setText("Menu");
        String[] columns = {"1", "2"};
        String[][] data = {{"test1", "test2"}, {"test3", "test4"}};
        menuTable = new JTable(data, columns);
        menuTable.setFillsViewportHeight(true);
        getContentPanel().add(new JScrollPane(menuTable));
    }
}