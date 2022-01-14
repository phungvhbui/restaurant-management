package vn.com.tma.training.restaurant.enumType;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;

public enum MenuType implements MenuEnum {
    FOOD("Food", 0),
    DRINK("Drink", 1);
    private final String type;
    private final int value;

    MenuType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public static MenuType getMenuType(int value) throws InvalidEnumValueException {
        for (MenuType e : MenuType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException("Menu");
    }

    public String getDisplayName() {
        return type;
    }
}

