package vn.com.tma.training.restaurant.util;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class Constant {
    // Enum name
    public static final String MENU = "Menu";
    public static final String FOOD = "Food";
    public static final String BREAKFAST = "Breakfast";
    public static final String LUNCH = "Lunch";
    public static final String DINNER = "Dinner";
    public static final String DRINK = "Drink";
    public static final String SOFT_DRINK = "Soft drink";
    public static final String ALCOHOL = "Alcohol";
    // Field name
    public static final String ID = "id";
    public static final String TABLE_NUMBER = "tableNumber";
    public static final String TIME = "time";
    public static final String ITEMS = "items";
    public static final String NAME = "name";
    public static final String UNIT_PRICE = "unitPrice";
    public static final String UNIT_TYPE = "unitType";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String MENU_TYPE = "menuType";
    public static final String IS_AVAILABLE = "isAvailable";
    public static final String CUSTOM_TYPE = "customType";
    public static final String STOCK = "stock";
    public static final String MENU_INDEX = "menuIndex";
    public static final String ORDER_INDEX = "orderIndex";
    // File
    public static final File ORDER_FILE = new File(System.getenv("ORDER_FILE"));
    public static final File MENU_FILE = new File(System.getenv("MENU_FILE"));
    public static final File INDEX_FILE = new File(System.getenv("INDEX_FILE"));
    // Formatter
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // Menu
    public static final String HELP_MENU = "Available commands:\n" +
            "    help               : Show help\n" +
            "    menu show          : Show menu\n" +
            "    menu add           : Add an item to menu\n" +
            "    menu edit          : Edit an item in menu\n" +
            "    menu delete        : Delete an item in menu\n" +
            "    order show done    : Show all finished orders\n" +
            "    order show ongoing : Show all ongoing orders\n" +
            "    order export       : Export a finished order to file\n" +
            "    order new          : Start a new order\n" +
            "    order get done     : Show a finished order\n" +
            "    order get ongoing  : Show an ongoing order\n" +
            "    order add          : Add an item to an ongoing order\n" +
            "    order remove       : Remove an item from an ongoing order\n" +
            "    order cancel       : Cancel an ongoing order\n" +
            "    order checkout     : Checkout an ongoing order\n" +
            "    recover            : Sync the program and database (file)\n" +
            "    quit/exit          : End the session";
    public static final String COMMAND_PREFIX = "> ";

    public static final String HELP = "help";
    public static final String MENU_SHOW = "menu show";
    public static final String MENU_ADD = "menu add";
    public static final String MENU_EDIT = "menu edit";
    public static final String MENU_DELETE = "menu delete";
    public static final String ORDER_SHOW_DONE = "order show done";
    public static final String ORDER_SHOW_ONGOING = "order show ongoing";
    public static final String ORDER_EXPORT = "order export";
    public static final String ORDER_NEW = "order new";
    public static final String ORDER_GET_DONE = "order get done";
    public static final String ORDER_GET_ONGOING = "order get ongoing";
    public static final String ORDER_ADD = "order add";
    public static final String ORDER_REMOVE = "order remove";
    public static final String ORDER_CANCEL = "order cancel";
    public static final String ORDER_CHECKOUT = "order checkout";
    public static final String RECOVER = "recover";
    public static final String EXIT = "exit";
    public static final String QUIT = "quit";
}
