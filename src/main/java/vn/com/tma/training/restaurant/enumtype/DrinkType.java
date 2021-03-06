package vn.com.tma.training.restaurant.enumtype;

import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

/**
 * DrinkType defines the type of drink, which can be Soft drink or Alcohol.
 * SOFT_DRINK with value 0 represent type Soft drink
 * ALCOHOL with value 1 represent type Alcohol
 */
public enum DrinkType {
    SOFT_DRINK(Constant.SOFT_DRINK, 0),
    ALCOHOL(Constant.ALCOHOL, 1);

    private final String label;
    private final int value;

    /**
     * Constructor that set the label and value of a drink type.
     *
     * @param label The label of the drink type
     * @param value The value of the drink type
     */
    DrinkType(String label, int value) {
        this.label = label;
        this.value = value;
    }

    /**
     * Gets the enum that match the passed value.
     *
     * @param value The value that needs to be matched
     * @return The enum matched the value
     * @throws InvalidEnumValueException If there is no enum matched that value
     */
    public static DrinkType getDrinkType(int value) {
        for (DrinkType e : DrinkType.values()) {
            if (e.value == value) {
                return e;
            }
        }
        throw new InvalidEnumValueException(Constant.DRINK);
    }

    @Override
    public String toString() {
        return label;
    }
}
