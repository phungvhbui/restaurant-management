package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrentOrderService extends Service<Order> {
    private final List<Order> orderList;

    public CurrentOrderService() {
        this.orderList = new ArrayList<>();
    }

    @Override
    public Order get(int id) {
        try {
            return orderList.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void add(Order itemToAdd) throws IOException {
        orderList.add(itemToAdd);
    }

    @Override
    public void update(int id, Order itemToUpdate) throws IOException {

    }

    @Override
    public void remove(int id) throws IOException {

    }

    @Override
    public void show() {
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println(i + "\n  ");
            System.out.println(orderList.get(i));
        }
    }
}
