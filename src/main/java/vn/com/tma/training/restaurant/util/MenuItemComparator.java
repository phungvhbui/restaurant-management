package vn.com.tma.training.restaurant.util;

import vn.com.tma.training.restaurant.entity.menu.Dish;
import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;

import java.util.Comparator;

/**
 * MenuItemComparator helps to define the sorting strategies for menu items
 */
public class MenuItemComparator implements Comparator<MenuItem> {
    /**
     * Compares two MenuItem
     *
     * @param o1 The first MenuItem
     * @param o2 The second MenuItem
     * @return An int value represents the comparison result.
     * If o1 < o2, return -1.
     * If o1 = o2, return 0.
     * If o1 > o2, return 1.
     */
    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        if (o1 instanceof Dish && o2 instanceof Drink) {
            return -1;
        } else if (o1 instanceof Drink && o2 instanceof Dish) {
            return 1;
        }

        if (o1 instanceof Dish && o2 instanceof Dish) {
            Dish f1 = (Dish) o1;
            Dish f2 = (Dish) o2;
            return f1.getMealType().compareTo(f2.getMealType());
        }

        if (o1 instanceof Drink && o2 instanceof Drink) {
            Drink d1 = (Drink) o1;
            Drink d2 = (Drink) o2;
            return d1.getDrinkType().compareTo(d2.getDrinkType());
        }
        return 0;
    }
}
