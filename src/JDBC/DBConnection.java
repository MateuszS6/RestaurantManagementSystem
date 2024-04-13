package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Menu menu;

    public DBConnection() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t06",
                "in2033t06_a",
                "PS7gGioYXOM")) {

            FrontOfHouse frontOfHouse = new FrontOfHouse(connection);
            menu = frontOfHouse.getMenu();
            if (menu == null) throw new SQLException("Menu is null");

//            Kitchen kitchen = new Kitchen(connection);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DBConnection test = new DBConnection();
        test.getMenu().print();
    }

    public Menu getMenu() {
        return menu;
    }
}
