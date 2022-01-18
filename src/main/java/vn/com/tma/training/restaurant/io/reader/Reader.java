package vn.com.tma.training.restaurant.io.reader;

import java.io.IOException;

/**
 * Reader is the generic interface that defines behaviour of all Reader
 *
 * @param <T> The generic type of the output object
 */
public interface Reader<T> {
    /**
     * Reads the data and returns the corresponding object.
     *
     * @return The object that was read
     * @throws IOException If there is something wrong when reading
     */
    T read() throws IOException;
}
