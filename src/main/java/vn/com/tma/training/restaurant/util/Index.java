package vn.com.tma.training.restaurant.util;

public class Index {
    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    private int menuIndex;
    private int orderIndex;

    public Index(int menuIndex, int orderIndex) {
        this.menuIndex = menuIndex;
        this.orderIndex = orderIndex;
    }

    public int getMenuIndex() {
        return menuIndex;
    }

    public int getOrderIndex() {
        return orderIndex;
    }
}
