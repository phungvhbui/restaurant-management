package vn.com.tma.training.restaurant.util;

public class Index {
    private int menuIndex;
    private int orderIndex;

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
