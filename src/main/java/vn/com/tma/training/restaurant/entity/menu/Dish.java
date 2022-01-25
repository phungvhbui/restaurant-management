package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.ItemType;
import vn.com.tma.training.restaurant.enumtype.MealType;

/**
 * Dish class represents a dish in the menu.
 * Dish has all the information in MenuItem, an availability status and its meal type (Breakfast, Lunch or Dinner).
 */
public class Dish extends MenuItem {
    private final boolean isAvailable;
    private final MealType mealType;

    /**
     * Constructor that set all information of a dish.
     *
     * @param id          The identifier of the dish
     * @param name        The name of the dish
     * @param description The description of the dish
     * @param unitPrice   The price of a single unit of the dish
     * @param unitType    The unit type of the dish (can, bottle, bowl...)
     * @param itemType    The type of item, should be a Dish
     * @param isAvailable The availability status of the dish
     * @param mealType    The meal type of the dish, can be Breakfast, Lunch or Dinner.
     */
    public Dish(int id, String name, String description, int unitPrice, String unitType, ItemType itemType, boolean isAvailable, MealType mealType) {
        super(id, name, description, unitPrice, unitType, itemType);
        this.isAvailable = isAvailable;
        this.mealType = mealType;
    }

    /**
     * Constructor that copy basic information from a MenuItem and add additional attributes
     *
     * @param item        The item that will be copied
     * @param isAvailable The availability status of the dish
     * @param mealType    The meal type of the dish, can be Breakfast, Lunch or Dinner.
     */
    public Dish(MenuItem item, boolean isAvailable, MealType mealType) {
        super(item);
        this.isAvailable = isAvailable;
        this.mealType = mealType;
    }

    public MealType getMealType() {
        return mealType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n             "
                + "Name: " + name + "\n             "
                + "Description: " + description + "\n             "
                + "Price: " + unitPrice + "/" + unitType + "\n             "
                + "Availability: " + (isAvailable ? "yes" : "no");
    }
}
