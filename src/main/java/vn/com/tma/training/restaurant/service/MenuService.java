package vn.com.tma.training.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.entity.menu.Dish;
import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.exception.InvalidAmountException;
import vn.com.tma.training.restaurant.io.reader.MenuReader;
import vn.com.tma.training.restaurant.io.writer.MenuWriter;
import vn.com.tma.training.restaurant.util.Index;
import vn.com.tma.training.restaurant.util.MenuItemComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuService extends Service<MenuItem> {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private final List<MenuItem> menuItemList;
    private final MenuWriter menuWriter;

    public MenuService() throws IOException {
        super();
        MenuReader menuReader = new MenuReader();
        this.menuWriter = new MenuWriter();
        this.menuItemList = menuReader.read();
        logger.info("Initialized Menu service");
    }

    @Override
    public MenuItem get(int id) {
        for (MenuItem item : menuItemList) {
            if (item.getId() == id) {
                logger.info("Found menu item id: " + id);
                return item;
            }
        }
        logger.warn("Menu item with id: " + id + " not found");
        throw new EntityNotFoundException();
    }

    @Override
    public void add(MenuItem itemToAdd) throws IOException {
        Index index = indexReader.read();
        itemToAdd.setId(index.getMenuIndex() + 1);
        index.setMenuIndex(index.getMenuIndex() + 1);
        menuItemList.add(itemToAdd);
        menuWriter.write(this.menuItemList);
        indexWriter.write(index);
        logger.info("Added item with id: " + itemToAdd.getId());
    }

    @Override
    public void update(int id, MenuItem itemToUpdate) throws IOException {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).getId() == id) {
                itemToUpdate.setId(id);
                menuItemList.set(i, itemToUpdate);
                menuWriter.write(this.menuItemList);
                logger.info("Updated item with id: " + itemToUpdate.getId());
                return;
            }
        }
        logger.warn("Menu item with id: " + id + " not found");
        throw new EntityNotFoundException();
    }

    @Override
    public void remove(int id) throws IOException {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).getId() == id) {
                menuItemList.remove(i);
                menuWriter.write(this.menuItemList);
                logger.info("Removed item with id: " + id);
                return;
            }
        }
        logger.warn("Menu item with id: " + id + " not found");
        throw new EntityNotFoundException();
    }

    @Override
    public void show() {
        List<MenuItem> items = new ArrayList<>(this.menuItemList);
        items.sort(new MenuItemComparator());
        String mainTypeMenu = "";
        String subTypeMenu = "";
        for (MenuItem item : items) {
            if (!mainTypeMenu.equals(item.getMenuType().toString())) {
                mainTypeMenu = item.getMenuType().toString();
                System.out.println(mainTypeMenu);
            }

            if ((item instanceof Dish && !subTypeMenu.equals(((Dish) item).getMealType().toString())) ||
                    (item instanceof Drink && !subTypeMenu.equals(((Drink) item).getDrinkType().toString()))) {
                if (item instanceof Dish) {
                    subTypeMenu = ((Dish) item).getMealType().toString();
                } else {
                    subTypeMenu = ((Drink) item).getDrinkType().toString();
                }
                System.out.println("    " + subTypeMenu);
            }

            System.out.println("        " + item);
        }
        System.out.println();
    }

    public void reduceStock(int itemId, int quality) {
        for (MenuItem item : menuItemList) {
            if (item.getId() == itemId) {
                if (item instanceof Drink) {
                    Drink drink = (Drink) item;
                    if (drink.getStock() < quality) {
                        throw new InvalidAmountException();
                    }
                    drink.setStock(drink.getStock() - quality);
                    logger.info("Reduced stock of a drink item with id: " + itemId + " (cached only)");
                }
                return;
            }
        }
        logger.warn("Menu item with id: " + itemId + " not found");
        throw new EntityNotFoundException();
    }

    public void sync() throws IOException {
        menuWriter.write(this.menuItemList);
    }
}