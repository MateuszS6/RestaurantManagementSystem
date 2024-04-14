package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection {
    // Front of house
    private Menu menu;
    // Kitchen
    private List<Ingredient> stock;
    private List<Ingredient> deliveries;

    public DBConnection() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t06",
                "in2033t06_a",
                "PS7gGioYXOM")) {

            FrontOfHouse frontOfHouse = new FrontOfHouse(connection);
            menu = frontOfHouse.getMenu();
            if (menu == null) throw new SQLException("Menu is null");

            Kitchen kitchen = new Kitchen(connection);
            stock = kitchen.getIngredientsAvailable();
            deliveries = kitchen.getDeliveryOrder();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        DBConnection test = new DBConnection();
//        test.menu.print();
        System.out.println(test.stock);
        System.out.println(test.deliveries);
    }

    public Menu getMenu() {
        return menu;
    }

    public List<Ingredient> getStock() {
        return stock;
    }

    public List<Ingredient> getDeliveries() {
        return deliveries;
    }
}
