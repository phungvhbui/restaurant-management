package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

/**
 * ItemType defines the type of item in the menu, which can be a Dish or a Drink.
 * DISH with value 0 represent type Dish
 * DRINK with value 1 represent type Drink
 */
public enum ItemType {
    DISH(Constant.DISH, 0),
    DRINK(Constant.DRINK, 1);
    private final String type;
    private final int value;

    /**
     * Constructor that set the label and value of a drink type.
     *
     * @param type  The type of the item type
     * @param value The value of the item type
     */
    ItemType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the enum that match the passed value.
     *
     * @param value The value that needs to be matched
     * @return The enum matched the value
     * @throws InvalidEnumValueException If there is no enum matched that value
     */
    public static ItemType getMenuType(int value) {
        for (ItemType e : ItemType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.MENU);
    }

    @Override
    public String toString() {
        return type;
    }
}

