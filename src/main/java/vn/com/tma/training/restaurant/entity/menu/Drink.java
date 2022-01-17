package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.MenuType;

public class Drink extends MenuItem {
    private final DrinkType drinkType;
    private int stock;

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
