package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.MenuType;

public class MenuItem {
    protected int id;
    protected String name;
    protected String description;
    protected int unitPrice;
    protected String unitType;
    protected MenuType menuType;

    public MenuItem(int id, String name, int unitPrice, String unitType) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
    }

    public MenuItem(int id, String name, String description, int unitPrice, String unitType, MenuType menuType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.menuType = menuType;
    }

    public MenuItem(String name, String description, int unitPrice, String unitType, MenuType menuType) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.menuType = menuType;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUnitType() {
        return unitType;
    }

    public String toString() {
        return "ID: " + id + "\n             "
                + "Name: " + name + "\n             "
                + "Price: " + unitPrice + "/" + unitType;
    }
}
