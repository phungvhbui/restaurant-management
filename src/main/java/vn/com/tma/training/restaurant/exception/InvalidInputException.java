package vn.com.tma.training.restaurant.exception;

/**
 * InvalidInputException represents the error when the user input is mismatched from requirements.
 */
public class InvalidInputException extends RuntimeException {
    @Override
    public String toString() {
        return "InvalidInputException{}";
    }
}
