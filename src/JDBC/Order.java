package JDBC;

import java.util.HashMap;

public class Order {
    private HashMap<IngredientOrder, Integer> ingredientAmounts;
    private String deliveryDetails; // New field

    public Order(HashMap<IngredientOrder, Integer> ingredientAmounts, String deliveryDetails) {
        this.ingredientAmounts = ingredientAmounts;
        this.deliveryDetails = deliveryDetails;
    }

    public HashMap<IngredientOrder, Integer> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(HashMap<IngredientOrder, Integer> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder("Order Details:\n");
        for (IngredientOrder ingredientOrder : ingredientAmounts.keySet()) {
            int quantity = ingredientAmounts.get(ingredientOrder);
            details.append("- ").append(quantity).append("x ").append(ingredientOrder).append("\n");
        }
        return details.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Delivery Order:\n");
        sb.append(getOrderDetails()); // Include order details
        sb.append("Delivery Details: ").append(deliveryDetails).append("\n"); // Include delivery details
        return sb.toString();
    }
}
