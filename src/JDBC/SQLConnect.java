package JDBC;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;

public class SQLConnect {
    static final String serverName = "smcse-stuproj00.city.ac.uk";
    static final String portNumber = "3306";
    static final String dbName = "in2033t06";
    static final String userName = "in2033t06_a";
    static final String password = "PS7gGioYXOM";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // establish connection
            connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password);

            if (connection != null) {
                System.out.println("Connected to the database!");


                viewTables(connection);
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTestData(Connection connection) throws SQLException {
        // Add data to the empty tables
        addTestDataToMenuItems(connection);
        addTestDataToOrders(connection);
        addTestDataToSales(connection);
        addTestDataToSaleItems(connection);
        addTestDataToStaffing(connection);
        addTestDataToWinePairings(connection);
    }

    public static void addTestDataToMenuItems(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO MenuItems (MenuItemID, Name, Description, Price, Allergens) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "Pasta Carbonara");
        preparedStatement.setString(3, "Spaghetti with creamy sauce, bacon, and parmesan cheese");
        preparedStatement.setBigDecimal(4, new BigDecimal("12.99"));
        preparedStatement.setString(5, "Contains wheat, eggs, dairy");

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Grilled Salmon");
        preparedStatement.setString(3, "Freshly grilled salmon fillet served with seasonal vegetables");
        preparedStatement.setBigDecimal(4, new BigDecimal("18.50"));
        preparedStatement.setString(5, "Contains fish");

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into MenuItems: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }

    public static void addTestDataToOrders(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO Orders (OrderID, OrderDate, Supplier, ItemType, Quantity, TotalCost) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setDate(2, Date.valueOf("2024-03-24"));
        preparedStatement.setString(3, "Supplier A");
        preparedStatement.setString(4, "Food");
        preparedStatement.setInt(5, 100);
        preparedStatement.setBigDecimal(6, new BigDecimal("250.00"));

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setDate(2, Date.valueOf("2024-03-25"));
        preparedStatement.setString(3, "Supplier B");
        preparedStatement.setString(4, "Beverage");
        preparedStatement.setInt(5, 50);
        preparedStatement.setBigDecimal(6, new BigDecimal("150.00"));

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into Orders: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }

    public static void addTestDataToSaleItems(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO SaleItems (SaleItemID, SaleID, MenuItemID, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 1); // Use an existing SaleID from the Sales table
        preparedStatement.setInt(3, 1); // Use an existing MenuItemID from the MenuItems table
        preparedStatement.setInt(4, 2);

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 2); // Use an existing SaleID from the Sales table
        preparedStatement.setInt(3, 2); // Use an existing MenuItemID from the MenuItems table
        preparedStatement.setInt(4, 3);

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into SaleItems: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }


    public static void addTestDataToSales(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO Sales (SaleID, EmployeeID, SaleDate, TotalAmount) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 101);
        preparedStatement.setDate(3, Date.valueOf("2024-03-24"));
        preparedStatement.setBigDecimal(4, new BigDecimal("150.00"));

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 102);
        preparedStatement.setDate(3, Date.valueOf("2024-03-25"));
        preparedStatement.setBigDecimal(4, new BigDecimal("200.00"));

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into Sales: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }

    public static void addTestDataToStaffing(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO Staffing (StaffingID, EmployeeID, Date, ShiftType) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 101);
        preparedStatement.setDate(3, Date.valueOf("2024-03-24"));
        preparedStatement.setString(4, "Morning");

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 102);
        preparedStatement.setDate(3, Date.valueOf("2024-03-25"));
        preparedStatement.setString(4, "Evening");

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into Staffing: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }

    public static void addTestDataToWinePairings(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO WinePairings (WinePairingID, MenuItemID, WineName) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        // Insert two rows of test data
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 1);
        preparedStatement.setString(3, "Chardonnay");

        preparedStatement.addBatch();

        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 2);
        preparedStatement.setString(3, "Merlot");

        preparedStatement.addBatch();

        int[] rowsAffected = preparedStatement.executeBatch();
        System.out.println("Rows inserted into WinePairings: " + Arrays.toString(rowsAffected));

        preparedStatement.close();
    }



    public static void executeUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
    public static void viewBasicTables(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tablesResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});

        while (tablesResultSet.next()) {
            String tableName = tablesResultSet.getString("TABLE_NAME");
            System.out.println("Table: " + tableName);

            Statement statement = connection.createStatement();
            ResultSet dataResultSet = statement.executeQuery("SELECT * FROM " + tableName);

            if (!dataResultSet.next()) {
                System.out.println("  - This table is empty.");
            } else {
                System.out.println("  - This table has data:");
                ResultSetMetaData rsmd = dataResultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                do {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(dataResultSet.getString(i) + "\t");
                    }
                    System.out.println();
                } while (dataResultSet.next());
            }

            dataResultSet.close();
            statement.close();
        }
        tablesResultSet.close();
    }

    public static void viewTables(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tablesResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});

        while (tablesResultSet.next()) {
            String tableName = tablesResultSet.getString("TABLE_NAME");
            System.out.println("Table: " + tableName);

            Statement statement = connection.createStatement();
            ResultSet dataResultSet = statement.executeQuery("SELECT * FROM " + tableName);

            ResultSetMetaData rsmd = dataResultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            System.out.println("Column Name\tColumn Type");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                String columnType = rsmd.getColumnTypeName(i);
                System.out.println(columnName + "\t" + columnType);
            }

            if (!dataResultSet.next()) {
                System.out.println("  - This table is empty.");
            } else {
                System.out.println("  - This table has data:");
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(dataResultSet.getString(i) + "\t");
                    }
                    System.out.println();
                } while (dataResultSet.next());
            }

            dataResultSet.close();
            statement.close();
        }
        tablesResultSet.close();
    }

    public static void addEmployee(Connection connection, int employeeID, String name, String role, String holidayDates, String contactDetails) throws SQLException {
        String insertQuery = "INSERT INTO Employees (EmployeeID, Name, Role, HolidayDates, ContactDetails) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        preparedStatement.setInt(1, employeeID);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, role);
        preparedStatement.setString(4, holidayDates);
        preparedStatement.setString(5, contactDetails);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Employee inserted successfully.");
        } else {
            System.out.println("Failed to insert employee.");
        }
        preparedStatement.close();
    }

    public static void removeEmployee(Connection connection, int employeeID) throws SQLException {
        String deleteQuery = "DELETE FROM Employees WHERE EmployeeID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

        preparedStatement.setInt(1, employeeID);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee with ID " + employeeID + " not found.");
        }
        preparedStatement.close();
    }
}

