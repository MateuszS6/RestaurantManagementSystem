package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the front of house operations.
 */
public class FrontOfHouse implements IFrontOfHouse {
    /**
     * Represents a connection to the database.
     */
    private final Connection connection;

    /**
     * Constructs a new FrontOfHouse instance with the given database connection.
     * @param connection The database connection.
     */
    public FrontOfHouse(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Menu getMenu() {
        String query = "SELECT * FROM MenuItems";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Build menu
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
            System.err.println(e.getMessage());
            return null;
        }
    }


    @Override
    public int getAnnualBookings() {
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
    public int getDayAverageBookings(WeekDay day) {
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

    @Override
    public List<WinePairing> getWinePairings() {
        List<WinePairing> winePairings = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WinePairings");

            while (resultSet.next()) {
                int winePairingID = resultSet.getInt("WinePairingID");
                int menuItemID = resultSet.getInt("MenuItemID");
                String wineName = resultSet.getString("WineName");

                winePairings.add(new WinePairing(winePairingID, menuItemID, wineName));
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return winePairings;
    }

    /**
     * Helper method to execute count SQL queries.
     *
     * @param query The SQL query to be executed.
     * @return The result of the count query.
     */
    private int executeCountQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Helper method to execute sum SQL queries.
     *
     * @param query The SQL query to be executed.
     * @return The result of the sum query.
     */
    private int executeSumQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Helper method to execute average SQL queries.
     *
     * @param query     The SQL query to be executed.
     * @param parameter The parameter for the prepared statement.
     * @return The result of the average query.
     */
    private int executeAverageQuery(String query, String parameter) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, parameter);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0; // return 0 if no result or error occurs
    }
}
