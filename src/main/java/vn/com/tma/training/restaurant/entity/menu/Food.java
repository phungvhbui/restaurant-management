package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.FoodType;
import vn.com.tma.training.restaurant.enumtype.MenuType;

public class Food extends MenuItem {
    private final boolean isAvailable;
    private final FoodType mealType;

    public Food(int id, String name, String description, int unitPrice, String unit, MenuType menuType, boolean isAvailable, FoodType mealType) {
        super(id, name, description, unitPrice, unit, menuType);
        this.isAvailable = isAvailable;
        this.mealType = mealType;
    }

    public Food(String name, String description, int unitPrice, String unit, MenuType menuType, boolean isAvailable, FoodType mealType) {
        super(name, description, unitPrice, unit, menuType);
        this.isAvailable = isAvailable;
        this.mealType = mealType;
    }

    public FoodType getMealType() {
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
