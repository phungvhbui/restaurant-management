package vn.com.tma.training.restaurant;

import vn.com.tma.training.restaurant.enumType.DrinkType;
import vn.com.tma.training.restaurant.enumType.FoodType;
import vn.com.tma.training.restaurant.enumType.MenuType;
import vn.com.tma.training.restaurant.menu.Drink;
import vn.com.tma.training.restaurant.menu.Food;
import vn.com.tma.training.restaurant.menu.Menu;
import vn.com.tma.training.restaurant.menu.MenuItem;
import vn.com.tma.training.restaurant.order.Order;
import vn.com.tma.training.restaurant.order.OrderList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Init system
        Menu menu = new Menu();
        OrderList orderList = new OrderList();
        Order newOrder = new Order();
        Scanner scanner = new Scanner(System.in);

        // Menu
        boolean isDone = false;
        String command;

        do {
            System.out.print("> ");
            command = scanner.nextLine();
            switch (command) {
                case "help":
                    showHelp();
                    break;
                case "menu show":
                    menu.show();
                    break;
                case "menu add":
                    MenuItem itemToAdd = inputItem(scanner);
                    if (itemToAdd != null) {
                        menu.addItem(itemToAdd);
                        System.out.println("Add item successfully.");
                    } else {
                        System.out.println("Add item failed. Please try again.");
                    }
                    break;
                case "menu edit":
                    int updateId;
                    try {
                        System.out.print("Item id: ");
                        updateId = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    MenuItem itemToUpdate = menu.getItem(updateId);
                    if (itemToUpdate == null) {
                        System.out.println("Item does not exist.");
                        break;
                    }
                    MenuItem newItemToUpdate = inputItem(scanner);
                    if (newItemToUpdate != null) {
                        menu.updateItem(updateId, newItemToUpdate);
                        System.out.println("Update item successfully.");
                    } else {
                        System.out.println("Update item failed. Please try again.");
                    }
                    break;
                case "menu delete":
                    int deleteId;
                    try {
                        System.out.print("Item id: ");
                        deleteId = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    menu.removeItem(deleteId);
                    System.out.println("Remove item successfully.");
                    break;
                case "order show":
                    orderList.showOrders();
                    break;
//                case "order export":
//                    int exportId;
//                    try {
//                        System.out.print("Order id: ");
//                        exportId = Integer.parseInt(scanner.nextLine());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                    Order orderToExport = orderList.getOrder(exportId);
//                    if (orderToExport != null) {
//                        System.out.print("Filename: ");
//                        String fileName = scanner.nextLine();
//                        Printer.exportOrder(orderToExport, fileName);
//                        System.out.println("Export order successfully.");
//                    } else {
//                        System.out.println("Export order failed. Please try again.");
//                    }
//                    break;
                case "order clear":
                    newOrder = new Order();
                    System.out.println("Start a new order successfully.");
                    break;
                case "order get":
                    System.out.println(newOrder);
                    break;
                case "order add":
                    int orderItem;
                    int quantity;
                    try {
                        System.out.print("Item id: ");
                        orderItem = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantity: ");
                        quantity = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    MenuItem itemToOrder = menu.getItem(orderItem);
                    if (itemToOrder != null) {
                        newOrder.orderItem(itemToOrder, quantity);
                        System.out.println("Order item successfully.");
                    } else {
                        System.out.println("Order item failed. Please try again.");
                    }
                    break;
                case "order save":
                    orderList.addOrder(newOrder);
                    System.out.println("Save order successfully.");
                    newOrder = new Order();
                    break;
                case "exit":
                case "quit":
                    System.out.println("Exiting...");
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (!isDone);
    }

    public static void showHelp() {
        System.out.println("Available commands:");
        System.out.println("    help            : Show help");
        System.out.println("    menu show       : Show menu");
        System.out.println("    menu add        : Add an item to menu");
        System.out.println("    menu edit       : Edit an item in menu");
        System.out.println("    menu delete     : Delete an item in menu");
        System.out.println("    order show      : Show all orders");
        System.out.println("    order export    : Export an order");
        System.out.println("    order clear     : Start a new order");
        System.out.println("    order get       : Show the current order");
        System.out.println("    order add       : Add an item to order");
        System.out.println("    order save      : Save the order");
        System.out.println("    quit/exit       : End the session");
    }

    public static MenuItem inputItem(Scanner scanner) {
        try {
            System.out.print("A dish (0) or a drink (1)? ");
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
                return new Food(name, description, unitPrice, unit, MenuType.FOOD, isAvailable, time);
            } else if (type.ordinal() == MenuType.DRINK.ordinal()) {
                System.out.print("Drink type (0: Soft drink, 1: Alcohol): ");
                int drinkTypeIn = Integer.parseInt(scanner.nextLine());
                DrinkType drinkType = DrinkType.getDrinkType(drinkTypeIn);
                System.out.print("Stock: ");
                int stock = Integer.parseInt(scanner.nextLine());
                return new Drink(name, description, unitPrice, unit, MenuType.DRINK, stock, drinkType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Canceling..");
        return null;
    }
}
