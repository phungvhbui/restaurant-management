package vn.com.tma.training.restaurant.io.writer;

import java.io.IOException;

public interface Writer<T> {
    void write(T data) throws IOException;
}
