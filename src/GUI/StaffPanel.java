package GUI;

import JDBC.Employee;

import javax.swing.*;
import java.util.List;

public class StaffPanel extends MyPanel {
    private JPanel mainPanel;
    private JTable staffHolidaysTable;

    public StaffPanel(MyPanel parent) {
        super("Staff", parent.getConnection());
    }

    private void createUIComponents() {
        List<Employee> staffHolidays = getConnection().getStaffHolidays();

        // Populate table arrays
        String[] columnNames = new String[]{"ID", "Name", "Role", "Holiday Dates"};
        String[][] data = new String[staffHolidays.size()][columnNames.length];
        for (int s = 0; s < staffHolidays.size(); s++) data[s] = staffHolidays.get(s).getInfo();

        // Staff holidays table
        staffHolidaysTable = new JTable(data, columnNames);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
