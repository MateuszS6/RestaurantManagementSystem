package JDBC;

import java.util.HashMap;

public class Order {
    private HashMap<Ingredient, Integer> ingredientAmounts;
    private String deliveryDetails; // New field

    public Order(HashMap<Ingredient, Integer> ingredientAmounts, String deliveryDetails) {
        this.ingredientAmounts = ingredientAmounts;
        this.deliveryDetails = deliveryDetails;
    }

    public HashMap<Ingredient, Integer> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(HashMap<Ingredient, Integer> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder("Order Details: ");
        for (Ingredient ingredient : ingredientAmounts.keySet()) {
            int quantity = ingredientAmounts.get(ingredient);
            details.append("- ").append(quantity).append("x ").append(ingredient).append("\n");
        }
        return details.toString();
    }

    public void print() {
        // Include order details
        System.out.println(getOrderDetails() + "Delivery Details: " + deliveryDetails);
    }
}
