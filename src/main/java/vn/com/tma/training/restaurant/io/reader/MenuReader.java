package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.enumType.DrinkType;
import vn.com.tma.training.restaurant.enumType.FoodType;
import vn.com.tma.training.restaurant.enumType.MenuType;
import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.menu.Drink;
import vn.com.tma.training.restaurant.menu.Food;
import vn.com.tma.training.restaurant.menu.MenuItem;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuReader implements Reader<List<MenuItem>> {
    @Override
    public List<MenuItem> read() {
        List<MenuItem> menuItemList = new ArrayList<>();
        File jsonInputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/menu.json");
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonArray items = reader.readArray();
            reader.close();
            for (int i = 0; i < items.size(); i++) {
                JsonObject item = items.getJsonObject(i);
                int id = item.getInt("id");
                String name = item.getString("name");
                String description = item.getString("description");
                int unitPrice = item.getInt("unitPrice");
                String unitType = item.getString("unitType");
                int menuType = item.getInt("menuType");
                if (menuType == MenuType.FOOD.ordinal()) {
                    boolean isAvailable = item.getInt("isAvailable") != 0;
                    FoodType foodType = FoodType.getFoodType(item.getInt("customType"));
                    menuItemList.add(new Food(id, name, description, unitPrice, unitType, MenuType.FOOD, isAvailable, foodType));
                } else if (menuType == MenuType.DRINK.ordinal()) {
                    int stock = item.getInt("stock");
                    DrinkType drinkType = DrinkType.getDrinkType(item.getInt("customType"));
                    menuItemList.add(new Drink(id, name, description, unitPrice, unitType, MenuType.DRINK, stock, drinkType));
                }
            }
        } catch (FileNotFoundException | InvalidEnumValueException e) {
            e.printStackTrace();
        }
        return menuItemList;
    }
}
