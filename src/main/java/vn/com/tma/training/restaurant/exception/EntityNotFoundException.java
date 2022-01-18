package vn.com.tma.training.restaurant.exception;

/**
 * EntityNotFoundException represents the error when the entity needed is not found in the list.
 */
public class EntityNotFoundException extends RuntimeException {
    @Override
    public String toString() {
        return "EntityNotFoundException{}";
    }
}
