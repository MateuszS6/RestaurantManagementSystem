package JDBC;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBConnection {
    private Connection connection;
    // Restaurant
    private Menu menu;
    private List<WinePairing> wines;
    // Inventory
    private List<Ingredient> stock;
    private List<Ingredient> deliveries;
    // Staff
    private List<Employee> staffHolidays;
    // Sales
    private int[] dayAverageBookings;
    private int[] dayAverageCovers;
    private int annualBookings;
    private int annualCovers;
    private Map<Date, Integer> bookingData;
    private Map<String, Map.Entry<Integer, BigDecimal>> dishData;
    private Map<Date, Integer> futureBookingPredictions;

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
            for (int i = 0; i < 7; i++) {
                dayAverageBookings[i] = frontOfHouse.getDayAverageBookings(intToWeekDay(i));
                dayAverageCovers[i] = frontOfHouse.getDayAverageCovers(intToWeekDay(i));
            }
            bookingData = fetchBookingData();
            dishData = fetchDishData();
            futureBookingPredictions = fetchFutureBookingPredictions(14); // For example, predicting the next 30 days

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        DBConnection test = new DBConnection();
//        test.menu.print();
//        System.out.println(test.stock);
//        System.out.println(test.deliveries);
//        System.out.println(test.staffHolidays);
    }

    public Menu getMenu() {
        return menu;
    }

    public List<WinePairing> getWines() {
        return wines;
    }

    public String getAnnualBookings() {
        return String.valueOf(annualBookings);
    }

    public String getAnnualCovers() {
        return String.valueOf(annualCovers);
    }

    public String getDayAverageBookings(int dayIndex) {
        return String.valueOf(dayAverageBookings[dayIndex]);
    }

    public String getDayAverageCovers(int dayIndex) {
        return String.valueOf(dayAverageCovers[dayIndex]);
    }

    public List<Ingredient> getStock() {
        return stock;
    }

    public List<Ingredient> getDeliveries() {
        return deliveries;
    }

    public List<Employee> getStaffHolidays() {
        return staffHolidays;
    }

    public Map<Date, Integer> getBookingData() {
        return bookingData;
    }

    public Map<String, Map.Entry<Integer, BigDecimal>> getDishData() {
        return dishData;
    }

    public Map<Date, Integer> getFutureBookingPredictions() {
        return futureBookingPredictions;
    }

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

    private Map<Date, Integer> fetchBookingData() {
        Map<Date, Integer> bookings = new HashMap<>();
        String sqlQuery = "SELECT BookingDate, Covers FROM Bookings";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

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

    private Map<Date, Integer> fetchBookingData2() {
        Map<Date, Integer> bookingData = new HashMap<>();
        String query = "SELECT BookingDate, SUM(Covers) AS TotalCovers FROM Bookings GROUP BY BookingDate";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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
