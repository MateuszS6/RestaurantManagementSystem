package JDBC;

public class Dish {
    private int ID;
    private String name;
    private String description;
    private double price;
    private String allergenInfo; // Optional field for allergen information

    public Dish(int id, String name, String description, double price, String allergenInfo) {
        this.ID = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergenInfo = allergenInfo;
    }

    // Getters and setters for all properties

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAllergenInfo() {
        return allergenInfo;
    }

    public void setAllergenInfo(String allergenInfo) {
        this.allergenInfo = allergenInfo;
    }

    public String[] getInfo() {
        return new String[]{String.valueOf(ID), name, description, '£' + String.valueOf((int) price), allergenInfo};
    }

    // For debug
    public void print() {
        System.out.println("MenuItem{" +
                "id=" + ID +
                ", name=" + name + ", " +
                "description=" + description + ", " +
                "price=" + price + ", " +
                "allergenInfo=" + allergenInfo + '}');
    }
}
