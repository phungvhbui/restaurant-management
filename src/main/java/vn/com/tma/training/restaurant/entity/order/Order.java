package vn.com.tma.training.restaurant.entity.order;

import vn.com.tma.training.restaurant.entity.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final HashMap<MenuItem, Integer> orderedItems;
    private final LocalDateTime orderedTime;
    private int id;
    private int totalPrice;

    public Order() {
        orderedItems = new HashMap<>();
        orderedTime = LocalDateTime.now();
        calculateOrder();
    }

    public Order(int orderId, HashMap<MenuItem, Integer> orderedItems, LocalDateTime orderedTime, int totalPrice) {
        this.id = orderId;
        this.orderedItems = orderedItems;
        this.orderedTime = orderedTime;
        this.totalPrice = totalPrice;
    }

    public void orderItem(MenuItem item, int quantity) {
        if (orderedItems.containsKey(item)) {
            orderedItems.replace(item, orderedItems.get(item) + quantity);
        }
        orderedItems.put(item, quantity);
        calculateOrder();
    }

    private void calculateOrder() {
        int total = 0;
        for (Map.Entry<MenuItem, Integer> set : orderedItems.entrySet()) {
            total += set.getKey().getUnitPrice() * set.getValue();
        }
        this.totalPrice = total;
    }

    public void exportOrder(String filename) {
        // TODO: Implement this
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: ").append(id).append("\n");
        stringBuilder.append("Items: ").append("\n");
        for (Map.Entry<MenuItem, Integer> set : orderedItems.entrySet()) {
            MenuItem order = set.getKey();
            int quantity = set.getValue();
            stringBuilder.append("    ").append(order.toStringForOrder()).append(" - Quantity: ").append(quantity).append("\n");
        }
        stringBuilder.append("Time: ").append(orderedTime).append("\n");
        stringBuilder.append("Total price: ").append(totalPrice).append("\n");
        return stringBuilder.toString();
    }
}