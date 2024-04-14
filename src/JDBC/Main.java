package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// make sure you have the library linked and the vpn running to get the DB to work - H

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t06", "in2033t06_a", "PS7gGioYXOM")) {
            FrontOfHouse frontOfHouse = new FrontOfHouse(connection);
            Menu menu = frontOfHouse.getMenu();

            if (menu != null) {
                System.out.println("Menu:");
                menu.print();
            }

            // FOH

            System.out.println("Front of house");
            System.out.println("1 - Annual Bookings: " + frontOfHouse.getAnnualBookings());
            System.out.println("2 - Annual Covers: " + frontOfHouse.getAnnualCovers());
            // Change this based on user input (any day of the week)
            System.out.println("3 - Average Bookings on MONDAY: " + frontOfHouse.getDayAverageBookings(WeekDay.MONDAY));
            // Change this based on user input (any day of the week)
            System.out.println("4 - Average Covers on MONDAY: " + frontOfHouse.getDayAverageCovers(WeekDay.MONDAY));
            //TODO: Send email notification if needed
            //frontOfHouse.sendLimitEmail(new Email("recipient@example.com"));

            // Kitchen
            System.out.println("Kitchen");
            Kitchen kitchen = new Kitchen(connection);
            System.out.println("1 - Delivery Order: " + kitchen.getDeliveryOrder());
            System.out.println("2 - Available Ingredients: " + kitchen.getIngredientsAvailable());
            System.out.println("3 - Is Menu Approved? " + kitchen.getMenuApproval(menu,false));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
