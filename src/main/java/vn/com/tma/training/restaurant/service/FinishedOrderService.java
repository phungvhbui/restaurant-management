package vn.com.tma.training.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.io.reader.OrderReader;
import vn.com.tma.training.restaurant.io.writer.OrderWriter;
import vn.com.tma.training.restaurant.util.Index;

import java.io.IOException;
import java.util.List;

/**
 * FinishedOrderService handles all operation with the list of finished Order.
 */
public class FinishedOrderService extends Service<Order> {
    private static final Logger logger = LoggerFactory.getLogger(FinishedOrderService.class);
    private final List<Order> orderList;
    private final OrderWriter orderWriter;

    /**
     * Default constructor without any params.
     * This constructor will read the file to initialize the list of MenuItem.
     *
     * @throws IOException If there is something wrong with files
     */
    public FinishedOrderService() throws IOException {
        super();
        OrderReader orderReader = new OrderReader();
        this.orderWriter = new OrderWriter();
        this.orderList = orderReader.read();
        logger.info("Initialized Order service");
    }

    /**
     * Gets the Order by identifier.
     *
     * @param id The identifier of the Order
     * @return The Order found
     * @throws EntityNotFoundException If there is no Order matched the id
     */
    @Override
    public Order get(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                logger.info("Found order with index: " + id);
                return order;
            }
        }
        logger.warn("Order with index: " + id + " not found");
        throw new EntityNotFoundException();
    }

    /**
     * Adds new Order.
     *
     * @param itemToAdd The Order to add
     * @throws IOException If there is something wrong with files
     */
    @Override
    public void add(Order itemToAdd) throws IOException {
        Index index = indexReader.read();
        itemToAdd.setId(index.getOrderIndex() + 1);
        index.setOrderIndex(index.getOrderIndex() + 1);
        orderList.add(itemToAdd);
        orderWriter.write(this.orderList);
        indexWriter.write(index);
        logger.info("Added item with index: " + itemToAdd.getId());
    }

    /**
     * Updates an Order.
     *
     * @param id           The identifier of the Order
     * @param itemToUpdate The Order to update
     * @throws UnsupportedOperationException This method is not supported for finished orders
     */
    @Override
    public void update(int id, Order itemToUpdate) {
        logger.warn("Order cannot be update");
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes an Order.
     *
     * @param id The identifier of the Order
     * @throws UnsupportedOperationException This method is not supported for finished orders
     */
    @Override
    public void remove(int id) {
        logger.warn("Finished order cannot be remove");
        throw new UnsupportedOperationException();
    }

    /**
     * Prints the list of finished Order to console.
     */
    @Override
    public void show() {
        for (Order order : orderList) {
            System.out.println(order.toStringForList());
        }
    }

    /**
     * Exports an Order to file
     *
     * @param id The identifier of the Order
     * @throws IOException             If there is something wrong with files
     * @throws EntityNotFoundException If there is no Order matched the id
     */
    public void export(int id) throws IOException {
        for (Order order : orderList) {
            if (order.getId() == id) {
                orderWriter.export(order);
                return;
            }
        }
        logger.warn("Order with index: " + id + " not found");
        throw new EntityNotFoundException();
    }

    /**
     * Syncs the list with the file by writing it to file
     *
     * @throws IOException If there is something wrong with files
     */
    public void sync() throws IOException {
        orderWriter.write(this.orderList);
    }
}
