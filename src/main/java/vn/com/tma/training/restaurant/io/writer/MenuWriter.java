package vn.com.tma.training.restaurant.io.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.Dish;
import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.enumtype.ItemType;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * MenuWriter writes the list of MenuItem
 */
public class MenuWriter implements Writer<List<MenuItem>> {
    private static final Logger logger = LoggerFactory.getLogger(MenuWriter.class);

    /**
     * Writes the list of MenuItem that passed in to file
     *
     * @param data The list of MenuItem that needs to be saved
     * @throws IOException If there is something wrong when writing file
     */
    @Override
    public void write(List<MenuItem> data) throws IOException {
        logger.info("Writing menu to file " + Constant.MENU_FILE.getAbsolutePath());

        try {
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
                if (item instanceof Dish) {
                    objectBuilder.add(Constant.ITEM_TYPE, ItemType.DISH.ordinal());
                    objectBuilder.add(Constant.CUSTOM_TYPE, ((Dish) item).getMealType().ordinal());
                    objectBuilder.add(Constant.IS_AVAILABLE, ((Dish) item).isAvailable() ? 1 : 0);
                } else if (item instanceof Drink) {
                    objectBuilder.add(Constant.ITEM_TYPE, ItemType.DRINK.ordinal());
                    objectBuilder.add(Constant.STOCK, ((Drink) item).getStock());
                    objectBuilder.add(Constant.CUSTOM_TYPE, ((Drink) item).getDrinkType().ordinal());
                }
                itemsBuilder.add(objectBuilder);
            }
            JsonArray items = itemsBuilder.build();
            writer.writeArray(items);
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }
    }
}
