package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.io.reader.IndexReader;
import vn.com.tma.training.restaurant.io.writer.IndexWriter;

import java.io.IOException;

public abstract class Service<T> {
    protected IndexReader indexReader;
    protected IndexWriter indexWriter;

    public Service() {
        this.indexReader = new IndexReader();
        this.indexWriter = new IndexWriter();
    }

    public abstract T get(int id);

    public abstract void add(T itemToAdd) throws IOException;

    public abstract void update(int id, T itemToUpdate) throws IOException;

    public abstract void remove(int id) throws IOException;

    public abstract void show();
}
