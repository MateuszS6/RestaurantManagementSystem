package JDBC;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * The DBConnection class manages the connection to the database and provides methods
 * to retrieve and manipulate data related to the restaurant's operations.
 */
public class DBConnection {
    /**
     * Represents the database connection.
     */
    private Connection connection;

    /**
     * Represents the menu of the restaurant.
     */
    private Menu menu;

    /**
     * Represents the list of wine pairings available in the restaurant.
     */
    private List<WinePairing> wines;

    /**
     * Represents the current stock of ingredients in the restaurant's inventory.
     */
    private List<Ingredient> stock;

    /**
     * Represents the list of ingredient deliveries received by the restaurant.
     */
    private List<Ingredient> deliveries;

    /**
     * Represents the list of staff members who are on holiday.
     */
    private List<Employee> staffHolidays;

    /**
     * Represents the average number of bookings for each day of the week.
     */
    private int[] dayAverageBookings;

    /**
     * Represents the average number of covers (guests) for each day of the week.
     */
    private int[] dayAverageCovers;

    /**
     * Represents the total number of annual bookings.
     */
    private int annualBookings;

    /**
     * Represents the total number of annual covers (guests).
     */
    private int annualCovers;

    /**
     * Represents the booking data, mapping booking dates to the number of covers.
     */
    private Map<Date, Integer> bookingData;

    /**
     * Represents the sales data for dishes, mapping dish names to sales quantity and revenue.
     */
    private Map<String, Map.Entry<Integer, BigDecimal>> dishData;

    /**
     * Represents the future booking predictions, mapping dates to predicted booking numbers.
     */
    private Map<Date, Integer> futureBookingPredictions;

    /**
     * Constructs a new DBConnection object and establishes a connection to the database.
     * Initializes various data structures and fetches data related to the restaurant's operations.
     */
    public DBConnection() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t06",
                "in2033t06_a",
                "PS7gGioYXOM")) {
            this.connection = connection;

            // Front of house
            FrontOfHouse frontOfHouse = new FrontOfHouse(connection);
            menu = frontOfHouse.getMenu();
            if (menu == null) throw new SQLException("Menu is null");
            wines = frontOfHouse.getWinePairings();
            annualBookings = frontOfHouse.getAnnualBookings();
            annualCovers = frontOfHouse.getAnnualCovers();

            // Kitchen
            Kitchen kitchen = new Kitchen(connection);
            stock = kitchen.getIngredientsAvailable();
            deliveries = kitchen.getDeliveryOrder();

            // Staff
            Staff staff = new Staff(connection);
            staffHolidays = staff.getEmployeeHolidayDates();

            // Sales
            dayAverageBookings = new int[7];
            dayAverageCovers = new int[7];
            for (int i = 0; i < 7; i++) { // Add data for each weekday
                dayAverageBookings[i] = frontOfHouse.getDayAverageBookings(intToWeekDay(i));
                dayAverageCovers[i] = frontOfHouse.getDayAverageCovers(intToWeekDay(i));
            }
            bookingData = fetchBookingData();
            dishData = fetchDishData();
            futureBookingPredictions = fetchFutureBookingPredictions(14); // Hardcoded example, predicting the next 14 days

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1); // Quit application if connection unsuccessful
        }
    }

    /**
     * Retrieves the menu of the restaurant.
     * @return The menu of the restaurant.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Retrieves the list of wine pairings available in the restaurant.
     * @return The list of wine pairings.
     */
    public List<WinePairing> getWines() {
        return wines;
    }

    /**
     * Retrieves the total number of annual bookings.
     * @return The total number of annual bookings.
     */
    public String getAnnualBookings() {
        return String.valueOf(annualBookings);
    }

    /**
     * Retrieves the total number of annual covers (guests).
     * @return The total number of annual covers.
     */
    public String getAnnualCovers() {
        return String.valueOf(annualCovers);
    }

    /**
     * Retrieves the average number of bookings for the specified day of the week.
     * @param dayIndex The index representing the day of the week (0 for Monday, 1 for Tuesday, and so on).
     * @return The average number of bookings for the specified day of the week.
     */
    public String getDayAverageBookings(int dayIndex) {
        return String.valueOf(dayAverageBookings[dayIndex]);
    }

    /**
     * Retrieves the average number of covers (guests) for the specified day of the week.
     * @param dayIndex The index representing the day of the week (0 for Monday, 1 for Tuesday, and so on).
     * @return The average number of covers for the specified day of the week.
     */
    public String getDayAverageCovers(int dayIndex) {
        return String.valueOf(dayAverageCovers[dayIndex]);
    }

    /**
     * Retrieves the current stock of ingredients in the restaurant's inventory.
     * @return The list of ingredients in stock.
     */
    public List<Ingredient> getStock() {
        return stock;
    }

    /**
     * Retrieves the list of ingredient deliveries received by the restaurant.
     * @return The list of ingredient deliveries.
     */
    public List<Ingredient> getDeliveries() {
        return deliveries;
    }

    /**
     * Retrieves the list of staff members who are on holiday.
     * @return The list of staff members on holiday.
     */
    public List<Employee> getStaffHolidays() {
        return staffHolidays;
    }

    /**
     * Retrieves the booking data, mapping booking dates to the number of covers.
     * @return The booking data.
     */
    public Map<Date, Integer> getBookingData() {
        return bookingData;
    }

    /**
     * Retrieves the sales data for dishes, mapping dish names to sales quantity and revenue.
     * @return The dish sales data.
     */
    public Map<String, Map.Entry<Integer, BigDecimal>> getDishData() {
        return dishData;
    }

    /**
     * Retrieves the future booking predictions, mapping dates to predicted booking numbers.
     * @return The future booking predictions.
     */
    public Map<Date, Integer> getFutureBookingPredictions() {
        return futureBookingPredictions;
    }

    /**
     * Converts an integer representing a day index to the corresponding WeekDay enum value.
     *
     * @param day The index of the day (0 for Monday, 1 for Tuesday, ..., 6 for Sunday).
     * @return The WeekDay enum value corresponding to the given day index.
     */
    private WeekDay intToWeekDay(int day) {
        return switch (day) {
            case 0 -> WeekDay.MONDAY;
            case 1 -> WeekDay.TUESDAY;
            case 2 -> WeekDay.WEDNESDAY;
            case 3 -> WeekDay.THURSDAY;
            case 4 -> WeekDay.FRIDAY;
            case 5 -> WeekDay.SATURDAY;
            case 6 -> WeekDay.SUNDAY;
            default -> throw new IllegalStateException("Unexpected value: " + day);
        };
    }

    /**
     * Fetches the booking data from the database, mapping booking dates to the number of covers.
     * @return The booking data.
     */
    private Map<Date, Integer> fetchBookingData() {
        Map<Date, Integer> bookings = new HashMap<>();
        String sqlQuery = "SELECT BookingDate, Covers FROM Bookings";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            // Populate bookings hashmap
            while (resultSet.next()) {
                Date bookingDate = resultSet.getDate("BookingDate");
                int covers = resultSet.getInt("Covers");
                bookings.put(bookingDate, covers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    /**
     * Fetches the sales data for dishes from the database, mapping dish names to sales quantity and revenue.
     * @return The dish sales data.
     */
    private Map<String, Map.Entry<Integer, BigDecimal>> fetchDishData() {
        Map<String, Map.Entry<Integer, BigDecimal>> dishData = new HashMap<>();
        String sqlQuery = "SELECT m.Name, s.Quantity, m.Price FROM MenuItems m JOIN SaleItems s ON m.MenuItemID = s.MenuItemID";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String dishName = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");
                BigDecimal price = resultSet.getBigDecimal("Price");

                // Calculate revenue
                BigDecimal revenue = price.multiply(BigDecimal.valueOf(quantity));

                // Update dish data map
                dishData.put(dishName, new AbstractMap.SimpleEntry<>(quantity, revenue));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching dish data: " + e.getMessage());
        }
        return dishData;
    }

    /**
     * Fetches the future booking predictions from the database.
     * @param daysToPredict The number of days for which to predict bookings.
     * @return The future booking predictions.
     */
    private Map<Date, Integer> fetchFutureBookingPredictions(int daysToPredict) {
        // First, get the historical data
        Map<Date, Integer> historicalData = fetchBookingData2();

        // Calculate the rate of change over the most recent period
        int daysForTrendCalculation = 7; // for example, use the last week to calculate trend
        List<Integer> values = new ArrayList<>(historicalData.values());
        int totalChange = 0;
        for (int i = values.size() - daysForTrendCalculation; i < values.size() - 1; i++) {
            totalChange += values.get(i + 1) - values.get(i);
        }
        int dailyChange = totalChange / (daysForTrendCalculation - 1);

        // Predict future bookings based on this trend
        Map<Date, Integer> futurePredictions = new HashMap<>();
        int lastValue = values.get(values.size() - 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Collections.max(historicalData.keySet()));
        for (int i = 1; i <= daysToPredict; i++) {
            calendar.add(Calendar.DATE, 1);
            lastValue += dailyChange; // apply the daily change to the last value
            futurePredictions.put(new Date(calendar.getTimeInMillis()), Math.max(0, lastValue)); // prevent negative predictions
        }
        return futurePredictions;
    }

    /**
     * Fetches the booking data from the database, summing up covers for each booking date.
     * @return The booking data.
     */
    private Map<Date, Integer> fetchBookingData2() {
        Map<Date, Integer> bookingData = new HashMap<>();
        String query = "SELECT BookingDate, SUM(Covers) AS TotalCovers FROM Bookings GROUP BY BookingDate";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Populate booking data
            while (resultSet.next()) {
                Date bookingDate = resultSet.getDate("BookingDate");
                int totalCovers = resultSet.getInt("TotalCovers");
                bookingData.put(bookingDate, totalCovers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingData;
    }
}
