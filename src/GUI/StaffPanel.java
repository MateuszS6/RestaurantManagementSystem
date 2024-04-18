package GUI;

import JDBC.Employee;

import javax.swing.*;
import java.util.List;

/**
 * The StaffPanel class represents a panel for displaying staff-related information.
 * It extends the MyPanel class and provides functionality to display staff holidays in a table.
 */
public class StaffPanel extends MyPanel {
    /**
     * The main panel for displaying staff-related information.
     */
    private JPanel mainPanel;

    /**
     * Table displaying staff holidays.
     */
    private JTable staffHolidaysTable;

    /**
     * Constructs a StaffPanel object with a specified parent panel.
     *
     * @param parent The parent panel.
     */
    public StaffPanel(MyPanel parent) {
        super("Staff", parent.getConnection());
    }

    /**
     * Custom-creates the staff holidays table for the staff panel.
     * Populates the table with data retrieved from the database connection.
     */
    private void createUIComponents() {
        // Staff/holiday list
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
