package JDBC;

public class IngredientOrder {
    private int ingredientId;
    private String ingredientName;
    private int quantity;

    public IngredientOrder(int ingredientId, String ingredientName, int quantity) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientID: " + ingredientId + ", IngredientName: " + ingredientName + ", Quantity: " + quantity;
    }
}
