package vn.com.tma.training.restaurant.util;

/**
 * Index class holds the current index of ids to make sure the ids will not collapse.
 * This program is not thread-safe tested.
 */
public class Index {
    private int menuIndex;
    private int orderIndex;

    /**
     * Constructor that set both menu and order indexes
     *
     * @param menuIndex  The current index (id) of menu
     * @param orderIndex The current index (id) of orders
     */
    public Index(int menuIndex, int orderIndex) {
        this.menuIndex = menuIndex;
        this.orderIndex = orderIndex;
    }

    public int getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
