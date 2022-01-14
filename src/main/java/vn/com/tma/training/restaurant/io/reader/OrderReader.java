package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.menu.MenuItem;
import vn.com.tma.training.restaurant.order.Order;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderReader implements Reader<List<Order>> {
    @Override
    public List<Order> read() {
        List<Order> orderList = new ArrayList<>();
        File jsonInputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/order.json");
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonArray orders = reader.readArray();
            reader.close();
            for (int i = 0; i < orders.size(); i++) {
                JsonObject order = orders.getJsonObject(i);
                int id = order.getInt("id");
                String timeString = order.getString("time");
                LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                JsonArray items = order.getJsonArray("items");
                HashMap<MenuItem, Integer> map = new HashMap<>();
                for (int j = 0; j < items.size(); j++) {
                    JsonObject item = items.getJsonObject(j);
                    int itemId = item.getInt("id");
                    String name = item.getString("name");
                    int unitPrice = item.getInt("unitPrice");
                    String unitType = item.getString("unitType");
                    MenuItem menuItem = new MenuItem(itemId, name, unitPrice, unitType);
                    int quantity = item.getInt("quantity");
                    map.put(menuItem, quantity);
                }
                int price = order.getInt("price");
                orderList.add(new Order(id, map, time, price));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
