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
                menu.printMenu();
            }

            // FOH

            System.out.println("Front of house:");

            int annualBooking = frontOfHouse.getAnnualBooking();
            System.out.println("1: Annual Bookings: " + annualBooking);

            int annualCovers = frontOfHouse.getAnnualCovers();
            System.out.println("2: Annual Covers: " + annualCovers);

            int dayAverageBooking = frontOfHouse.getDayAverageBooking(WeekDay.MONDAY);
            System.out.println("3: Average Bookings on WEEKDAY: " + dayAverageBooking);
            // change this based on user input (any day of the week)

            int dayAverageCovers = frontOfHouse.getDayAverageCovers(WeekDay.MONDAY);
            System.out.println("4: Average Covers on WEEKDAY: " + dayAverageCovers);
            // change this based on user input (any day of the week)

            //TODO: Send email notification if needed
            //frontOfHouse.sendLimitEmail(new Email("recipient@example.com"));

            // Kitchen

            System.out.println("Kitchen:");

            Kitchen kitchen = new Kitchen(connection);

            Order deliveryOrder = kitchen.getDeliveryOrder();
            System.out.println("1: Delivery Order: " + deliveryOrder);

            System.out.println("2: Available Ingredients: " + kitchen.getIngredientsAvailable());

            boolean isMenuApproved = kitchen.getMenuApproval(menu,false);
            System.out.println("3: Is Menu Approved? " + isMenuApproved);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
