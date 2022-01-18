package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.io.reader.IndexReader;
import vn.com.tma.training.restaurant.io.writer.IndexWriter;

import java.io.IOException;

/**
 * The interface that defines similar behaviour of all Services
 *
 * @param <T> The generic type of the object that service handing
 */
public abstract class Service<T> {
    protected IndexReader indexReader;
    protected IndexWriter indexWriter;

    /**
     * Default constructor without any params.
     * This constructor will new IndexReader and IndexWriter.
     */
    public Service() {
        this.indexReader = new IndexReader();
        this.indexWriter = new IndexWriter();
    }

    /**
     * Gets the item by identifier
     *
     * @param id The identifier of the item
     * @return The item found
     * @throws EntityNotFoundException If there is no item matched the id
     */
    public abstract T get(int id);

    /**
     * Adds new item
     *
     * @param itemToAdd The item to add
     * @throws IOException If there is something wrong with files
     */
    public abstract void add(T itemToAdd) throws IOException;

    /**
     * Updates an item
     *
     * @param id           The identifier of the item
     * @param itemToUpdate The item to update
     * @throws IOException             If there is something wrong with files
     * @throws EntityNotFoundException If there is no item matched the id
     */
    public abstract void update(int id, T itemToUpdate) throws IOException;

    /**
     * Deletes an item
     *
     * @param id The identifier of the item
     * @throws IOException             If there is something wrong with files
     * @throws EntityNotFoundException If there is no item matched the id
     */
    public abstract void remove(int id) throws IOException;

    /**
     * Prints the list to console
     */
    public abstract void show();
}
