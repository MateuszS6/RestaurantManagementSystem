package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnection {
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

    public DBConnection() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t06",
                "in2033t06_a",
                "PS7gGioYXOM")) {

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
}
