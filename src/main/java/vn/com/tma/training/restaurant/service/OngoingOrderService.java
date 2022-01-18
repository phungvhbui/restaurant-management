package vn.com.tma.training.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OngoingOrderService extends Service<Order> {
    private static final Logger logger = LoggerFactory.getLogger(OngoingOrderService.class);
    private final List<Order> orderList;

    public OngoingOrderService() {
        this.orderList = new ArrayList<>();
        logger.info("Initialized ongoing Order service");
    }

    @Override
    public Order get(int id) {
        try {
            Order order = orderList.get(id);
            logger.info("Found order with index: " + id);
            return order;
        } catch (IndexOutOfBoundsException e) {
            logger.warn("Order with index: " + id + " not found");
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void add(Order itemToAdd) throws IOException {
        orderList.add(itemToAdd);
        logger.info("Added item with index: " + (orderList.size() - 1));
    }

    @Override
    public void update(int id, Order itemToUpdate) {
        logger.warn("Order cannot be update");
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int id) {
        try {
            orderList.remove(id);
            logger.info("Removed item with index: " + id);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("Order with index: " + id + " not found");
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void show() {
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println("Order index: " + i);
            System.out.println(orderList.get(i).toStringForList());
        }
    }

    public void addItemToOrder(int id, MenuItem menuItem, int quantity) {
        for (int i = 0; i < orderList.size(); i++) {
            if (i == id) {
                orderList.get(i).orderItem(menuItem, quantity);
                logger.info("Added item " + menuItem.getId() + " to order with index " + id);
                return;
            }
        }
        logger.warn("Order with index: " + id + " not found");
        throw new EntityNotFoundException();
    }

    public void removeItemFromOrder(int id, MenuItem menuItem) {
        for (int i = 0; i < orderList.size(); i++) {
            if (i == id) {
                orderList.get(i).removeItem(menuItem);
                logger.info("Removed item " + menuItem.getId() + " from order with index " + id);
                return;
            }
        }
        logger.warn("Order with index: " + id + " not found");
        throw new EntityNotFoundException();
    }
}
