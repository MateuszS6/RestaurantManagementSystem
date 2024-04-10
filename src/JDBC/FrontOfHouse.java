package JDBC;

import java.sql.*;

// NB: to run methods in this class, instantiate it in a main method and simply call the functions.

public class FrontOfHouse implements IFrontOfHouse {
    private final Connection connection;

    public FrontOfHouse(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Menu getMenu() {
        String query = "SELECT MenuItemID, Name, Description, Price, Allergens FROM MenuItems";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // builds menu
            Menu menu = new Menu();
            while (resultSet.next()) {
                int menuItemID = resultSet.getInt("MenuItemID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                String allergens = resultSet.getString("Allergens");

                Dish dish = new Dish(menuItemID, name, description, price, allergens);
                menu.addDish(dish);
            }
            return menu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int getAnnualBooking() {
        // total annual bookings
        String query = "SELECT COUNT(*) FROM Bookings WHERE YEAR(BookingDate) = YEAR(CURDATE())";
        return executeCountQuery(query);
    }

    @Override
    public int getAnnualCovers() {
        // total annual covers
        String query = "SELECT SUM(Covers) FROM Bookings WHERE YEAR(BookingDate) = YEAR(CURDATE())";
        return executeSumQuery(query);
    }

    @Override
    public int getDayAverageBooking(WeekDay day) {
        // average weekday bookings (provided as argument)
        String query = "SELECT AverageBookings FROM DailyBookings WHERE DayOfWeek = ?";
        return executeAverageQuery(query, day.toString());
    }

    @Override
    public int getDayAverageCovers(WeekDay day) {
        // average weekday covers (provided as argument)
        String query = "SELECT AverageCovers FROM DailyCovers WHERE DayOfWeek = ?";
        return executeAverageQuery(query, day.toString());
    }

    @Override
    public void sendLimitEmail(Email email) {
        // TODO: implement email sending API
        System.out.println("Sending limit email to: " + email.getRecipient());
    }

    // helper to run the count SQL queries
    private int executeCountQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // helper to run sum SQL queries
    private int executeSumQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // same, but for averages
    private int executeAverageQuery(String query, String parameter) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, parameter);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // return 0 if no result or error occurs
    }
}
