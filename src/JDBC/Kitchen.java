package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Kitchen implements IKitchen {
    private final Connection connection;

    public Kitchen(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Ingredient> getDeliveryOrder() {
        List<Ingredient> ingredients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DeliveryOrders");

            while (resultSet.next()) {
                int ingredientId = resultSet.getInt("IngredientID");
                String ingredientName = resultSet.getString("IngredientName");
                int quantity = resultSet.getInt("Quantity");

                Ingredient ingredient = new Ingredient(ingredientId, ingredientName, quantity);
                ingredients.add(ingredient);
            }

//            if (resultSet.next()) {
//                HashMap<Ingredient, Integer> ingredientAmounts = new HashMap<>();
//
//                String deliveryDetails = "Delivery!";
//                deliveryOrder = new Order(ingredientAmounts, deliveryDetails);
//            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> getIngredientsAvailable() {
        List<Ingredient> ingredients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM IngredientsAvailable");

            while (resultSet.next()) {
                int ingredientID = resultSet.getInt("IngredientID");
                String ingredientName = resultSet.getString("IngredientName");
                int quantity = resultSet.getInt("Quantity");

                Ingredient ingredient = new Ingredient(ingredientID, ingredientName, quantity);
                ingredients.add(ingredient);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return ingredients;
    }

    @Override
    public boolean getMenuApproval(Menu menu, boolean forceApproval) {
        boolean isMenuApproved = false;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM IngredientsAvailable WHERE IngredientName IN (SELECT IngredientName FROM MenuItems)");

            if (resultSet.next() && resultSet.getInt(1) == menu.getDishes().size()) {
                if (forceApproval || resultSet.getInt(1) == menu.getDishes().size()) {
                    isMenuApproved = true;
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isMenuApproved;
    }

}
