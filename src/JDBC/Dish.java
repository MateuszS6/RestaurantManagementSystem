package JDBC;

/**
 * Represents a dish.
 */
public class Dish {
    /**
     * The ID of the dish.
     */
    private int ID;

    /**
     * The name of the dish.
     */
    private String name;

    /**
     * The description of the dish.
     */
    private String description;

    /**
     * The price of the dish.
     */
    private double price;

    /**
     * Optional allergen information for the dish.
     */
    private String allergenInfo;

    /**
     * Constructs a Dish object with the given ID, name, description, price, and allergen information.
     *
     * @param id           the ID of the dish
     * @param name         the name of the dish
     * @param description  the description of the dish
     * @param price        the price of the dish
     * @param allergenInfo the allergen information for the dish
     */
    public Dish(int id, String name, String description, double price, String allergenInfo) {
        this.ID = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergenInfo = allergenInfo;
    }

    /**
     * Gets the ID of the dish.
     *
     * @return the ID of the dish
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the dish.
     *
     * @param ID the ID of the dish
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the dish.
     *
     * @return the name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the dish.
     *
     * @param name the name of the dish
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the dish.
     *
     * @return the description of the dish
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the dish.
     *
     * @param description the description of the dish
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the dish.
     *
     * @return the price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the dish.
     *
     * @param price the price of the dish
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the allergen information for the dish.
     *
     * @return the allergen information for the dish
     */
    public String getAllergenInfo() {
        return allergenInfo;
    }

    /**
     * Sets the allergen information for the dish.
     *
     * @param allergenInfo the allergen information for the dish
     */
    public void setAllergenInfo(String allergenInfo) {
        this.allergenInfo = allergenInfo;
    }

    /**
     * Gets an array containing information about the dish.
     *
     * @return an array containing the ID, name, description, price, and allergen information of the dish
     */
    public String[] getInfo() {
        return new String[]{String.valueOf(ID), name, description, '£' + String.valueOf((int) price), allergenInfo};
    }
}
