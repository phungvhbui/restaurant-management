package vn.com.tma.training.restaurant.util;

import vn.com.tma.training.restaurant.entity.menu.Drink;
import vn.com.tma.training.restaurant.entity.menu.Food;
import vn.com.tma.training.restaurant.entity.menu.MenuItem;

import java.util.Comparator;

public class MenuItemComparator implements Comparator<MenuItem> {
    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        if (o1 instanceof Food && o2 instanceof Drink) {
            return -1;
        } else if (o1 instanceof Drink && o2 instanceof Food) {
            return 1;
        }

        if (o1 instanceof Food && o2 instanceof Food) {
            Food f1 = (Food) o1;
            Food f2 = (Food) o2;
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
