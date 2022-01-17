package vn.com.tma.training.restaurant.util;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class Constant {
    // Enum name
    public static String FOOD = "Food";
    public static String BREAKFAST = "Breakfast";
    public static String LUNCH = "Lunch";
    public static String DINNER = "Dinner";
    public static String DRINK = "Drink";
    public static String SOFT_DRINK = "Soft drink";
    public static String ALCOHOL = "Alcohol";

    // Field name
    public static String ID = "id";
    public static String TIME = "time";
    public static String ITEMS = "items";
    public static String NAME = "name";
    public static String UNIT_PRICE = "unitPrice";
    public static String UNIT_TYPE = "unitType";
    public static String QUANTITY = "quantity";
    public static String PRICE = "price";
    public static String DESCRIPTION = "description";
    public static String MENU_TYPE = "menuType";
    public static String IS_AVAILABLE = "isAvailable";
    public static String CUSTOM_TYPE = "customType";
    public static String STOCK = "stock";
    public static String MENU_INDEX = "menuIndex";
    public static String ORDER_INDEX = "orderIndex";


    // File
    public static File ORDER_FILE = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/restaurant/data/order.json");
    public static File MENU_FILE = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/restaurant/data/menu.json");
    public static File INDEX_FILE = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/restaurant/data/index.json");

    // Formatter
    public static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
