package vn.com.tma.training.restaurant.entity.order;

import vn.com.tma.training.restaurant.entity.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Order class represents an order in the restaurant.
 * Order has its basic information: id, table number, total price, ordered items and their corresponding quantity.
 */

public class Order {
    private final HashMap<MenuItem, Integer> orderedItems;
    private final LocalDateTime orderedTime;
    private final int tableNumber;
    private int id;
    private int totalPrice;

    /**
     * Constructor that set the table number of an order.
     * This constructor is used when an order is first created.
     *
     * @param tableNumber The table number of the order
     */
    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.orderedItems = new HashMap<>();
        this.orderedTime = LocalDateTime.now();
        calculateOrder();
    }

    /**
     * Constructor that set all information of an order.
     *
     * @param id           The identifier of the order
     * @param orderedItems The map of ordered items and their quantity
     * @param orderedTime  The created time of the order
     * @param tableNumber  The table number of the order
     * @param totalPrice   The total price of the order
     */
    public Order(int id, HashMap<MenuItem, Integer> orderedItems, LocalDateTime orderedTime, int tableNumber, int totalPrice) {
        this.id = id;
        this.orderedItems = orderedItems;
        this.orderedTime = orderedTime;
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
    }

    /**
     * Adds an item to the order.
     * If the item is already ordered, this method will increase the quantity.
     * Else, this method will add a new item to the order.
     *
     * @param item     The ordered item
     * @param quantity The ordered quantity
     */
    public void orderItem(MenuItem item, int quantity) {
        if (orderedItems.containsKey(item)) {
            orderedItems.replace(item, orderedItems.get(item) + quantity);
        }
        orderedItems.put(item, quantity);
        calculateOrder();
    }

    /**
     * Removes an item from the order
     *
     * @param item The item that needs to be removed.
     */
    public void removeItem(MenuItem item) {
        orderedItems.remove(item);
        calculateOrder();
    }

    /**
     * Calculates the total price of the order.
     * This method is used when there is an added item or a removed item.
     */
    private void calculateOrder() {
        int total = 0;
        for (Map.Entry<MenuItem, Integer> set : orderedItems.entrySet()) {
            total += set.getKey().getUnitPrice() * set.getValue();
        }
        this.totalPrice = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<MenuItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: ").append(id).append("\n");
        stringBuilder.append("Table: ").append(tableNumber).append("\n");
        stringBuilder.append("Items: ").append("\n");
        for (Map.Entry<MenuItem, Integer> set : orderedItems.entrySet()) {
            MenuItem item = set.getKey();
            int quantity = set.getValue();
            stringBuilder.append("    ").append(item).append("\n");
            stringBuilder.append("        Quantity: ").append(quantity).append("\n");
        }
        stringBuilder.append("Time: ").append(orderedTime).append("\n");
        stringBuilder.append("Total price: ").append(totalPrice).append("\n");
        return stringBuilder.toString();
    }

    /**
     * Generates a simple string that represents an order, which has: id, table name and total price.
     *
     * @return The generated string
     */
    public String toStringForList() {
        return "ID: " + id + "\n" +
                "  Table: " + tableNumber + "\n" +
                "  Total price: " + totalPrice + "\n";
    }
}
