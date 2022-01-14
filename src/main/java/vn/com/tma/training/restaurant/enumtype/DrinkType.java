package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;

public enum DrinkType implements MenuEnum {
    SOFT_DRINK("Soft drink", 0),
    ALCOHOL("Alcohol", 1);

    private final String label;
    private final int value;

    DrinkType(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public static DrinkType getDrinkType(int value) throws InvalidEnumValueException {
        for (DrinkType e : DrinkType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException("Drink");
    }

    public String getDisplayName() {
        return label;
    }
}
