package vn.com.tma.training.restaurant.exception;

/**
 * InvalidEnumValueException represents the error when there is no enum matched the value.
 */
public class InvalidEnumValueException extends RuntimeException {
    private final String enumName;

    /**
     * Constructor that set the enum name
     *
     * @param enumName The enum name of the exception
     */
    public InvalidEnumValueException(String enumName) {
        this.enumName = enumName;
    }

    @Override
    public String toString() {
        return "InvalidEnumValueException{" +
                "enumName='" + enumName + '\'' +
                '}';
    }
}
