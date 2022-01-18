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

public class OrderService extends Service<Order> {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final List<Order> orderList;
    private final OrderWriter orderWriter;

    public OrderService() throws IOException {
        super();
        OrderReader orderReader = new OrderReader();
        this.orderWriter = new OrderWriter();
        this.orderList = orderReader.read();
        logger.info("Initialized Order service");
    }

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

    @Override
    public void update(int id, Order itemToUpdate) {
        logger.warn("Order cannot be update");
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int id) {
        logger.warn("Finished order cannot be remove");
        throw new UnsupportedOperationException();
    }

    @Override
    public void show() {
        for (Order order : orderList) {
            System.out.println(order.toStringForList());
        }
    }

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

    public void sync() throws IOException {
        orderWriter.write(this.orderList);
    }
}
