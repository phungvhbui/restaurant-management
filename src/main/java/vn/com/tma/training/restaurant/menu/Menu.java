package vn.com.tma.training.restaurant.menu;

import vn.com.tma.training.restaurant.io.reader.IndexReader;
import vn.com.tma.training.restaurant.io.reader.MenuReader;
import vn.com.tma.training.restaurant.io.writer.IndexWriter;
import vn.com.tma.training.restaurant.io.writer.MenuWriter;
import vn.com.tma.training.restaurant.util.Index;
import vn.com.tma.training.restaurant.util.MenuItemComparator;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> menuItemList;
    private final MenuWriter menuWriter;
    private final IndexReader indexReader;
    private final IndexWriter indexWriter;

    public Menu() {
        MenuReader menuReader = new MenuReader();
        this.menuWriter = new MenuWriter();
        this.menuItemList = menuReader.read();
        this.indexReader = new IndexReader();
        this.indexWriter = new IndexWriter();
    }

    public MenuItem getItem(int id) {
        for (MenuItem item : menuItemList) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    public void addItem(MenuItem item) {
        Index index = indexReader.read();
        item.id = index.getMenuIndex() + 1;
        index.setMenuIndex(index.getMenuIndex() + 1);
        menuItemList.add(item);
        menuWriter.write(this.menuItemList);
        indexWriter.write(index);
    }

    public void removeItem(int id) {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).id == id) {
                menuItemList.remove(i);
                break;
            }
        }
        menuWriter.write(this.menuItemList);
    }

    public void updateItem(int id, MenuItem item) {
        for (int i = 0; i < menuItemList.size(); i++) {
            if (menuItemList.get(i).id == id) {
                item.id = id;
                menuItemList.set(i, item);
                break;
            }
        }
        menuWriter.write(this.menuItemList);
    }

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

            // To print the item
            System.out.println("        " + item);
        }
    }
}