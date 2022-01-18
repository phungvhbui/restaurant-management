package vn.com.tma.training.restaurant.entity.menu;

import vn.com.tma.training.restaurant.enumtype.ItemType;

/**
 * MenuItem class represents an item in the menu, which can be a Drink or a Dish.
 * A MenuItem will have all the basic information that all item can have: id, name, description, unit price, unit type and the type (dish or drink).
 * This class can be initialized because finished orders will not need to know the item is a dish or a drink.
 */
public class MenuItem {
    protected int id;
    protected String name;
    protected String description;
    protected int unitPrice;
    protected String unitType;
    protected ItemType itemType;

    /**
     * Constructor that set the basic information of an item. This constructor is usually used in Order.
     *
     * @param id        The identifier of the item
     * @param name      The name of the item
     * @param unitPrice The price of a single unit of the item
     * @param unitType  The unit type of the item (can, bottle, bowl...)
     */
    public MenuItem(int id, String name, int unitPrice, String unitType) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
    }

    /**
     * Constructor that set all information of an item.
     *
     * @param id          The identifier of the item
     * @param name        The name of the item
     * @param description The description of the item
     * @param unitPrice   The price of a single unit of the item
     * @param unitType    The unit type of the item (can, bottle, bowl...)
     * @param itemType    The type of item, can be a drink or a dish
     */
    public MenuItem(int id, String name, String description, int unitPrice, String unitType, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.itemType = itemType;
    }

    /**
     * Constructor that set all information of an item except for the identifier. This constructor is usually used when we need index to generate the id later.
     *
     * @param name        The name of the item
     * @param description The description of the item
     * @param unitPrice   The price of a single unit of the item
     * @param unitType    The unit type of the item (can, bottle, bowl...)
     * @param itemType    The type of item, can be a drink or a dish
     */
    public MenuItem(String name, String description, int unitPrice, String unitType, ItemType itemType) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.unitType = unitType;
        this.itemType = itemType;
    }

    /**
     * This method overrides hashCode() method of class Object. Hash code of an item now is its identifier.
     *
     * @return The identifier of the item
     */
    @Override
    public int hashCode() {
        return this.id;
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

    public ItemType getMenuType() {
        return itemType;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n             "
                + "Name: " + name + "\n             "
                + "Price: " + unitPrice + "/" + unitType;
    }
}
