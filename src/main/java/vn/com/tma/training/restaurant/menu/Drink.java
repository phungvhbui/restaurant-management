package vn.com.tma.training.restaurant.menu;

import vn.com.tma.training.restaurant.enumType.DrinkType;
import vn.com.tma.training.restaurant.enumType.MenuType;

public class Drink extends MenuItem {
    private int stock;
    private DrinkType drinkType;

    public Drink(int id, String name, String description, int unitPrice, String unit, MenuType menuType, int stock, DrinkType drinkType) {
        super(id, name, description, unitPrice, unit, menuType);
        this.stock = stock;
        this.drinkType = drinkType;
    }

    public Drink(String name, String description, int unitPrice, String unit, MenuType menuType, int stock, DrinkType drinkType) {
        super(name, description, unitPrice, unit, menuType);
        this.stock = stock;
        this.drinkType = drinkType;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n             "
                + "Name: " + name + "\n             "
                + "Description: " + description + "\n             "
                + "Price: " + unitPrice + "/" + unit + "\n             "
                + "Stock: " + stock + "\n             "
                + "Availability: " + (stock > 0 ? "yes" : "no");
    }

    public int getStock() {
        return stock;
    }
}
