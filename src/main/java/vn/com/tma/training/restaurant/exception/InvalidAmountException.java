package vn.com.tma.training.restaurant.exception;

/**
 * InvalidAmountException represents the error when there is not enough stock in the menu for an ordered item.
 */
public class InvalidAmountException extends RuntimeException {
    @Override
    public String toString() {
        return "InvalidAmountException{}";
    }
}
