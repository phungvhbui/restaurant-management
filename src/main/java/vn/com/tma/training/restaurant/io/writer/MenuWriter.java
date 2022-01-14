package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.enumtype.MenuType;
import vn.com.tma.training.restaurant.menu.Drink;
import vn.com.tma.training.restaurant.menu.Food;
import vn.com.tma.training.restaurant.menu.MenuItem;

import javax.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class MenuWriter implements Writer<List<MenuItem>> {
    @Override
    public void write(List<MenuItem> data) {
        File jsonOutputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/menu.json");
        OutputStream os;
        try {
            os = new FileOutputStream(jsonOutputFile);
            JsonWriter writer = Json.createWriter(os);
            JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
            for (MenuItem item : data) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", item.getId());
                objectBuilder.add("name", item.getName());
                objectBuilder.add("description", item.getDescription());
                objectBuilder.add("unitPrice", item.getUnitPrice());
                objectBuilder.add("unitType", item.getUnit());
                if (item instanceof Food) {
                    objectBuilder.add("menuType", MenuType.FOOD.ordinal());
                    objectBuilder.add("customType", ((Food) item).getMealType().ordinal());
                    objectBuilder.add("isAvailable", ((Food) item).isAvailable() ? 1 : 0);
                } else if (item instanceof Drink) {
                    objectBuilder.add("menuType", MenuType.DRINK.ordinal());
                    objectBuilder.add("stock", ((Drink) item).getStock());
                    objectBuilder.add("customType", ((Drink) item).getDrinkType().ordinal());
                }
                itemsBuilder.add(objectBuilder);
            }
            JsonArray items = itemsBuilder.build();
            writer.writeArray(items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
