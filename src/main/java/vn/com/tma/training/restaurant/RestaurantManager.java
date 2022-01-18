package vn.com.tma.training.restaurant;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.FoodType;
import vn.com.tma.training.restaurant.enumtype.MenuType;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.exception.InvalidEnumValueException;
import vn.com.tma.training.restaurant.exception.InvalidInputException;
import vn.com.tma.training.restaurant.service.CurrentOrderService;
import vn.com.tma.training.restaurant.service.MenuService;
import vn.com.tma.training.restaurant.service.OrderService;
import vn.com.tma.training.restaurant.util.Constant;

import java.io.IOException;
import java.util.Scanner;

public class RestaurantManager {
    private static MenuService menuService = null;
    private static OrderService finishedOrderService = null;
    private static CurrentOrderService currentOrderService = null;
    private static Scanner scanner = null;

    static {
        try {
            menuService = new MenuService();
            finishedOrderService = new OrderService();
            currentOrderService = new CurrentOrderService();
            scanner = new Scanner(System.in);
        } catch (IOException | InvalidEnumValueException e) {
            System.out.println("Initialize system failed.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Menu
        String command;
        do {
            System.out.print(Constant.COMMAND_PREFIX);
            command = scanner.nextLine();
        } while (isHandingCommand(command));

        scanner.close();
    }

    private static boolean isHandingCommand(String command) {
        switch (command) {
            case Constant.HELP:
                showHelp();
                break;
            case Constant.MENU_SHOW:
                showMenu();
                break;
            case Constant.MENU_EXPORT:
                exportMenu();
                break;
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
            case Constant.ORDER_SHOW_CURRENT:
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
            case Constant.ORDER_GET_CURRENT:
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
            case Constant.EXIT:
            case Constant.QUIT:
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("Invalid command. Please try again.");
        }
        return true;
    }

    private static void showHelp() {
        System.out.println(Constant.HELP_MENU);
    }

    private static void showMenu() {
        menuService.show();
    }

    private static void exportMenu() {
        menuService.export();
    }

    private static void addItemToMenu() {
        try {
            MenuItem itemToAdd = inputItem();
            menuService.add(itemToAdd);
            System.out.println("Add item successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid item. Please check your input.");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void editItemInMenu() {
        try {
            System.out.print("Item id: ");
            int editId = Integer.parseInt(scanner.nextLine());
            menuService.get(editId);
            MenuItem newItemToUpdate = inputItem();
            menuService.update(editId, newItemToUpdate);
            System.out.println("Update item successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.");
        } catch (EntityNotFoundException e) {
            System.out.println("Item does not exist.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid item. Please check your input.");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void deleteItemFromMenu() {
        try {
            System.out.print("Item id: ");
            int deleteId = Integer.parseInt(scanner.nextLine());
            menuService.remove(deleteId);
            System.out.println("Remove item successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void showDoneOrders() {
        finishedOrderService.show();
    }

    private static void showCurrentOrders() {
        currentOrderService.show();
    }

    private static void exportOrder() {
        try {
            System.out.print("Order id: ");
            int exportId = Integer.parseInt(scanner.nextLine());
            finishedOrderService.export(exportId);
            System.out.println("Export order successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.");
        } catch (IOException e) {
            System.out.println("Error in writing to file. Please try again.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void addNewOrder() {
        try {
            System.out.print("Table number: ");
            int tableNumber = Integer.parseInt(scanner.nextLine());
            Order order = new Order(tableNumber);
            currentOrderService.add(order);
            System.out.println("Add new order successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a table id.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void getDoneOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order order = finishedOrderService.get(orderId);
            System.out.println(order);
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void getCurrentOrder() {
        try {
            System.out.print("Index: ");
            int idx = Integer.parseInt(scanner.nextLine());
            Order order = currentOrderService.get(idx);
            System.out.println(order);
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void addItemToOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.print("Item id: ");
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem menuItem = menuService.get(itemId);
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            currentOrderService.addItemToOrder(orderId, menuItem, quantity);
            System.out.println("Order item successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id and/or quality.");
        } catch (EntityNotFoundException e) {
            System.out.println("Item/Order does not exist.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void removeItemFromOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.print("Item id: ");
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem menuItem = menuService.get(itemId);
            currentOrderService.removeItemFromOrder(orderId, menuItem);
            System.out.println("Remove item successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id and/or quality.");
        } catch (EntityNotFoundException e) {
            System.out.println("Item/Order does not exist.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void cancelOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            currentOrderService.remove(orderId);
            System.out.println("Cancel new order successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.");
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static void checkoutOrder() {
        try {
            System.out.print("Order id: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order order = currentOrderService.get(orderId);
            finishedOrderService.add(order);
            System.out.println("Checkout order successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println("Order does not exist.");
        } catch (IOException e) {
            System.out.println("Error in writing to file. You can run `recover` to sync the program and the file.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    private static MenuItem inputItem() {
        MenuItem item = null;
        try {
            System.out.print("Would you like to add/update a dish (0) or a drink (1)? ");
            int option = Integer.parseInt(scanner.nextLine());
            MenuType type = MenuType.getMenuType(option);
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Unit price: ");
            int unitPrice = Integer.parseInt(scanner.nextLine());
            System.out.print("Unit type (ex: bowl, can, bottle,...): ");
            String unit = scanner.nextLine();
            if (type.ordinal() == MenuType.FOOD.ordinal()) {
                System.out.print("Time of the day (0: Breakfast, 1: Lunch, 2: Dinner): ");
                int timeIn = Integer.parseInt(scanner.nextLine());
                FoodType time = FoodType.getFoodType(timeIn);
                System.out.print("Availability (0: no, != 0: yes): ");
                int in = Integer.parseInt(scanner.nextLine());
                boolean isAvailable = in != 0;
                item = new Food(name, description, unitPrice, unit, MenuType.FOOD, isAvailable, time);
            } else if (type.ordinal() == MenuType.DRINK.ordinal()) {
                System.out.print("Drink type (0: Soft drink, 1: Alcohol): ");
                int drinkTypeIn = Integer.parseInt(scanner.nextLine());
                DrinkType drinkType = DrinkType.getDrinkType(drinkTypeIn);
                System.out.print("Stock: ");
                int stock = Integer.parseInt(scanner.nextLine());
                item = new Drink(name, description, unitPrice, unit, MenuType.DRINK, stock, drinkType);
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }
        if (item == null) {
            throw new InvalidInputException();
        }
        return item;
    }
}
