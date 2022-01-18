package vn.com.tma.training.restaurant.io.writer;

import java.io.IOException;

/**
 * Writer is the generic interface that defines behaviour of all Writer
 *
 * @param <T> The generic type of the input object
 */
public interface Writer<T> {
    /**
     * Writes the data that passed in
     *
     * @param data The data that needs to be saved
     * @throws IOException If there is something wrong when writing file
     */
    void write(T data) throws IOException;
}
