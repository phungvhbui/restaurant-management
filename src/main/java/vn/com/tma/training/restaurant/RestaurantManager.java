package vn.com.tma.training.restaurant;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.entity.order.Order;
import vn.com.tma.training.restaurant.enumtype.DrinkType;
import vn.com.tma.training.restaurant.enumtype.FoodType;
import vn.com.tma.training.restaurant.enumtype.MenuType;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.exception.InvalidInputException;
import vn.com.tma.training.restaurant.service.MenuService;
import vn.com.tma.training.restaurant.service.OrderService;
import vn.com.tma.training.restaurant.util.Constant;

import java.util.Scanner;

public class RestaurantManager {
    private static final MenuService menuService = new MenuService();
    private static final OrderService orderService = new OrderService();
    private static final Scanner scanner = new Scanner(System.in);
    private static Order newOrder = new Order();

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
            case Constant.ORDER_SHOW:
                showOrders();
                break;
            case Constant.ORDER_EXPORT:
                exportOrder();
                break;
            case Constant.ORDER_CLEAR:
                newOrder = new Order();
                System.out.println("Start a new order successfully.");
                break;
            case Constant.ORDER_GET:
                System.out.println(newOrder);
                break;
            case Constant.ORDER_ADD:
                addItemToOrder();
                break;
            case Constant.ORDER_SAVE:
                saveOrder();
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
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    public static void showOrders() {
        orderService.show();
    }

    public static void exportOrder() {
        int exportId;
        try {
            System.out.print("Order id: ");
            exportId = Integer.parseInt(scanner.nextLine());
            orderService.export(exportId);
            System.out.println("Export order successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    public static void addItemToOrder() {
        try {
            System.out.print("Item id: ");
            int orderItem = Integer.parseInt(scanner.nextLine());
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            MenuItem itemToOrder = menuService.get(orderItem);
            newOrder.orderItem(itemToOrder, quantity);
            System.out.println("Order item successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid id and/or quality.");
        } catch (EntityNotFoundException e) {
            System.out.println("Item does not exist.");
        } catch (Exception e) {
            System.out.println("Something is wrong. Please try again.");
        }
    }

    public static void saveOrder() {
        try {
            orderService.add(newOrder);
            System.out.println("Save order successfully.");
            newOrder = new Order();
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
