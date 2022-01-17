package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.io.reader.OrderReader;
import vn.com.tma.training.restaurant.io.writer.OrderWriter;
import vn.com.tma.training.restaurant.util.Index;

import java.util.List;

public class OrderService extends Service<Order> {
    private final List<Order> orderList;
    private final OrderWriter orderWriter;

    public OrderService() {
        super();
        OrderReader orderReader = new OrderReader();
        this.orderWriter = new OrderWriter();
        this.orderList = orderReader.read();
    }

    @Override
    public Order get(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void add(Order itemToAdd) {
        Index index = indexReader.read();
        itemToAdd.setId(index.getOrderIndex() + 1);
        index.setOrderIndex(index.getOrderIndex() + 1);
        orderList.add(itemToAdd);
        orderWriter.write(this.orderList);
        indexWriter.write(index);
    }

    @Override
    public void update(int id, Order itemToUpdate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void show() {
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}
