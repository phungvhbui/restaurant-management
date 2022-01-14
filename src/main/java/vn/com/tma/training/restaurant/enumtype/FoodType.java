package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;

public enum FoodType implements MenuEnum {
    BREAKFAST("Breakfast", 0),
    LUNCH("Lunch", 1),
    DINNER("Dinner", 2);

    private final String meal;
    private final int value;

    FoodType(String meal, int value) {
        this.meal = meal;
        this.value = value;
    }

    public static FoodType getFoodType(int value) throws InvalidEnumValueException {
        for (FoodType e : FoodType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException("Food");
    }

    public String getDisplayName() {
        return meal;
    }
}
