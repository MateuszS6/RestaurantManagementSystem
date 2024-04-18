package JDBC;

/**
 * Represents an ingredient.
 */
public class Ingredient {
    /**
     * The ID of the ingredient.
     */
    private int ID;

    /**
     * The name of the ingredient.
     */
    private String name;

    /**
     * The quantity of the ingredient.
     */
    private int quantity;

    /**
     * Constructs an Ingredient object with the given ID, name, and quantity.
     *
     * @param ingredientId   the ID of the ingredient
     * @param ingredientName the name of the ingredient
     * @param quantity       the quantity of the ingredient
     */
    public Ingredient(int ingredientId, String ingredientName, int quantity) {
        this.ID = ingredientId;
        this.name = ingredientName;
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the ingredient.
     *
     * @return the ID of the ingredient
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the ingredient.
     *
     * @param ID the ID of the ingredient
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param name the name of the ingredient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantity of the ingredient.
     *
     * @return the quantity of the ingredient
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity the quantity of the ingredient
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets an array containing the information of the ingredient.
     *
     * @return an array containing the ID, name, and quantity of the ingredient
     */
    public String[] getInfo() {
        return new String[]{String.valueOf(ID), name, String.valueOf(quantity)};
    }
}

