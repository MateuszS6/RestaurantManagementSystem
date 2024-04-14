package JDBC;

import java.util.List;

/**
 * An interface outlining the front of house operations in the restaurant.
 * It handles tasks related to managing bookings, covers, and menu.
 */
public interface IFrontOfHouse {
    /**
     * Retrieves the current menu of the restaurant.
     *
     * @return The menu of the restaurant.
     */
    Menu getMenu();

    /**
     * Retrieves the total number of bookings made in the past year.
     *
     * @return The total number of annual bookings.
     */
    int getAnnualBookings();

    /**
     * Retrieves the total number of covers served in the past year.
     *
     * @return The total number of annual covers.
     */
    int getAnnualCovers();


    /**
     * Retrieves the average number of bookings for a specific day of the week.
     *
     * @param day a day of the week (an enum).
     * @return The average number of bookings for the specified day.
     */
    int getDayAverageBookings(WeekDay day);

    /**
     * Retrieves the average number of covers for a specific day of the week.
     *
     * @param day a day of the week (an enum).
     * @return The average number of covers for the specified day, specified through the argument.
     */
    int getDayAverageCovers(WeekDay day);

    /**
     * Sends an email notification when the number of bookings exceeds a predefined limit.
     */
    void sendLimitEmail(Email email);

    List<WinePairing> getWinePairings();
}