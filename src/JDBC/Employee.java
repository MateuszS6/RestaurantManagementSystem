package JDBC;

public class Employee {
    private int ID;
    private String name;
    private String role;
    private String holidayDates;

    public Employee(int ID, String name, String role, String holidayDates) {
        this.ID = ID;
        this.name = name;
        this.role = role;
        this.holidayDates = holidayDates;
    }

    public int getID() {
        return ID;
    }

    public String[] getInfo() {
        return new String[] {String.valueOf(ID), name, role, holidayDates};
    }
}
