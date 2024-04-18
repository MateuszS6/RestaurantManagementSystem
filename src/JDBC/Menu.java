package JDBC;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the menu of a restaurant.
 */
public class Menu {
    /**
     * A list containing all the dishes in the menu.
     */
    private final List<Dish> dishes = new ArrayList<>();

    /**
     * Retrieves all dishes in the menu.
     *
     * @return The list of dishes.
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Adds a new dish to the menu.
     *
     * @param dish The dish to be added.
     */
    public void addDish(Dish dish) {
        if (!isIdExists(dish.getID())) { // all dish IDs are unique and cannot be reused.
            dishes.add(dish); // if the ID does not exist (i.e., it can be added, since it is unique).
        } else {
            System.out.println("Dish with ID " + dish.getID() + " already exists in the menu. Cannot add.");
        }
    }

    /**
     * Removes a dish from the menu.
     *
     * @param dish The dish to be removed.
     */
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    /**
     * Checks if a dish with the specified ID already exists in the menu.
     *
     * @param id The ID of the dish to check.
     * @return True if the dish with the specified ID exists, otherwise false.
     */
    private boolean isIdExists(int id) {
        for (Dish dish : dishes) {
            if (dish.getID() == id) {
                return true;
            }
        }
        return false;
    }
}
