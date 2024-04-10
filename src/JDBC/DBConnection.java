package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String serverName = "smcse-stuproj00.city.ac.uk";
    static final String portNumber = "3306";
    static final String dbName = "in2033t06";
    static final String userName = "in2033t06_a";
    static final String password = "PS7gGioYXOM";

    public DBConnection() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, userName, password)) {
            FrontOfHouse frontOfHouse = new FrontOfHouse(connection);
            Menu menu = frontOfHouse.getMenu();
            menu.print();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new DBConnection();
    }
}
