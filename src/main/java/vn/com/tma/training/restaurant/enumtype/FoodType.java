package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

public enum FoodType implements MenuEnum {
    BREAKFAST(Constant.BREAKFAST, 0),
    LUNCH(Constant.LUNCH, 1),
    DINNER(Constant.DINNER, 2);

    private final String meal;
    private final int value;

    FoodType(String meal, int value) {
        this.meal = meal;
        this.value = value;
    }

    public static FoodType getFoodType(int value) {
        for (FoodType e : FoodType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.FOOD);
    }

    public String getDisplayName() {
        return meal;
    }
}
