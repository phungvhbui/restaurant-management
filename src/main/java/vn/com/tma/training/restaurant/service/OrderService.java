package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.io.reader.OrderReader;
import vn.com.tma.training.restaurant.io.writer.OrderWriter;
import vn.com.tma.training.restaurant.util.Index;

import java.io.IOException;
import java.util.List;

public class OrderService extends Service<Order> {
    private final List<Order> orderList;
    private final OrderWriter orderWriter;

    public OrderService() throws IOException {
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
        throw new EntityNotFoundException();
    }

    public void sync() throws IOException {
        orderWriter.write(this.orderList);
    }
}
