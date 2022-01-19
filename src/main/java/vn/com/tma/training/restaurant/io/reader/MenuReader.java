package vn.com.tma.training.restaurant.io.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.Dish;
import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.ItemType;
import vn.com.tma.training.restaurant.enumtype.MealType;
import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.util.Constant;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuReader reads the list of MenuItem
 */
public class MenuReader implements Reader<List<MenuItem>> {
    private static final Logger logger = LoggerFactory.getLogger(MenuReader.class);

    /**
     * Reads the menu file and returns the list of MenuItem
     *
     * @return The list of MenuItem
     * @throws IOException               If there is something wrong when reading file
     * @throws InvalidEnumValueException If there is no enum match with the type in file
     */
    @Override
    public List<MenuItem> read() throws IOException, InvalidEnumValueException {
        logger.info("Reading menu from file " + Constant.MENU_FILE.getAbsolutePath());

        List<MenuItem> menuItemList = new ArrayList<>();
        try {
            InputStream is;
            is = new FileInputStream(Constant.MENU_FILE);
            JsonReader reader = Json.createReader(is);
            JsonArray items = reader.readArray();
            reader.close();
            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.getJsonObject(i);
                int id = item.getInt(Constant.ID);
                String name = item.getString(Constant.NAME);
                String description = item.getString(Constant.DESCRIPTION);
                int unitPrice = item.getInt(Constant.UNIT_PRICE);
                String unitType = item.getString(Constant.UNIT_TYPE);
                int itemType = item.getInt(Constant.ITEM_TYPE);
                if (itemType == ItemType.DISH.ordinal()) {
                    boolean isAvailable = item.getInt(Constant.IS_AVAILABLE) != 0;
                    MealType mealType = MealType.getMealType(item.getInt(Constant.CUSTOM_TYPE));
                    menuItemList.add(new Dish(id, name, description, unitPrice, unitType, ItemType.DISH, isAvailable, mealType));
                } else if (itemType == ItemType.DRINK.ordinal()) {
                    int stock = item.getInt(Constant.STOCK);
                    DrinkType drinkType = DrinkType.getDrinkType(item.getInt(Constant.CUSTOM_TYPE));
                    menuItemList.add(new Drink(id, name, description, unitPrice, unitType, ItemType.DRINK, stock, drinkType));
                }
            }
        } catch (IOException | InvalidEnumValueException e) {
            logger.error(e.toString());
            throw e;
        }

        return menuItemList;
    }
}
