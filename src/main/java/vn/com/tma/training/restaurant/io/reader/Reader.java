package vn.com.tma.training.restaurant.io.reader;

import java.io.IOException;

public interface Reader<T> {
    T read() throws IOException;
}
