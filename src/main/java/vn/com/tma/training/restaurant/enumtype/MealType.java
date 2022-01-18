package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

/**
 * MealType defines the meal type of dish, which can be Breakfast, Lunch or Dinner.
 * BREAKFAST with value 0 represent type Breakfast
 * LUNCH with value 1 represent type Lunch
 * DINNER with value 1 represent type Dinner
 */
public enum MealType {
    BREAKFAST(Constant.BREAKFAST, 0),
    LUNCH(Constant.LUNCH, 1),
    DINNER(Constant.DINNER, 2);

    private final String meal;
    private final int value;

    /**
     * Constructor that set the label and value of a drink type.
     *
     * @param meal  The meal type of the item type
     * @param value The value of the item type
     */
    MealType(String meal, int value) {
        this.meal = meal;
        this.value = value;
    }

    /**
     * Gets the enum that match the passed value.
     *
     * @param value The value that needs to be matched
     * @return The enum matched the value
     * @throws InvalidEnumValueException If there is no enum matched that value
     */
    public static MealType getFoodType(int value) {
        for (MealType e : MealType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.DISH);
    }

    @Override
    public String toString() {
        return meal;
    }
}
