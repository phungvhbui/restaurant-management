package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderReader implements Reader<List<Order>> {
    @Override
    public List<Order> read() throws IOException, InvalidEnumValueException {
        List<Order> orderList = new ArrayList<>();
        InputStream is;
        is = new FileInputStream(Constant.ORDER_FILE);
        JsonReader reader = Json.createReader(is);
        JsonArray orders = reader.readArray();
        reader.close();
        for (int i = 0; i < orders.size(); i++) {
            JsonObject order = orders.getJsonObject(i);
            int id = order.getInt(Constant.ID);
            int tableNumber = order.getInt(Constant.TABLE_NUMBER);
            String timeString = order.getString(Constant.TIME);
            LocalDateTime time = LocalDateTime.parse(timeString, Constant.DATETIME_FORMATTER);
            JsonArray items = order.getJsonArray(Constant.ITEMS);
            HashMap<MenuItem, Integer> map = new HashMap<>();
            for (int j = 0; j < items.size(); j++) {
                JsonObject item = items.getJsonObject(j);
                int itemId = item.getInt(Constant.ID);
                String name = item.getString(Constant.NAME);
                int unitPrice = item.getInt(Constant.UNIT_PRICE);
                String unitType = item.getString(Constant.UNIT_TYPE);
                MenuItem menuItem = new MenuItem(itemId, name, unitPrice, unitType);
                int quantity = item.getInt(Constant.QUANTITY);
                map.put(menuItem, quantity);
            }
            int price = order.getInt(Constant.PRICE);
            orderList.add(new Order(id, map, time, tableNumber, price));
        }
        return orderList;
    }
}
