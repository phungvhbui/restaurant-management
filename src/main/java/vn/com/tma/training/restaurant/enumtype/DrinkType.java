package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

public enum DrinkType implements MenuEnum {
    SOFT_DRINK(Constant.SOFT_DRINK, 0),
    ALCOHOL(Constant.ALCOHOL, 1);

    private final String label;
    private final int value;

    DrinkType(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public static DrinkType getDrinkType(int value) {
        for (DrinkType e : DrinkType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.DRINK);
    }

    public String getDisplayName() {
        return label;
    }
}
