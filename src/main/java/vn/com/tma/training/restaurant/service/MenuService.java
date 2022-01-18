package vn.com.tma.training.restaurant.service;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;
import vn.com.tma.training.restaurant.exception.EntityNotFoundException;
import vn.com.tma.training.restaurant.exception.NotYetImplementedException;
import vn.com.tma.training.restaurant.io.reader.MenuReader;
import vn.com.tma.training.restaurant.io.writer.MenuWriter;
import vn.com.tma.training.restaurant.util.Index;
import vn.com.tma.training.restaurant.util.MenuItemComparator;

import java.util.ArrayList;
import java.util.List;

public class MenuService extends Service<MenuItem> {
    private final List<MenuItem> menuItemList;
    private final MenuWriter menuWriter;

    public MenuService() {
        super();
        MenuReader menuReader = new MenuReader();
        this.menuWriter = new MenuWriter();
        this.menuItemList = menuReader.read();
    }

    @Override
    public MenuItem get(int id) {
        for (MenuItem item : menuItemList) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void add(MenuItem itemToAdd) {
        Index index = indexReader.read();
        itemToAdd.setId(index.getMenuIndex() + 1);
        index.setMenuIndex(index.getMenuIndex() + 1);
        menuItemList.add(itemToAdd);
        menuWriter.write(this.menuItemList);
        indexWriter.write(index);
    }

    @Override
    public void update(int id, MenuItem itemToUpdate) {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).getId() == id) {
                itemToUpdate.setId(id);
                menuItemList.set(i, itemToUpdate);
                break;
            }
        }
        menuWriter.write(this.menuItemList);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).getId() == id) {
                menuItemList.remove(i);
                break;
            }
        }
        menuWriter.write(this.menuItemList);
    }

    @Override
    public void show() {
        List<MenuItem> items = new ArrayList<>(this.menuItemList);
        items.sort(new MenuItemComparator());
        String mainTypeMenu = "";
        String subTypeMenu = "";
        for (MenuItem item : items) {
            // To print menu type
            if (!mainTypeMenu.equals(item.getMenuType().getDisplayName())) {
                mainTypeMenu = item.getMenuType().getDisplayName();
                System.out.println(mainTypeMenu);
            }

            // To print sub menu type
            if ((item instanceof Food && !subTypeMenu.equals(((Food) item).getMealType().getDisplayName())) ||
                    (item instanceof Drink && !subTypeMenu.equals(((Drink) item).getDrinkType().getDisplayName()))) {
                if (item instanceof Food) {
                    subTypeMenu = ((Food) item).getMealType().getDisplayName();
                } else {
                    subTypeMenu = ((Drink) item).getDrinkType().getDisplayName();
                }
                System.out.println("    " + subTypeMenu);
            }

            System.out.println("        " + item);
        }
    }

    public void export() {
        throw new NotYetImplementedException();
    }
}