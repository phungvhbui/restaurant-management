package vn.com.tma.training.restaurant.order;

import vn.com.tma.training.restaurant.io.reader.IndexReader;
import vn.com.tma.training.restaurant.io.reader.OrderReader;
import vn.com.tma.training.restaurant.io.writer.IndexWriter;
import vn.com.tma.training.restaurant.io.writer.OrderWriter;
import vn.com.tma.training.restaurant.util.Index;

import java.util.List;

public class OrderList {
    private final List<Order> orderList;
    private final OrderWriter orderWriter;
    private final IndexReader indexReader;
    private final IndexWriter indexWriter;

    public OrderList() {
        OrderReader orderReader = new OrderReader();
        this.orderWriter = new OrderWriter();
        this.orderList = orderReader.read();
        this.indexReader = new IndexReader();
        this.indexWriter = new IndexWriter();
    }

    public Order getOrder(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        Index index = indexReader.read();
        order.setId(index.getOrderIndex() + 1);
        index.setOrderIndex(index.getOrderIndex() + 1);
        orderList.add(order);
        orderWriter.write(this.orderList);
        indexWriter.write(index);
    }

    public void showOrders() {
        for (Order order : orderList) {
            System.out.println(order);
        }
    }

}
