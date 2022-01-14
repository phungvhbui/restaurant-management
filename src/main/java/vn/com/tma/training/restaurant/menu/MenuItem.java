package vn.com.tma.training.restaurant.menu;

import vn.com.tma.training.restaurant.enumType.MenuType;

public class MenuItem {
    protected int id;
    protected String name;
    protected String description;
    protected int unitPrice;
    protected String unit;
    protected MenuType menuType;

    public MenuItem(int id, String name, int unitPrice, String unit) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
    }

    public MenuItem(int id, String name, String description, int unitPrice, String unit, MenuType menuType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.menuType = menuType;
    }

    public MenuItem(String name, String description, int unitPrice, String unit, MenuType menuType) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unit = unit;
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

    public String toStringForOrder() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + unitPrice + "/" + unit + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }
}
