package JDBC;

/**
 * Represents an employee.
 */
public class Employee {
    /**
     * The ID of the employee.
     */
    private int ID;

    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The role of the employee.
     */
    private String role;

    /**
     * The holiday dates of the employee.
     */
    private String holidayDates;

    /**
     * Constructs an Employee object with the given ID, name, role, and holiday dates.
     *
     * @param ID           the ID of the employee
     * @param name         the name of the employee
     * @param role         the role of the employee
     * @param holidayDates the holiday dates of the employee
     */
    public Employee(int ID, String name, String role, String holidayDates) {
        this.ID = ID;
        this.name = name;
        this.role = role;
        this.holidayDates = holidayDates;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return the ID of the employee
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets an array containing the information of the employee.
     *
     * @return an array containing the ID, name, role, and holiday dates of the employee
     */
    public String[] getInfo() {
        return new String[]{String.valueOf(ID), name, role, holidayDates};
    }
}
