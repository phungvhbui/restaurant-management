package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.FoodType;
import vn.com.tma.training.restaurant.enumtype.MenuType;
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

public class MenuReader implements Reader<List<MenuItem>> {
    @Override
    public List<MenuItem> read() throws IOException, InvalidEnumValueException {
        List<MenuItem> menuItemList = new ArrayList<>();
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
            int menuType = item.getInt(Constant.MENU_TYPE);
            if (menuType == MenuType.FOOD.ordinal()) {
                boolean isAvailable = item.getInt(Constant.IS_AVAILABLE) != 0;
                FoodType foodType = FoodType.getFoodType(item.getInt(Constant.CUSTOM_TYPE));
                menuItemList.add(new Food(id, name, description, unitPrice, unitType, MenuType.FOOD, isAvailable, foodType));
            } else if (menuType == MenuType.DRINK.ordinal()) {
                int stock = item.getInt(Constant.STOCK);
                DrinkType drinkType = DrinkType.getDrinkType(item.getInt(Constant.CUSTOM_TYPE));
                menuItemList.add(new Drink(id, name, description, unitPrice, unitType, MenuType.DRINK, stock, drinkType));
            }
        }
        return menuItemList;
    }
}
