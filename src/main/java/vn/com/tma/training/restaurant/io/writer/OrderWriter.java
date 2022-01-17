package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderWriter implements Writer<List<Order>> {
    @Override
    public void write(List<Order> data) {
        OutputStream os;
        try {
            os = new FileOutputStream(Constant.ORDER_FILE);
            JsonWriter writer = Json.createWriter(os);
            JsonArrayBuilder ordersBuilder = Json.createArrayBuilder();
            for (Order order : data) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add(Constant.ID, order.getId());
                objectBuilder.add(Constant.TIME, order.getOrderedTime().format(Constant.DATETIME_FORMATTER));
                HashMap<MenuItem, Integer> map = order.getOrderedItems();
                JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
                for (Map.Entry<MenuItem, Integer> set : map.entrySet()) {
                    MenuItem is = set.getKey();
                    JsonObjectBuilder itemBuilder = Json.createObjectBuilder();
                    itemBuilder.add(Constant.ID, is.getId());
                    itemBuilder.add(Constant.NAME, is.getName());
                    itemBuilder.add(Constant.UNIT_PRICE, is.getUnitPrice());
                    itemBuilder.add(Constant.UNIT_TYPE, is.getUnitType());
                    itemBuilder.add(Constant.QUANTITY, set.getValue());
                    itemsBuilder.add(itemBuilder);
                }
                objectBuilder.add(Constant.ITEMS, itemsBuilder);
                objectBuilder.add(Constant.PRICE, order.getTotalPrice());
                ordersBuilder.add(objectBuilder);
            }
            JsonArray orders = ordersBuilder.build();
            writer.writeArray(orders);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
