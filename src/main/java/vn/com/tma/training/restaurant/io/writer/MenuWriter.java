package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.enumtype.MenuType;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MenuWriter implements Writer<List<MenuItem>> {
    @Override
    public void write(List<MenuItem> data) throws IOException {
        OutputStream os;
        os = new FileOutputStream(Constant.MENU_FILE);
        JsonWriter writer = Json.createWriter(os);
        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        for (MenuItem item : data) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add(Constant.ID, item.getId());
            objectBuilder.add(Constant.NAME, item.getName());
            objectBuilder.add(Constant.DESCRIPTION, item.getDescription());
            objectBuilder.add(Constant.UNIT_PRICE, item.getUnitPrice());
            objectBuilder.add(Constant.UNIT_TYPE, item.getUnitType());
            if (item instanceof Food) {
                objectBuilder.add(Constant.MENU_TYPE, MenuType.FOOD.ordinal());
                objectBuilder.add(Constant.CUSTOM_TYPE, ((Food) item).getMealType().ordinal());
                objectBuilder.add(Constant.IS_AVAILABLE, ((Food) item).isAvailable() ? 1 : 0);
            } else if (item instanceof Drink) {
                objectBuilder.add(Constant.MENU_TYPE, MenuType.DRINK.ordinal());
                objectBuilder.add(Constant.STOCK, ((Drink) item).getStock());
                objectBuilder.add(Constant.CUSTOM_TYPE, ((Drink) item).getDrinkType().ordinal());
            }
            itemsBuilder.add(objectBuilder);
        }
        JsonArray items = itemsBuilder.build();
        writer.writeArray(items);
    }
}
