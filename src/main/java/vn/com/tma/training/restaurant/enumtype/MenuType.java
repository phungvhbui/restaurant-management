package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

public enum MenuType implements MenuEnum {
    FOOD(Constant.FOOD, 0),
    DRINK(Constant.DRINK, 1);
    private final String type;
    private final int value;

    MenuType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public static MenuType getMenuType(int value) {
        for (MenuType e : MenuType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.MENU);
    }

    public String getDisplayName() {
        return type;
    }
}

