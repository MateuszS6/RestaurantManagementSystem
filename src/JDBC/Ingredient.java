package JDBC;

public class Ingredient {
    private int ID;
    private String name;
    private int quantity;

    public Ingredient(int ingredientId, String ingredientName, int quantity) {
        this.ID = ingredientId;
        this.name = ingredientName;
        this.quantity = quantity;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String[] getInfo() {
        return new String[]{String.valueOf(ID), name, String.valueOf(quantity)};
    }

    public void print() {
        System.out.println("IngredientID: " + ID + "\tIngredient Name: " + name + "\tQuantity: " + quantity);
    }
}
