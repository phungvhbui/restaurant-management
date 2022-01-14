package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.menu.MenuItem;
import vn.com.tma.training.restaurant.order.Order;

import javax.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderWriter implements Writer<List<Order>> {
    @Override
    public void write(List<Order> data) {
        File jsonOutputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/order.json");
        OutputStream os;
        try {
            os = new FileOutputStream(jsonOutputFile);
            JsonWriter writer = Json.createWriter(os);
            JsonArrayBuilder ordersBuilder = Json.createArrayBuilder();
            for (Order order : data) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", order.getId());
                objectBuilder.add("time", order.getOrderedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                HashMap<MenuItem, Integer> map = order.getOrderedItems();
                JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
                for (Map.Entry<MenuItem, Integer> set : map.entrySet()) {
                    MenuItem is = set.getKey();
                    JsonObjectBuilder itemBuilder = Json.createObjectBuilder();
                    itemBuilder.add("id", is.getId());
                    itemBuilder.add("name", is.getName());
                    itemBuilder.add("unitPrice", is.getUnitPrice());
                    itemBuilder.add("unitType", is.getUnit());
                    itemBuilder.add("quantity", set.getValue());
                    itemsBuilder.add(itemBuilder);
                }
                objectBuilder.add("items", itemsBuilder);
                objectBuilder.add("price", order.getTotalPrice());
                ordersBuilder.add(objectBuilder);
            }
            JsonArray orders = ordersBuilder.build();
            writer.writeArray(orders);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
