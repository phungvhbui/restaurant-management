package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.ItemType;

/**
 * Dish class represents a drink in the menu.
 * Dish has all the information in MenuItem, a stock number and its drink type (Soft drink or Alcohol).
 */

public class Drink extends MenuItem {
    private final DrinkType drinkType;
    private int stock;

    /**
     * Constructor that set all information of a drink.
     *
     * @param id          The identifier of the drink
     * @param name        The name of the drink
     * @param description The description of the drink
     * @param unitPrice   The price of a single unit of the drink
     * @param unitType    The unit type of the drink (can, bottle, bowl...)
     * @param itemType    The type of drink, should be a Drink
     * @param stock       The number of stock left of the drink
     * @param drinkType   The drink type of the drink, can be Soft drink or Alcohol
     */
    public Drink(int id, String name, String description, int unitPrice, String unitType, ItemType itemType, int stock, DrinkType drinkType) {
        super(id, name, description, unitPrice, unitType, itemType);
        this.stock = stock;
        this.drinkType = drinkType;
    }
    
    /**
     * Constructor that copy basic information from a MenuItem and add additional attributes
     *
     * @param menuItem  The item that will be copied
     * @param stock     The number of stock left of the drink
     * @param drinkType The drink type of the drink, can be Soft drink or Alcohol
     */
    public Drink(MenuItem menuItem, int stock, DrinkType drinkType) {
        super(menuItem);
        this.stock = stock;
        this.drinkType = drinkType;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n             "
                + "Name: " + name + "\n             "
                + "Description: " + description + "\n             "
                + "Price: " + unitPrice + "/" + unitType + "\n             "
                + "Stock: " + stock + "\n             "
                + "Availability: " + (stock > 0 ? "yes" : "no");
    }
}
