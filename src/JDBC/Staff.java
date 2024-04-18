package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents staff-related operations in the database.
 */
public class Staff {
    /**
     * Represents a connection to the database.
     */
    private final Connection connection;

    /**
     * Constructs a Staff object with the given database connection.
     *
     * @param connection The database connection.
     */
    public Staff(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a list of employee holiday dates from the database.
     *
     * @return A list of Employee objects representing employees' holiday dates.
     */
    public List<Employee> getEmployeeHolidayDates() {
        List<Employee> employeeHolidayDates = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT EmployeeID, Name, Role, HolidayDates FROM Employees");

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("EmployeeID");
                String name = resultSet.getString("Name");
                String role = resultSet.getString("Role");
                String holidayDates = resultSet.getString("HolidayDates");

                employeeHolidayDates.add(new Employee(employeeID, name, role, holidayDates));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeHolidayDates;
    }
}
