package vn.com.tma.training.restaurant.menu;

import vn.com.tma.training.restaurant.enumtype.FoodType;
import vn.com.tma.training.restaurant.enumtype.MenuType;

public class Food extends MenuItem {
    private boolean isAvailable;
    private FoodType mealType;

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
                + "Price: " + unitPrice + "/" + unit + "\n             "
                + "Availability: " + (isAvailable ? "yes" : "no");
    }
}
