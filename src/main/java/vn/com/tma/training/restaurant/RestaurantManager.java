package vn.com.tma.training.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.Dish;
import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.ItemType;
import vn.com.tma.training.restaurant.enumtype.MealType;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.exception.InvalidAmountException;
import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.exception.InvalidInputException;
import vn.com.tma.training.restaurant.service.FinishedOrderService;
import vn.com.tma.training.restaurant.service.MenuService;
import vn.com.tma.training.restaurant.service.OngoingOrderService;
import vn.com.tma.training.restaurant.util.Constant;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 * RestaurantManager is the main class of the program.
 * This class will handle user input and process the commands using services.
 * The first static block will initialize every component needed in the program
 */
public class RestaurantManager {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantManager.class);
    private static MenuService menuService = null;
    private static FinishedOrderService finishedOrderService = null;
    private static OngoingOrderService ongoingOrderService = null;
    private static Scanner scanner = null;

    static {
        try {
            if (logger.isDebugEnabled()) {
                logger.info("Start the session at " + new Date());
            }
            menuService = new MenuService();
            finishedOrderService = new FinishedOrderService();
            ongoingOrderService = new OngoingOrderService();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Initialize system failed.");
            if (logger.isDebugEnabled()) {
                logger.error("Error in loading system. Forcing ending session.");
            }
            System.exit(0);
        }
    }

    /**
     * The main function of the program
     *
     * @param args Any argument needed from the console
     */
    public static void main(String[] args) {
        String command;
        do {
            System.out.print(Constant.COMMAND_PREFIX);
            command = scanner.nextLine();
        } while (isHandingCommand(command));
        if (logger.isDebugEnabled()) {
            logger.info("Exiting the session at " + new Date());
        }
        scanner.close();
    }

    /**
     * This function will process the command that was input by the user.
     * After processing, the function will return the next status of the program if it will continue of stop.
     *
     * @param command The command will be processed
     * @return If the program continue or not
     */
    private static boolean isHandingCommand(String command) {
        if (logger.isDebugEnabled()) {
            logger.info("Received command: " + command);
        }
        switch (command) {
            case Constant.HELP:
                showHelp();
                break;
            case Constant.MENU_SHOW:
                showMenu();
                break;
            case Constant.MENU_GET:
                getItemInMenu();
            case Constant.MENU_ADD:
                addItemToMenu();
                break;
            case Constant.MENU_EDIT:
                editItemInMenu();
                break;
            case Constant.MENU_DELETE:
                deleteItemFromMenu();
                break;
            case Constant.ORDER_SHOW_DONE:
                showDoneOrders();
                break;
            case Constant.ORDER_SHOW_ONGOING:
                showCurrentOrders();
                break;
            case Constant.ORDER_EXPORT:
                exportOrder();
                break;
            case Constant.ORDER_NEW:
                addNewOrder();
                break;
            case Constant.ORDER_GET_DONE:
                getDoneOrder();
                break;
            case Constant.ORDER_GET_ONGOING:
                getCurrentOrder();
                break;
            case Constant.ORDER_ADD:
                addItemToOrder();
                break;
            case Constant.ORDER_REMOVE:
                removeItemFromOrder();
                break;
            case Constant.ORDER_CANCEL:
                cancelOrder();
                break;
            case Constant.ORDER_CHECKOUT:
                checkoutOrder();
                break;
            case Constant.RECOVER:
                recover();
                break;
            case Constant.EXIT:
            case Constant.QUIT:
                System.out.println("Exiting...");
                return false;
            default:
                if (logger.isDebugEnabled()) {
                    logger.info("Invalid command. Cancel processing");
                }
                System.out.println("Invalid command. Please try again.\n");
        }
        return true;
    }

    /**
     * Shows help menu for user.
     */
    private static void showHelp() {
        System.out.println(Constant.HELP_MENU);
    }

    /**
     * Shows all menu items for user
     */
    private static void showMenu() {
        menuService.show();
    }

    /**
     * Prints out a specific item in the menu
     */
    private static void getItemInMenu() {
        try {
            System.out.print("Item id: ");
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem item = menuService.get(itemId);
            System.out.println(item);
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Item does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Adds a new item to the menu
     */
    private static void addItemToMenu() {
        try {
            MenuItem itemToAdd = createAMenuItem();
            menuService.add(itemToAdd);
            System.out.println("Add item successfully.\n");
        } catch (InvalidInputException e) {
            System.out.println("Invalid item. Please check your input.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Edits an item in the menu
     */
    private static void editItemInMenu() {
        try {
            System.out.print("Item id: ");
            int editId = Integer.parseInt(scanner.nextLine());
            MenuItem newItemToUpdate = createAMenuItem();
            menuService.update(editId, newItemToUpdate);
            System.out.println("Update item successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Item does not exist.\n");
        } catch (InvalidInputException e) {
            System.out.println("Invalid item. Please check your input.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Deletes an item from the menu
     */
    private static void deleteItemFromMenu() {
        try {
            System.out.print("Item id: ");
            int deleteId = Integer.parseInt(scanner.nextLine());
            menuService.remove(deleteId);
            System.out.println("Remove item successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Shows all finished orders
     */
    private static void showDoneOrders() {
        finishedOrderService.show();
    }

    /**
     * Shows all ongoing orders
     */
    private static void showCurrentOrders() {
        ongoingOrderService.show();
    }

    /**
     * Exports an order and generate the file with unix timestamp name
     */
    private static void exportOrder() {
        try {
            System.out.print("Order id: ");
            int exportId = Integer.parseInt(scanner.nextLine());
            finishedOrderService.export(exportId);
            System.out.println("Export order successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file. Please try again.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Adds a new order to ongoing list
     */
    private static void addNewOrder() {
        try {
            System.out.print("Table number: ");
            int tableNumber = Integer.parseInt(scanner.nextLine());
            Order order = new Order(tableNumber);
            ongoingOrderService.add(order);
            System.out.println("Add new order successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a table id.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Gets a finished order
     */
    private static void getDoneOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order order = finishedOrderService.get(orderId);
            System.out.println(order);
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Gets an ongoing order
     */
    private static void getCurrentOrder() {
        try {
            System.out.print("Order index: ");
            int idx = Integer.parseInt(scanner.nextLine());
            Order order = ongoingOrderService.get(idx);
            System.out.println(order);
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Adds an item to an ongoing order
     */
    private static void addItemToOrder() {
        try {
            System.out.print("Order index: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.print("Item id: ");
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem menuItem = menuService.get(itemId);
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            menuService.reduceStock(itemId, quantity);
            ongoingOrderService.addItemToOrder(orderId, menuItem, quantity);
            System.out.println("Order item successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id and/or quality.\n");
        } catch (InvalidAmountException e) {
            System.out.println("There is not enough stock to order.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Item/Order does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Removes an item from an ongoing order
     */
    private static void removeItemFromOrder() {
        try {
            System.out.print("Order index: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.print("Item id: ");
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem menuItem = menuService.get(itemId);
            ongoingOrderService.removeItemFromOrder(orderId, menuItem);
            System.out.println("Remove item successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id and/or quality.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Item/Order does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Cancels an ongoing order
     */
    private static void cancelOrder() {
        try {
            System.out.print("Order index: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            ongoingOrderService.remove(orderId);
            System.out.println("Cancel new order successfully.\n");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Checks out an ongoing order
     */
    private static void checkoutOrder() {
        try {
            System.out.print("Order index: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order order = ongoingOrderService.get(orderId);
            finishedOrderService.add(order);
            ongoingOrderService.remove(orderId);
            menuService.sync();
            System.out.println("Checkout order successfully.\n");
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Syncs the current lists with the files
     */
    private static void recover() {
        try {
            menuService.sync();
            finishedOrderService.sync();
            System.out.println("Sync file successfully.\n");
        } catch (IOException e) {
            System.out.println("Error in writing to file.\n");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.\n");
        }
    }

    /**
     * Creates a menu item based on the input of user
     *
     * @return The item created, can be a Dish or a Drink
     */
    private static MenuItem createAMenuItem() {
        try {
            System.out.print("Would you like to add/update a dish (0) or a drink (1)? ");
            int option = Integer.parseInt(scanner.nextLine());
            ItemType type = ItemType.getMenuType(option);
            MenuItem item = createAGeneralMenuItem();
            if (type.ordinal() == ItemType.DISH.ordinal()) {
                item.setItemType(ItemType.DISH);
                return createADish(item);
            } else if (type.ordinal() == ItemType.DRINK.ordinal()) {
                item.setItemType(ItemType.DRINK);
                return createADrink(item);
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }
        throw new InvalidEnumValueException(Constant.MENU);
    }

    /**
     * Creates a general MenuItem
     *
     * @return The created MenuItem
     */
    private static MenuItem createAGeneralMenuItem() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Unit price: ");
        int unitPrice = Integer.parseInt(scanner.nextLine());
        System.out.print("Unit type (ex: bowl, can, bottle,...): ");
        String unit = scanner.nextLine();
        return new MenuItem(name, description, unitPrice, unit);
    }

    /**
     * Creates a Dish
     *
     * @param item The current MenuItem
     * @return The created Dish
     */
    private static Dish createADish(MenuItem item) {
        System.out.print("Time of the day (0: Breakfast, 1: Lunch, 2: Dinner): ");
        int timeIn = Integer.parseInt(scanner.nextLine());
        MealType time = MealType.getMealType(timeIn);
        System.out.print("Availability (0: no, != 0: yes): ");
        int in = Integer.parseInt(scanner.nextLine());
        boolean isAvailable = in != 0;
        return new Dish(item, isAvailable, time);
    }

    /**
     * Creates a Drink
     *
     * @param item The current MenuItem
     * @return The created Drink
     */
    private static Drink createADrink(MenuItem item) {
        System.out.print("Drink type (0: Soft drink, 1: Alcohol): ");
        int drinkTypeIn = Integer.parseInt(scanner.nextLine());
        DrinkType drinkType = DrinkType.getDrinkType(drinkTypeIn);
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        return new Drink(item, stock, drinkType);
    }
}
