package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Staff {
    private final Connection connection;

    public Staff(Connection connection) {
        this.connection = connection;
    }

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
