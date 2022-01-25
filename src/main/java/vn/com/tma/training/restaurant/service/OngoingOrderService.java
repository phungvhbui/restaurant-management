package vn.com.tma.training.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * OngoingOrderService handles all operation with the list of ongoing Order.
 */
public class OngoingOrderService extends Service<Order> {
    private static final Logger logger = LoggerFactory.getLogger(OngoingOrderService.class);
    private final List<Order> orderList;

    /**
     * Default constructor without any params.
     */
    public OngoingOrderService() {
        this.orderList = new ArrayList<>();
        if (logger.isDebugEnabled()) {
            logger.info("Initialized ongoing Order service");
        }
    }

    /**
     * Gets the Order by identifier.
     *
     * @param id The index of the Order
     * @return The Order found
     * @throws EntityNotFoundException If there is no MenuItem matched the id
     */
    @Override
    public Order get(int id) {
        try {
            Order order = orderList.get(id);
            if (logger.isDebugEnabled()) {
                logger.info("Found order with index: " + id);
            }
            return order;
        } catch (IndexOutOfBoundsException e) {
            if (logger.isDebugEnabled()) {
                logger.warn("Order with index: " + id + " not found");
            }
            throw new EntityNotFoundException();
        }
    }

    /**
     * Adds new Order.
     *
     * @param itemToAdd The Order to add
     */
    @Override
    public void add(Order itemToAdd) {
        orderList.add(itemToAdd);
        if (logger.isDebugEnabled()) {
            logger.info("Added item with index: " + (orderList.size() - 1));
        }
    }

    /**
     * Updates an Order.
     *
     * @param id           The index of the Order
     * @param itemToUpdate The Order to update
     * @throws UnsupportedOperationException This method is not supported for ongoing orders
     */
    @Override
    public void update(int id, Order itemToUpdate) {
        if (logger.isDebugEnabled()) {
            logger.warn("Order cannot be update");
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes an Order.
     *
     * @param id The index of the Order
     * @throws EntityNotFoundException If there is no Order matched the id
     */
    @Override
    public void remove(int id) {
        try {
            orderList.remove(id);
            if (logger.isDebugEnabled()) {
                logger.info("Removed item with index: " + id);
            }
        } catch (IndexOutOfBoundsException e) {
            if (logger.isDebugEnabled()) {
                logger.warn("Order with index: " + id + " not found");
            }
            throw new EntityNotFoundException();
        }
    }

    /**
     * Prints the list of ongoing order to console.
     */
    @Override
    public void show() {
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println("Order index: " + i);
            System.out.println(orderList.get(i).toStringForList());
        }
    }

    /**
     * Add an item to an ongoing Order.
     * If the item is already ordered, this method will increase the quantity.
     * Else, this method will add a new item to the order
     *
     * @param id       The index of the Order
     * @param menuItem The MenuItem to add
     * @param quantity The ordered quantity
     */
    public void addItemToOrder(int id, MenuItem menuItem, int quantity) {
        for (int i = 0; i < orderList.size(); i++) {
            if (i == id) {
                orderList.get(i).orderItem(menuItem, quantity);
                if (logger.isDebugEnabled()) {
                    logger.info("Added item " + menuItem.getId() + " to order with index " + id);
                }
                return;
            }
        }
        if (logger.isDebugEnabled()) {
            logger.warn("Order with index: " + id + " not found");
        }
        throw new EntityNotFoundException();
    }

    /**
     * Remove an item from an ongoing Order.
     *
     * @param id       The index of the Order
     * @param menuItem The MenuItem to remove
     */
    public void removeItemFromOrder(int id, MenuItem menuItem) {
        for (int i = 0; i < orderList.size(); i++) {
            if (i == id) {
                orderList.get(i).removeItem(menuItem);
                if (logger.isDebugEnabled()) {
                    logger.info("Removed item " + menuItem.getId() + " from order with index " + id);
                }
                return;
            }
        }
        if (logger.isDebugEnabled()) {
            logger.warn("Order with index: " + id + " not found");
        }
        throw new EntityNotFoundException();
    }
}
