package JDBC;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Dish> dishes;

    public Menu() {
        dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        if (!isIdExists(dish.getId())) { // all dish IDs are unique and cannot be reused.
            dishes.add(dish); // if the ID does not exist (i,e it can be added, since it is unique).
        } else {
            System.out.println("Dish with ID " + dish.getId() + " already exists in the menu. Cannot add.");
        }
    }

    private boolean isIdExists(int id) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Method to remove a dish from the menu
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    // Method to get all dishes in the menu
    public List<Dish> getDishes() {
        return dishes;
    }

    public void print() {
        for (Dish dish : dishes) {
            System.out.println("ID: " + dish.getId());
            System.out.println("\tName: " + dish.getName());
            System.out.println("\tDescription: " + dish.getDescription());
            System.out.println("\tPrice: " + dish.getPrice());
            System.out.println("\tAllergen Info: " + dish.getAllergenInfo());
        }
    }
}
