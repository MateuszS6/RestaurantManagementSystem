package JDBC;

public class Dish {
    private int id;
    private String name;
    private String description;
    private double price;
    private String allergenInfo; // Optional field for allergen information

    public Dish(int id, String name, String description, double price, String allergenInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergenInfo = allergenInfo;
    }

    // Getters and setters for all properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // toString method to display MenuItem details

    public void print() {
        System.out.println("MenuItem{" +
                "id=" + id +
                ", name=" + name + ", " +
                "description=" + description + ", " +
                "price=" + price + ", " +
                "allergenInfo=" + allergenInfo + '}');
    }
}
