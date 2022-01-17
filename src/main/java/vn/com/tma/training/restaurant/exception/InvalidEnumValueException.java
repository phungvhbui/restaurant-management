package vn.com.tma.training.restaurant.exception;

public class InvalidEnumValueException extends RuntimeException {
    private final String enumName;

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
