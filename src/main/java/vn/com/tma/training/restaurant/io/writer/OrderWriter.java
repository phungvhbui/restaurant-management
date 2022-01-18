package vn.com.tma.training.restaurant.io.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderWriter implements Writer<List<Order>> {
    private static final Logger logger = LoggerFactory.getLogger(OrderWriter.class);

    @Override
    public void write(List<Order> data) throws IOException {
        logger.info("Writing orders to file " + Constant.ORDER_FILE.getAbsolutePath());

        try {
            OutputStream os;
            os = new FileOutputStream(Constant.ORDER_FILE);
            JsonWriter writer = Json.createWriter(os);
            JsonArrayBuilder orderArrayBuilder = Json.createArrayBuilder();
            for (Order order : data) {
                JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
                orderBuilder.add(Constant.ID, order.getId());
                orderBuilder.add(Constant.TABLE_NUMBER, order.getTableNumber());
                orderBuilder.add(Constant.TIME, order.getOrderedTime().format(Constant.DATETIME_FORMATTER));
                HashMap<MenuItem, Integer> map = order.getOrderedItems();
                JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
                for (Map.Entry<MenuItem, Integer> set : map.entrySet()) {
                    MenuItem item = set.getKey();
                    JsonObjectBuilder itemBuilder = Json.createObjectBuilder();
                    itemBuilder.add(Constant.ID, item.getId());
                    itemBuilder.add(Constant.NAME, item.getName());
                    itemBuilder.add(Constant.UNIT_PRICE, item.getUnitPrice());
                    itemBuilder.add(Constant.UNIT_TYPE, item.getUnitType());
                    itemBuilder.add(Constant.QUANTITY, set.getValue());
                    itemsBuilder.add(itemBuilder);
                }
                orderBuilder.add(Constant.ITEMS, itemsBuilder);
                orderBuilder.add(Constant.PRICE, order.getTotalPrice());
                orderArrayBuilder.add(orderBuilder);
            }
            JsonArray orders = orderArrayBuilder.build();
            writer.writeArray(orders);
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }
    }

    public void export(Order order) throws IOException {
        try {
            String fileName = System.currentTimeMillis() / 1000L + ".txt";
            logger.info("Exporting order to file " + fileName);
            FileWriter fileWriter = new FileWriter(fileName + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("'RESTAURANT' RESTAURANT");
            printWriter.println();
            printWriter.println("ORDER ID: " + order.getId());
            printWriter.println("TABLE: " + order.getTableNumber());
            printWriter.println("TIME: " + order.getOrderedTime().format(Constant.DATETIME_FORMATTER));
            printWriter.println();
            printWriter.println("----------------------------------------------------------------------------------");
            printWriter.printf("%-15s%-15s%-15s%n", "NAME", "UNIT PRICE", "QUANTITY");
            HashMap<MenuItem, Integer> map = order.getOrderedItems();
            for (Map.Entry<MenuItem, Integer> set : map.entrySet()) {
                MenuItem item = set.getKey();
                printWriter.printf("%-15s%-15s%-15s%n", item.getName(), item.getUnitPrice() + "/" + item.getUnitType(), set.getValue());
            }
            printWriter.println("----------------------------------------------------------------------------------");
            printWriter.println("TOTAL PRICE: " + order.getTotalPrice());
            printWriter.println();
            printWriter.println("THANK YOU FOR USING OUR SERVICE!");
            printWriter.close();
            System.out.println("Exported to file " + fileName);
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }
    }
}
